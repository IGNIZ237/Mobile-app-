package com.agrriguard.ui.result

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.agrriguard.AgriGuardApp
import com.agrriguard.R
import com.agrriguard.databinding.ActivityResultBinding
import com.agrriguard.ui.camera.CameraActivity
import com.agrriguard.utils.GpsUtils

class ResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultBinding
    private val viewModel: ResultViewModel by viewModels {
        ResultViewModelFactory(application, (application as AgriGuardApp).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val imageUriString = intent.getStringExtra(CameraActivity.EXTRA_IMAGE_URI)
        if (imageUriString != null) {
            val imageUri = Uri.parse(imageUriString)
            binding.imgCaptured.setImageURI(imageUri)
            viewModel.classifyImage(imageUri)
        }

        setupObservers()
        setupClickListeners()
    }

    private fun setupObservers() {
        viewModel.diagnosticResult.observe(this) { result ->
            binding.txtDiseaseName.text = result.diseaseName
            val confidencePercent = (result.confidence * 100).toInt()
            binding.progressConfidence.progress = confidencePercent
            binding.txtConfidencePercent.text = "$confidencePercent%"

            if (result.diseaseId == "healthy") {
                binding.btnReport.visibility = View.GONE
            }
        }

        viewModel.diseaseKnowledge.observe(this) { knowledge ->
            if (knowledge != null) {
                binding.txtTreatment.text = "Naturel : ${knowledge.naturalTreatment}\n\nChimique : ${knowledge.chemicalTreatment}"
                binding.txtPrevention.text = knowledge.prevention
            } else {
                binding.txtTreatment.text = "Aucun conseil spécifique trouvé."
                binding.txtPrevention.text = "Maintenez une surveillance régulière."
            }
        }

        viewModel.isReporting.observe(this) { isReporting ->
            binding.btnReport.isEnabled = !isReporting
            if (isReporting) {
                Toast.makeText(this, "Signalement en cours...", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupClickListeners() {
        binding.btnReport.setOnClickListener {
            handleReport()
        }

        binding.btnShare.setOnClickListener {
            shareResult()
        }
    }

    private fun handleReport() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Permission GPS requise pour signaler", Toast.LENGTH_SHORT).show()
            return
        }

        GpsUtils.getCurrentLocation(this).addOnSuccessListener { location ->
            if (location != null) {
                viewModel.reportCase(location.latitude, location.longitude)
                Toast.makeText(this, "Cas signalé avec succès", Toast.LENGTH_SHORT).show()
                binding.btnReport.visibility = View.GONE
            } else {
                Toast.makeText(this, "Impossible d'obtenir la position GPS", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener {
            Toast.makeText(this, "Erreur lors de la récupération du GPS", Toast.LENGTH_SHORT).show()
        }
    }

    private fun shareResult() {
        val diseaseName = binding.txtDiseaseName.text
        val shareIntent = Intent().apply {
            action = Intent.ACTION_SEND
            type = "text/plain"
            putExtra(Intent.EXTRA_SUBJECT, "Diagnostic AgriGuard")
            putExtra(Intent.EXTRA_TEXT, "AgriGuard a détecté : $diseaseName sur ma culture. Conseils de traitement : ${binding.txtTreatment.text}")
        }
        startActivity(Intent.createChooser(shareIntent, "Partager le diagnostic"))
    }
}
