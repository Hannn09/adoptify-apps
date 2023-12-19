package com.example.adoptify.utils

import android.content.Context
import com.example.adoptify.model.AdoptifyRepository
import com.example.adoptify.service.api.ApiConfig

object Injection {
    fun provideRepository(context: Context): AdoptifyRepository {
        val apiService = ApiConfig.getApiService()
        val pref = SettingsPreferences.getInstance(context.dataStore)
        return  AdoptifyRepository.getInstance(apiService, pref)
    }
}