package com.example.adoptify.ui.pet

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.adoptify.model.AdoptifyRepository
import com.example.adoptify.service.response.AdoptResponse
import com.example.adoptify.service.response.DataAbandoned
import com.example.adoptify.service.response.DataItem
import com.example.adoptify.service.response.PetResponse
import kotlinx.coroutines.launch
import java.io.File

class PetViewModel(private val adoptifyRepository: AdoptifyRepository) : ViewModel() {
    val isLoading: LiveData<Boolean> = adoptifyRepository.isLoading
    val listPet: LiveData<List<DataItem>> = adoptifyRepository.petResponse
    val detailPet: LiveData<List<DataItem>> = adoptifyRepository.detailPetResponse
    val recommendationPet: LiveData<List<DataItem>> = adoptifyRepository.recommendationPetResponse
    val adoptResponse: LiveData<AdoptResponse> = adoptifyRepository.adoptResponse
    val abandonedResponse: LiveData<List<DataAbandoned>> = adoptifyRepository.abandonedResponse

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

    fun getRecommendationPet(petId: Int, recomType: String) {
        viewModelScope.launch {
            adoptifyRepository.getRecommendationPet(petId, recomType)
        }
    }

    fun insertAdopt(
        name: String,
        email: String,
        address: String,
        prov: String,
        pos: String,
        kartuIdentitas: File,
        buktiTransfer: File,
        petId: Int,
    ) {
        viewModelScope.launch {
            adoptifyRepository.processAdopt(name, email, address, prov, pos, kartuIdentitas,  buktiTransfer, petId)
        }
    }

    fun getAbandonedPet() {
        viewModelScope.launch {
            adoptifyRepository.getAbandonedPet()
        }
    }
}