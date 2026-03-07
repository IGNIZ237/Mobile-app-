package com.example.excelgradeprocessor

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.pdf.PdfDocument
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream

/**
 * PROJET : Student Grade Calculator (Version Améliorée - Chapitre 1 & 2)
 * Notions intégrées : POO (Interfaces, Classes), Fonctions d'ordre supérieur,
 * Lambdas, Null Safety avancée, expressions when/if.
 */

// 1. DATA CLASS (Slide 21 - Chap 1) : Génère auto toString, equals, copy...
data class Student(val name: String, val matricule: String, val score: Int?)

// 2. INTERFACE (Chapitre 2) : Définit un contrat pour le calcul des notes
interface IGradeCalculator {
    fun calculate(score: Int?): String
}

// 3. POO & HERITAGE (Chapitre 2) : Implémentation concrète du calculateur
class StudentEvaluator : IGradeCalculator {
    // Expression 'when' comme valeur de retour (Slide 17)
    override fun calculate(score: Int?): String {
        // Utilisation de l'opérateur ELVIS (Slide 13)
        val finalScore = score ?: 0
        
        return when (finalScore) {
            in 90..100 -> "A (Excellent)"
            in 80..89  -> "B (Très Bien)"
            in 70..79  -> "C (Bien)"
            in 60..69  -> "D (Passable)"
            else       -> "F (Échec)"
        }
    }
}

// 4. CLASSE ABSTRAITE (Chapitre 2) : Modèle de base pour le traitement
abstract class BaseFileProcessor {
    abstract fun process()
}

class MainActivity : AppCompatActivity() {

    // Utilisation de 'by lazy' (Chap 2, Slide 6) pour économiser des ressources
    private val evaluator by lazy { StudentEvaluator() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // val (Immuable - Slide 5)
        val outputDir = getExternalFilesDir(null)
        val outputFile = File(outputDir, "results.xlsx")
        val docFile = File(outputDir, "documentation_cours.pdf")

        // Exécution des tâches
        runProcessingPipeline("students.xlsx", outputFile, docFile)
    }

    /**
     * 5. FONCTION D'ORDRE SUPÉRIEUR (Chapitre 2)
     * Prend une lambda ou gère une logique complexe de pipeline.
     */
    private fun runProcessingPipeline(assetName: String, excelOut: File, pdfOut: File) {
        try {
            // Lecture
            val students = readStudentsFromAssets(assetName)
            
            // Traitement avec LAMBDA & Higher-order function (Slide 26 - Chap 1)
            // On filtre les étudiants qui n'ont pas de nom (Null Safety)
            val validStudents = students.filter { it.name.isNotBlank() }

            // Écriture Excel
            writeToExcel(validStudents, excelOut)

            // Génération Documentation PDF détaillée
            generateDetailedPdf(pdfOut)

            Log.d("Excel", "Opération terminée. PDF et Excel générés avec succès.")
        } catch (e: Exception) {
            Log.e("Excel", "Erreur Pipeline: ${e.message}")
        }
    }

    private fun readStudentsFromAssets(fileName: String): List<Student> {
        val students = mutableListOf<Student>()
        assets.open(fileName).use { inputStream ->
            val workbook = XSSFWorkbook(inputStream)
            val sheet = workbook.getSheetAt(0)
            
            for (row in sheet) {
                if (row.rowNum == 0) continue // Skip header
                
                // Safe calls ?. (Slide 12)
                val name = row.getCell(0)?.stringCellValue ?: "Inconnu"
                val matricule = row.getCell(1)?.stringCellValue ?: "N/A"
                val score = row.getCell(2)?.numericCellValue?.toInt()
                
                students.add(Student(name, matricule, score))
            }
            workbook.close()
        }
        return students
    }

    private fun writeToExcel(students: List<Student>, file: File) {
        val workbook = XSSFWorkbook()
        val sheet = workbook.createSheet("Resultats_Finaux")

        // Création des entêtes
        val header = sheet.createRow(0)
        listOf("NOM", "MATRICULE", "GRADE CALCULÉ").forEachIndexed { i, title ->
            header.createCell(i).setCellValue(title)
        }

        // Remplissage avec forEachIndexed (Functional Style)
        students.forEachIndexed { index, student ->
            val row = sheet.createRow(index + 1)
            row.createCell(0).setCellValue(student.name)
            row.createCell(1).setCellValue(student.matricule)
            // Appel à notre interface Evaluator
            row.createCell(2).setCellValue(evaluator.calculate(student.score))
        }

        FileOutputStream(file).use { workbook.write(it) }
        workbook.close()
    }

    private fun generateDetailedPdf(file: File) {
        val document = PdfDocument()
        val pageInfo = PdfDocument.PageInfo.Builder(595, 842, 1).create()
        val page = document.startPage(pageInfo)
        val canvas = page.canvas
        val paint = Paint()

        // Titre
        paint.color = Color.BLUE
        paint.textSize = 20f
        paint.isFakeBoldText = true
        canvas.drawText("DOCUMENTATION : EXCEL GRADE PROCESSOR", 50f, 50f, paint)

        // Corps du texte
        paint.color = Color.BLACK
        paint.isFakeBoldText = false
        paint.textSize = 11f
        var y = 90f
        
        val docContent = listOf(
            "--- NOTIONS DE COURS UTILISÉES (CHAP 1 & 2) ---",
            "1. VARIABLES (val vs var) : Utilisation de 'val' pour l'immuabilité par défaut.",
            "2. NULL SAFETY : Utilisation intensive de ?. (Safe call) et ?: (Elvis) pour",
            "   éviter le 'Billion Dollar Mistake' (Slide 10, Chap 1).",
            "3. DATA CLASS : Student est une 'data class', idéale pour stocker les infos",
            "   extraites de l'Excel (Slide 21, Chap 1).",
            "4. POO & INTERFACES : Utilisation de IGradeCalculator pour séparer la logique",
            "   de calcul de l'interface Android (Chap 2).",
            "5. INITIALISATION TARDIVE : 'by lazy' utilisé pour l'évaluateur afin",
            "   d'optimiser la mémoire (Slide 6, Chap 2).",
            "6. FONCTIONS D'ORDRE SUPÉRIEUR : Utilisation de .filter { } et .forEach { }",
            "   pour manipuler les listes d'étudiants via des Lambdas.",
            "",
            "--- ÉTAPES DU PROGRAMME ---",
            "A. Ouverture du flux Asset vers 'students.xlsx'.",
            "B. Parcours des lignes et conversion en objets Kotlin Student.",
            "C. Injection de la logique métier via l'interface IGradeCalculator.",
            "D. Création d'un nouveau Workbook Excel avec entêtes personnalisées.",
            "E. Exportation finale dans le stockage privé de l'application.",
            "",
            "Auteur : Etudiant SE 3242 - ICT University"
        )

        for (line in docContent) {
            canvas.drawText(line, 50f, y, paint)
            y += 22f
        }

        document.finishPage(page)
        FileOutputStream(file).use { document.writeTo(it) }
        document.close()
    }
}
