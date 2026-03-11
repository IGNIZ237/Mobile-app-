package com.agrriguard.ui.home

import androidx.lifecycle.*
import com.agrriguard.data.model.DiseaseReport
import com.agrriguard.data.repository.DiseaseRepository
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: DiseaseRepository) : ViewModel() {

    val allReports: LiveData<List<DiseaseReport>> = repository.allReports.asLiveData()

    // Status GPS (pourrait être géré par un StateFlow ou LiveData)
    private val _isGpsActive = MutableLiveData<Boolean>(false)
    val isGpsActive: LiveData<Boolean> = _isGpsActive

    fun setGpsStatus(active: Boolean) {
        _isGpsActive.value = active
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
