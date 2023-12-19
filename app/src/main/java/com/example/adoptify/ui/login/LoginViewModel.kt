package com.example.adoptify.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.adoptify.model.AdoptifyRepository
import com.example.adoptify.model.SessionModel
import com.example.adoptify.service.response.LoginResponse
import kotlinx.coroutines.launch

class LoginViewModel(private val adoptifyRepository: AdoptifyRepository): ViewModel() {
    val loginResponse: LiveData<LoginResponse> = adoptifyRepository.loginResponse
    val isLoading: LiveData<Boolean> = adoptifyRepository.isLoading

    fun login(email: String, password: String) {
        viewModelScope.launch {
            adoptifyRepository.login(email, password)
        }
    }

    fun saveSession(session: SessionModel) {
        viewModelScope.launch {
            adoptifyRepository.saveSession(session)
        }
    }

    fun getSession() : LiveData<SessionModel> {
        return adoptifyRepository.getSession()
    }

    fun logout() {
        viewModelScope.launch {
            adoptifyRepository.logout()
        }
    }
}