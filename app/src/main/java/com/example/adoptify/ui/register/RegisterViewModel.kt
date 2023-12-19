package com.example.adoptify.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.adoptify.model.AdoptifyRepository
import com.example.adoptify.service.response.RegisterResponse
import kotlinx.coroutines.launch

class RegisterViewModel(private val adoptRepository: AdoptifyRepository) : ViewModel() {
    val registerResponse: LiveData<RegisterResponse> = adoptRepository.registerResponse
    val isLoading: LiveData<Boolean> = adoptRepository.isLoading
    val isSuccess: LiveData<Boolean> = adoptRepository.isSuccess


    private val _isShow = MutableLiveData<Boolean>()
    val isShow: LiveData<Boolean> = _isShow
    fun registerUser(name: String, email: String, password: String) {
        viewModelScope.launch {
            try {
            adoptRepository.register(name, email, password)
                _isShow.value = true
            } catch (e: Exception) {
                _isShow.value = false
            }

        }
    }
}