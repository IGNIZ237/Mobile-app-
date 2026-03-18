package com.agrriguard.ui.home

import android.location.Location
import androidx.lifecycle.*
import com.agrriguard.data.model.DiseaseReport
import com.agrriguard.data.repository.DiseaseRepository
import com.agrriguard.utils.GpsUtils
import kotlinx.coroutines.launch

data class RiskZone(
    val diseaseName: String,
    val distance: Double,
    val severity: String // "HIGH" or "MODERATE"
)

class HomeViewModel(private val repository: DiseaseRepository) : ViewModel() {

    val allReports: LiveData<List<DiseaseReport>> = repository.allReports.asLiveData()

    private val _isGpsActive = MutableLiveData<Boolean>(false)
    val isGpsActive: LiveData<Boolean> = _isGpsActive

    private val _currentRisk = MutableLiveData<RiskZone?>(null)
    val currentRisk: LiveData<RiskZone?> = _currentRisk

    fun setGpsStatus(active: Boolean) {
        _isGpsActive.value = active
    }

    // Analyse les rapports pour détecter un risque à proximité
    fun checkNearbyRisks(userLocation: Location) {
        viewModelScope.launch {
            val reports = repository.allReports.asLiveData().value ?: return@launch
            
            // On groupe les rapports par maladie dans un rayon de 5km
            val nearbyReports = reports.filter { report ->
                GpsUtils.calculateDistance(
                    userLocation.latitude, userLocation.longitude,
                    report.latitude, report.longitude
                ) <= 5.0
            }

            if (nearbyReports.isNotEmpty()) {
                val mostFrequentDisease = nearbyReports.groupBy { it.diseaseName }
                    .maxByOrNull { it.value.size }
                
                if (mostFrequentDisease != null) {
                    val count = mostFrequentDisease.value.size
                    val severity = if (count >= 3) "HIGH" else "MODERATE"
                    
                    // Calcul de la distance du cas le plus proche
                    val minDistance = mostFrequentDisease.value.minOf {
                        GpsUtils.calculateDistance(
                            userLocation.latitude, userLocation.longitude,
                            it.latitude, it.longitude
                        )
                    }

                    _currentRisk.postValue(RiskZone(mostFrequentDisease.key, minDistance, severity))
                }
            } else {
                _currentRisk.postValue(null)
            }
        }
    }
}

class HomeViewModelFactory(private val repository: DiseaseRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HomeViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
