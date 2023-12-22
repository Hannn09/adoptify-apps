package com.example.adoptify.ui.shelter

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.adoptify.model.AdoptifyRepository
import com.example.adoptify.service.response.DataShelter
import kotlinx.coroutines.launch

class ShelterViewModel(private val adoptifyRepository: AdoptifyRepository) : ViewModel()  {
    val shelterResponse: LiveData<List<DataShelter>> = adoptifyRepository.shelterResponse
    val isLoading: LiveData<Boolean> = adoptifyRepository.isLoading

    fun getShelter() {
        viewModelScope.launch {
            adoptifyRepository.getShelter()
        }
    }
}