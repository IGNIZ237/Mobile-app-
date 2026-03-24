package com.example.excelgradeprocessor

import android.app.Activity
import android.content.Intent
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.pdf.PdfDocument
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import kotlin.concurrent.thread

/**
 * PROJET : Student Grade Calculator (Version Interactive & Abstraite)
 * Ce code fusionne les notions des Chapitres 1 et 2 du cours SE 3242.
 */

// 1. DATA CLASS (Chap 1, Slide 21) : Modèle de données pour les étudiants
data class Student(val name: String, val matricule: String, val score: Int?)

// 2. ABSTRACT CLASS (Chap 2, Slide 3) : Modèle de base pour le traitement des fichiers
// Elle définit LA STRUCTURE commune, mais laisse les détails aux classes filles.
abstract class FileProcessor {
    // Méthode abstraite : doit être implémentée par la classe fille
    abstract fun process(inputUri: Uri, excelOut: File, pdfOut: File): Boolean
}

// 3. POO & HERITAGE : Implémentation concrète du processeur
class ExcelGradeProcessor(
    private val activity: Activity,
    private val evaluator: GradeEvaluator // Utilisation de la composition
) : FileProcessor() {

    override fun process(inputUri: Uri, excelOut: File, pdfOut: File): Boolean {
        return try {
            val students = readExcelData(inputUri)
            writeExcel(students, excelOut)
            generateReport(pdfOut)
            true
        } catch (e: Exception) {
            Log.e("Excel", "Erreur: ${e.message}")
            false
        }
    }

    private fun readExcelData(uri: Uri): List<Student> {
        val students = mutableListOf<Student>()
        activity.contentResolver.openInputStream(uri)?.use { stream ->
            val workbook = XSSFWorkbook(stream)
            val sheet = workbook.getSheetAt(0)
            for (row in sheet) {
                if (row.rowNum == 0) continue
                val name = row.getCell(0)?.stringCellValue ?: "Inconnu"
                val matricule = row.getCell(1)?.stringCellValue ?: "N/A"
                val score = row.getCell(2)?.numericCellValue?.toInt()
                students.add(Student(name, matricule, score))
            }
            workbook.close()
        }
        return students
    }

    private fun writeExcel(list: List<Student>, file: File) {
        XSSFWorkbook().apply {
            val sheet = createSheet("Grades")
            val header = sheet.createRow(0)
            header.createCell(0).setCellValue("NOM")
            header.createCell(1).setCellValue("MATRICULE")
            header.createCell(2).setCellValue("GRADE")

            list.forEachIndexed { i, s ->
                val row = sheet.createRow(i + 1)
                row.createCell(0).setCellValue(s.name)
                row.createCell(1).setCellValue(s.matricule)
                row.createCell(2).setCellValue(evaluator.getGrade(s.score))
            }
            FileOutputStream(file).use { write(it) }
            close()
        }
    }

    private fun generateReport(file: File) {
        val doc = PdfDocument()
        val info = PdfDocument.PageInfo.Builder(595, 842, 1).create()
        val page = doc.startPage(info)
        with(page.canvas) {
            val p = Paint().apply { 
                textSize = 20f
                isFakeBoldText = true 
            }
            drawText("RAPPORT TECHNIQUE - SE 3242", 50f, 50f, p)
            
            p.apply { 
                textSize = 12f
                isFakeBoldText = false 
            }
            var y = 100f
            val documentation = listOf(
                "--- NOTIONS UTILISÉES ---",
                "1. ABSTRACT CLASS : FileProcessor définit le contrat de traitement.",
                "2. FILE PICKER : Utilisation de l'interface Android pour charger",
                "   n'importe quel fichier externe (plus seulement les assets).",
                "3. COMPOSITION : Utilisation de GradeEvaluator à l'intérieur du processeur.",
                "4. SCOPE FUNCTIONS : apply, with, et use pour un code propre.",
                "5. NULL SAFETY : Toujours au cœur du traitement des données Excel.",
                "",
                "--- FONCTIONNEMENT SUR PC ---",
                "Le code de traitement (readExcelData, writeExcel) est portable sur PC.",
                "Seule l'interface (Activity) change entre Android et Desktop."
            )
            for (line in documentation) {
                drawText(line, 50f, y, p)
                y += 25f
            }
        }
        doc.finishPage(page)
        FileOutputStream(file).use { doc.writeTo(it) }
        doc.close()
    }
}

// 4. LOGIQUE MÉTIER (Chap 1)
class GradeEvaluator {
    fun getGrade(score: Int?): String {
        val finalScore = score ?: 0
        return when (finalScore) {
            in 90..100 -> "A"
            in 80..89  -> "B"
            in 70..79  -> "C"
            in 60..69  -> "D"
            else       -> "F"
        }
    }
}

class MainActivity : AppCompatActivity() {

    // 5. DELEGATION (Chap 2)
    private val processor: FileProcessor by lazy { 
        ExcelGradeProcessor(this, GradeEvaluator()) 
    }
    
    private lateinit var btnSelectFile: Button
    private lateinit var progressBar: ProgressBar
    private lateinit var tvStatus: TextView

    // 6. ACTION DU SÉLECTEUR DE FICHIER (Interactive GUI)
    private val selectFileLauncher = registerForActivityResult(
        ActivityResultContracts.OpenDocument()
    ) { uri: Uri? ->
        uri?.let { startProcessing(it) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnSelectFile = findViewById(R.id.btnSelectFile)
        progressBar = findViewById(R.id.progressBar)
        tvStatus = findViewById(R.id.tvStatus)

        btnSelectFile.setOnClickListener {
            // Lancer le sélecteur de fichier Android
            selectFileLauncher.launch(arrayOf("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
        }
    }

    private fun startProcessing(uri: Uri) {
        btnSelectFile.isEnabled = false
        progressBar.visibility = View.VISIBLE
        tvStatus.text = "Traitement du fichier sélectionné..."

        val outputDir = getExternalFilesDir(null)
        val excelOut = File(outputDir, "results_custom.xlsx")
        val pdfOut = File(outputDir, "TP_Documentation_Interactive.pdf")

        thread {
            val success = processor.process(uri, excelOut, pdfOut)
            runOnUiThread {
                progressBar.visibility = View.GONE
                btnSelectFile.isEnabled = true
                tvStatus.text = if (success) {
                    "Terminé ! Fichiers générés dans le stockage privé."
                } else {
                    "Échec du traitement."
                }
            }
        }
    }
}
