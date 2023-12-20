package com.example.adoptify.ui.pet

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.adoptify.model.AdoptifyRepository
import com.example.adoptify.service.response.DataItem
import com.example.adoptify.service.response.PetResponse
import kotlinx.coroutines.launch

class PetViewModel(private val adoptifyRepository: AdoptifyRepository): ViewModel() {
    val isLoading: LiveData<Boolean> = adoptifyRepository.isLoading
    val listPet: LiveData<List<DataItem>> = adoptifyRepository.petResponse
    val detailPet: LiveData<List<DataItem>> = adoptifyRepository.detailPetResponse

    fun getPet(type: String) {
        viewModelScope.launch {
            adoptifyRepository.getListPet(type)
        }
    }

    fun getDetailPet(uid: Int) {
        viewModelScope.launch {
            adoptifyRepository.getPetById(uid)
        }
    }
}