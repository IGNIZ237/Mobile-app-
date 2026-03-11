package com.agrriguard.ui.result

import android.app.Application
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import androidx.lifecycle.*
import com.agrriguard.data.model.DiseaseKnowledge
import com.agrriguard.data.model.DiseaseReport
import com.agrriguard.data.repository.DiseaseRepository
import com.agrriguard.ml.DiagnosticResult
import com.agrriguard.ml.PlantDiseaseClassifier
import com.agrriguard.utils.GpsUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ResultViewModel(
    application: Application,
    private val repository: DiseaseRepository
) : AndroidViewModel(application) {

    private val classifier = PlantDiseaseClassifier(application)

    private val _diagnosticResult = MutableLiveData<DiagnosticResult>()
    val diagnosticResult: LiveData<DiagnosticResult> = _diagnosticResult

    private val _diseaseKnowledge = MutableLiveData<DiseaseKnowledge?>()
    val diseaseKnowledge: LiveData<DiseaseKnowledge?> = _diseaseKnowledge

    private val _isReporting = MutableLiveData<Boolean>(false)
    val isReporting: LiveData<Boolean> = _isReporting

    fun classifyImage(imageUri: Uri) {
        viewModelScope.launch {
            val bitmap = withContext(Dispatchers.IO) {
                val context = getApplication<Application>().applicationContext
                MediaStore.Images.Media.getBitmap(context.contentResolver, imageUri)
            }
            
            val result = withContext(Dispatchers.Default) {
                classifier.classify(bitmap)
            }
            
            _diagnosticResult.value = result
            
            // Fetch detailed knowledge from DB
            val knowledge = repository.getKnowledgeByName(result.diseaseName)
            _diseaseKnowledge.value = knowledge
        }
    }

    fun reportCase(latitude: Double, longitude: Double) {
        val result = _diagnosticResult.value ?: return
        if (result.diseaseId == "healthy") return

        _isReporting.value = true
        viewModelScope.launch {
            val report = DiseaseReport(
                diseaseName = result.diseaseName,
                cropType = _diseaseKnowledge.value?.affectedCrops ?: "Inconnu",
                latitude = latitude,
                longitude = longitude,
                timestamp = System.currentTimeMillis()
            )
            repository.insertReport(report)
            _isReporting.value = false
        }
    }

    override fun onCleared() {
        super.onCleared()
        classifier.close()
    }
}

class ResultViewModelFactory(
    private val application: Application,
    private val repository: DiseaseRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ResultViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ResultViewModel(application, repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
