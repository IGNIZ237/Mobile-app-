package com.agrriguard.ml

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import org.tensorflow.lite.Interpreter
import org.tensorflow.lite.support.common.FileUtil
import org.tensorflow.lite.support.common.ops.NormalizeOp
import org.tensorflow.lite.support.image.ImageProcessor
import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.support.image.ops.ResizeOp
import java.nio.MappedByteBuffer

data class DiagnosticResult(
    val diseaseId: String,
    val diseaseName: String,
    val confidence: Float,
    val treatmentAdvice: String = "",
    val preventionTips: String = ""
)

class PlantDiseaseClassifier(private val context: Context) {

    private var interpreter: Interpreter? = null
    private val modelPath = "plant_disease_model.tflite"
    private val inputImageSize = 200

    // Liste étendue à 39 labels (Ordre standard PlantVillage + extensions)
    private val labels = listOf(
        "Pomme___Tavelure", "Pomme___Pourriture_noire", "Pomme___Rouille_cedre", "Pomme___Saine",
        "Myrtille___Saine", "Cerise___Mildiou", "Cerise___Saine",
        "Maïs___Taches_grises", "Maïs___Rouille_commune", "Maïs___Mildiou_du_Nord", "Maïs___Sain",
        "Raisin___Pourriture_noire", "Raisin___Esca", "Raisin___Brulure_feuille", "Raisin___Sain",
        "Orange___Citrus_Greening", "Pêche___Taches_bactériennes", "Pêche___Saine",
        "Poivron___Taches_bactériennes", "Poivron___Sain",
        "Pomme_de_terre___Mildiou_précoce", "Pomme_de_terre___Mildiou_tardif", "Pomme_de_terre___Saine",
        "Framboise___Saine", "Soja___Saine", "Courge___Mildiou",
        "Fraise___Brulure_feuille", "Fraise___Saine",
        "Tomate___Taches_bactériennes", "Tomate___Mildiou_précoce", "Tomate___Mildiou_tardif",
        "Tomate___Moisissure_feuille", "Tomate___Septoriose", "Tomate___Acariens",
        "Tomate___Taches_cibles", "Tomate___Virus_enroulement_jaune", "Tomate___Virus_mosaique", "Tomate___Saine",
        "Autre___Inconnu" // 39ème label
    )

    init {
        try {
            val model: MappedByteBuffer = FileUtil.loadMappedFile(context, modelPath)
            val options = Interpreter.Options()
            interpreter = Interpreter(model, options)
            Log.d("Classifier", "Modèle TFLite chargé (39 classes)")
        } catch (e: Exception) {
            Log.e("Classifier", "Erreur chargement modèle", e)
        }
    }

    fun classify(bitmap: Bitmap): DiagnosticResult {
        try {
            if (interpreter == null) return DiagnosticResult("error", "Modèle non chargé", 0f)

            val imageProcessor = ImageProcessor.Builder()
                .add(ResizeOp(inputImageSize, inputImageSize, ResizeOp.ResizeMethod.BILINEAR))
                .add(NormalizeOp(0f, 255f))
                .build()

            var tensorImage = TensorImage(interpreter!!.getInputTensor(0).dataType())
            tensorImage.load(bitmap)
            tensorImage = imageProcessor.process(tensorImage)

            // Buffer de sortie dimensionné à 39
            val probabilityBuffer = FloatArray(39)
            interpreter?.run(tensorImage.buffer, arrayOf(probabilityBuffer))

            var maxIdx = 0
            var maxProb = 0f
            for (i in probabilityBuffer.indices) {
                if (probabilityBuffer[i] > maxProb) {
                    maxProb = probabilityBuffer[i]
                    maxIdx = i
                }
            }

            // Sécurité si l'index dépasse la liste des labels
            val resultLabel = if (maxIdx < labels.size) labels[maxIdx] else "Maladie inconnue ($maxIdx)"

            return DiagnosticResult(
                diseaseId = resultLabel,
                diseaseName = resultLabel.replace("___", " ").replace("_", " "),
                confidence = maxProb
            )
        } catch (e: Exception) {
            Log.e("Classifier", "Erreur diagnostic", e)
            return DiagnosticResult("error", "Erreur : ${e.message}", 0f)
        }
    }

    fun close() {
        interpreter?.close()
        interpreter = null
    }
}
