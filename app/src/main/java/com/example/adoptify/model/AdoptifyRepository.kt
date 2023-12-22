package com.example.adoptify.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import com.example.adoptify.service.api.ApiService
import com.example.adoptify.service.response.AbandonedResponse
import com.example.adoptify.service.response.AdoptResponse
import com.example.adoptify.service.response.DataAbandoned
import com.example.adoptify.service.response.DataItem
import com.example.adoptify.service.response.DataShelter
import com.example.adoptify.service.response.LoginResponse
import com.example.adoptify.service.response.PetResponse
import com.example.adoptify.service.response.RegisterResponse
import com.example.adoptify.service.response.ShelterResponse
import com.example.adoptify.utils.SettingsPreferences
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback
import java.io.File


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

    private val _petResponse = MutableLiveData<List<DataItem>>()
    val petResponse: LiveData<List<DataItem>> = _petResponse

    private val _detailPetResponse = MutableLiveData<List<DataItem>>()
    val detailPetResponse: LiveData<List<DataItem>> = _detailPetResponse

    private val _recommendationPetResponse = MutableLiveData<List<DataItem>>()
    val recommendationPetResponse: LiveData<List<DataItem>> = _recommendationPetResponse

    private val _adoptResponse = MutableLiveData<AdoptResponse>()
    val adoptResponse: LiveData<AdoptResponse> = _adoptResponse

    private val _abandonedResponse = MutableLiveData<List<DataAbandoned>>()
    val abandonedResponse: LiveData<List<DataAbandoned>> = _abandonedResponse

    private val _shelterResponse = MutableLiveData<List<DataShelter>>()
    val shelterResponse: LiveData<List<DataShelter>> = _shelterResponse

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

    fun getListPet(type: String) {
        _isLoading.value = true
        val client = apiService.getPetByType(type)

        client.enqueue(object : Callback<PetResponse> {
            override fun onResponse(call: Call<PetResponse>, response: Response<PetResponse>) {
                try {
                    _isLoading.value = false
                    val listPet = response.body()?.data
                    if (response.isSuccessful && response.body() != null) {
                        _petResponse.value = listPet!!
                    } else {
                        Log.d(TAG, "onResponse: $response")
                    }
                } catch (e: Exception) {
                    Log.d(TAG, "onResponse: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<PetResponse>, t: Throwable) {
                _isLoading.value = false
                Log.d(TAG, "onFailure: ${t.message.toString()}")
            }

        })
    }

    fun getPetById(uid: Int) {
        _isLoading.value = true

        val client = apiService.getDetailPet(uid)

        client.enqueue(object : Callback<PetResponse> {
            override fun onResponse(call: Call<PetResponse>, response: Response<PetResponse>) {
                try {
                    _isLoading.value = false
                    val detail = response.body()?.data
                    if (response.isSuccessful && response.body() != null) {
                        _detailPetResponse.value = detail!!
                    } else {
                        Log.d(TAG, "onResponse: $response")
                    }
                } catch (e: Exception) {
                    Log.d(TAG, "onResponse: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<PetResponse>, t: Throwable) {
                _isLoading.value = false
                Log.d(TAG, "onFailure: ${t.message.toString()}")
            }

        })

    }

    fun getRecommendationPet(petId: Int, recomType: String) {
        _isLoading.value = true

        val client = apiService.getPetReccomendation(petId, recomType)

        client.enqueue(object : Callback<PetResponse> {
            override fun onResponse(call: Call<PetResponse>, response: Response<PetResponse>) {
                try {
                    _isLoading.value = false
                    val recommendation = response.body()?.data
                    if (response.isSuccessful && response.body() != null) {
                        _recommendationPetResponse.value = recommendation!!
                    } else {
                        Log.d(TAG, "onResponse: $response")
                    }
                } catch (e: Exception) {
                    Log.d(TAG, "onResponse: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<PetResponse>, t: Throwable) {
                _isLoading.value = false
                Log.d(TAG, "onFailure: ${t.message.toString()}")
            }

        })
    }

    fun processAdopt(
        name: String,
        email: String,
        address: String,
        prov: String,
        pos: String,
        kartuIdentitas: File,
        buktiTransfer: File,
        petId: Int,
    ) {
        _isLoading.value = true
        val requestName = name.toRequestBody("text/plain".toMediaType())
        val requestEmail = email.toRequestBody("text/plain".toMediaType())
        val requestAddress = address.toRequestBody("text/plain".toMediaType())
        val requestProv = prov.toRequestBody("text/plain".toMediaType())
        val requestPos = pos.toRequestBody("text/plain".toMediaType())
        val requestImageKartu = kartuIdentitas.asRequestBody("image/jpeg".toMediaType())
        val requestImageBukti = buktiTransfer.asRequestBody("image/jpeg".toMediaType())
        val multiPartBodyKartu = MultipartBody.Part.createFormData(
            "kartuIdentitas",
            kartuIdentitas.name,
            requestImageKartu
        )
        val multiPartBodyBukti = MultipartBody.Part.createFormData(
            "buktiTransfer",
            buktiTransfer.name,
            requestImageBukti
        )

        val client = apiService.insertAdopt(
            requestName,
            requestEmail,
            requestAddress,
            requestProv,
            requestPos,
            multiPartBodyKartu,
            multiPartBodyBukti,
            petId
        )

        client.enqueue(object : Callback<AdoptResponse> {
            override fun onResponse(call: Call<AdoptResponse>, response: Response<AdoptResponse>) {
                try {
                    _isLoading.value = false
                    if (response.isSuccessful && response.body() != null) {
                        _adoptResponse.value = response.body()
                    } else {
                        Log.d(TAG, "onResponse: $response")
                    }
                } catch (e: Exception) {
                    Log.d(TAG, "onResponse: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<AdoptResponse>, t: Throwable) {
                _isLoading.value = false
                Log.d(TAG, "onFailure: ${t.message.toString()}")
            }

        })
    }

    fun getAbandonedPet() {
        _isLoading.value = true

        val client = apiService.getAbandonedPet()

        client.enqueue(object: Callback<AbandonedResponse> {
            override fun onResponse(
                call: Call<AbandonedResponse>,
                response: Response<AbandonedResponse>,
            ) {
                try {
                  _isLoading.value = false
                    val abandonedPet = response.body()?.data
                  if (response.isSuccessful && response.body() != null) {
                      _abandonedResponse.value = abandonedPet!!
                  } else {
                      Log.d(TAG, "onResponse: $response")
                  }

                } catch (e: Exception) {
                    Log.d(TAG, "onResponse: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<AbandonedResponse>, t: Throwable) {
                _isLoading.value = false
                Log.d(TAG, "onFailure: ${t.message.toString()}")
            }

        })
    }

    fun getShelter() {
        _isLoading.value = true

        val client = apiService.getShelter()

        client.enqueue(object : Callback<ShelterResponse> {
            override fun onResponse(
                call: Call<ShelterResponse>,
                response: Response<ShelterResponse>,
            ) {
                try {
                    _isLoading.value = false
                    val shelter = response.body()?.data
                    if (response.isSuccessful && response.body() != null) {
                        _shelterResponse.value = shelter!!
                        Log.d(TAG, "data: $shelter")
                    } else {
                        Log.d(TAG, "onResponse: $response")
                    }
                } catch (e: Exception) {
                    Log.d(TAG, "onResponse: ${response.message()}")
                }

            }

            override fun onFailure(call: Call<ShelterResponse>, t: Throwable) {
                _isLoading.value = false
                Log.d(TAG, "onFailure: ${t.message.toString()}")
            }

        })
    }


    companion object {
        private const val TAG = "AdoptifyRepository"

        @Volatile
        private var instance: AdoptifyRepository? = null
        fun getInstance(
            apiService: ApiService,
            pref: SettingsPreferences,
        ): AdoptifyRepository = Companion.instance ?: synchronized(this) {
            instance ?: AdoptifyRepository(apiService, pref)
        }.also { instance = it }
    }
}
