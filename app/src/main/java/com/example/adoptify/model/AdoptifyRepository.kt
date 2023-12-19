package com.example.adoptify.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import com.example.adoptify.service.api.ApiService
import com.example.adoptify.service.response.LoginResponse
import com.example.adoptify.service.response.RegisterResponse
import com.example.adoptify.utils.SettingsPreferences
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback
import kotlin.math.log


class AdoptifyRepository private constructor(
    private val apiService: ApiService,
    private val pref: SettingsPreferences,
) {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _loginResponse = MutableLiveData<LoginResponse>()
    val loginResponse: LiveData<LoginResponse> = _loginResponse

    private val _registerResponse = MutableLiveData<RegisterResponse>()
    val registerResponse: LiveData<RegisterResponse> = _registerResponse

    private val _isSuccess = MutableLiveData<Boolean>()
    val isSuccess: LiveData<Boolean> = _isSuccess

    fun register(name: String, email: String, password: String) {
        _isLoading.value = true
        val client = apiService.registerUser(name, email, password)

        client.enqueue(object : Callback<RegisterResponse> {
            override fun onResponse(
                call: Call<RegisterResponse>,
                response: Response<RegisterResponse>,
            ) {
                try {
                    _isLoading.value = false
                    if (response.isSuccessful && response.body() != null) {
                        _registerResponse.value = response.body()
                        _isSuccess.value = true
                    } else {
                        Log.d(TAG, "onResponse: $response")
                        _isSuccess.value = false
                    }
                } catch (e: Exception) {

                    Log.d(TAG, "onResponse: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                _isLoading.value = false

                Log.d(TAG, "onFailure: ${t.message.toString()}")
            }

        })
    }

    fun login(email: String, password: String) {
        _isLoading.value = true
        val client = apiService.loginUser(email, password)

        client.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                try {
                    _isLoading.value = false
                    if (response.isSuccessful && response.body() != null) {
                        _loginResponse.value = response.body()
                    } else {
                        Log.d(TAG, "onResponse: $response")
                    }
                } catch (e: Exception) {
                    Log.d(TAG, "onResponse: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                _isLoading.value = false
                Log.d(TAG, "onFailure: ${t.message.toString()}")
            }

        })
    }

    suspend fun saveSession(session: SessionModel) {
        pref.saveSession(session)
    }

    fun getSession(): LiveData<SessionModel> {
        return pref.getSession().asLiveData()
    }

    suspend fun logout() {
        pref.deleteToken()
    }


    companion object {
        private const val TAG = "AdoptifyRepository"

        @Volatile
        private var instance: AdoptifyRepository? = null
        fun getInstance(
            apiService: ApiService,
            pref: SettingsPreferences
        ): AdoptifyRepository = Companion.instance ?: synchronized(this) {
            instance ?: AdoptifyRepository(apiService, pref)
        }.also { instance = it }
    }
}
