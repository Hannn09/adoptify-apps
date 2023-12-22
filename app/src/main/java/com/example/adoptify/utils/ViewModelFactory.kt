package com.example.adoptify.utils

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.adoptify.model.AdoptifyRepository
import com.example.adoptify.ui.login.LoginViewModel
import com.example.adoptify.ui.pet.PetViewModel
import com.example.adoptify.ui.register.RegisterViewModel
import com.example.adoptify.ui.shelter.ShelterViewModel

class ViewModelFactory(private val adoptRepository: AdoptifyRepository) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RegisterViewModel::class.java)) {
            return RegisterViewModel(adoptRepository) as T
        } else if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(adoptRepository) as T
        } else if (modelClass.isAssignableFrom(PetViewModel::class.java)) {
            return PetViewModel(adoptRepository) as T
        } else if (modelClass.isAssignableFrom(ShelterViewModel::class.java)) {
            return ShelterViewModel(adoptRepository) as T
        }
        throw IllegalArgumentException("Unknown View Model class : ${modelClass.name}")
    }

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null
        fun getInstance(context: Context): ViewModelFactory {
            return instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository(context))
            }.also { instance = it }
        }
    }
}