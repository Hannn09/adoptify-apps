package com.example.adoptify.service.api

import com.example.adoptify.service.response.LoginResponse
import com.example.adoptify.service.response.PetResponse
import com.example.adoptify.service.response.RegisterResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @POST("register")
    fun registerUser(
        @Query("username") username: String,
        @Query("email") email: String,
        @Query("password") password: String
    ): Call<RegisterResponse>

    @POST("login")
    fun loginUser(
        @Query("email") email: String,
        @Query("password") password: String
    ) : Call<LoginResponse>

    @GET("pets-byType")
    fun getPetByType(
        @Query ("petType") petType: String
    ) : Call<PetResponse>
}