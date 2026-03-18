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

    // Liste EXACTE fournie par l'utilisateur (39 labels)
    private val labels = listOf(
        "apple apple scab",
        "apple black rot",
        "apple cedar apple rust",
        "apple healthy",
        "blueberry healthy",
        "cherry including sour powdery mildew",
        "cherry including sour healthy",
        "corn maize cercospora leaf spot gray leaf spot",
        "corn maize common rust",
        "corn maize northern leaf blight",
        "corn maize healthy",
        "grape black rot",
        "grape esca black measles",
        "grape leaf blight isariopsis leaf spot",
        "grape healthy",
        "orange haunglongbing citrus greening",
        "peach bacterial spot",
        "peach healthy",
        "pepper bell bacterial spot",
        "pepper bell healthy",
        "potato early blight",
        "potato late blight",
        "potato healthy",
        "raspberry healthy",
        "soybean healthy",
        "squash powdery mildew",
        "strawberry leaf scorch",
        "strawberry healthy",
        "tomato bacterial spot",
        "tomato early blight",
        "tomato late blight",
        "tomato leaf mold",
        "tomato septoria leaf spot",
        "tomato spider mites two spotted spider mite",
        "tomato target spot",
        "tomato tomato yellow leaf curl virus",
        "tomato tomato mosaic virus",
        "tomato healthy",
        "background"
    )

    init {
        try {
            val model: MappedByteBuffer = FileUtil.loadMappedFile(context, modelPath)
            val options = Interpreter.Options()
            interpreter = Interpreter(model, options)
            Log.d("Classifier", "Modèle TFLite chargé avec succès (39 classes)")
        } catch (e: Exception) {
            Log.e("Classifier", "Erreur lors du chargement du modèle", e)
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

            val resultLabel = if (maxIdx < labels.size) labels[maxIdx] else "Unknown"

            return DiagnosticResult(
                diseaseId = resultLabel,
                diseaseName = formatName(resultLabel),
                confidence = maxProb
            )
        } catch (e: Exception) {
            Log.e("Classifier", "Erreur d'inférence", e)
            return DiagnosticResult("error", "Erreur : ${e.message}", 0f)
        }
    }

    private fun formatName(name: String): String {
        return name.replace(" ", " ").capitalizeWords()
    }

    private fun String.capitalizeWords(): String = split(" ").joinToString(" ") { it.replaceFirstChar { char -> char.uppercase() } }

    fun close() {
        interpreter?.close()
        interpreter = null
    }
}
