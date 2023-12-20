package com.example.adoptify.service.api

import com.example.adoptify.service.response.DataItem
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
    @FormUrlEncoded
    @POST("register")
    fun registerUser(
        @Field("username") username: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<RegisterResponse>

    @FormUrlEncoded
    @POST("login")
    fun loginUser(
        @Field("email") email: String,
        @Field("password") password: String
    ) : Call<LoginResponse>

    @GET("pets-byType")
    fun getPetByType(
        @Query ("petType") petType: String
    ) : Call<PetResponse>

    @GET("pet-detail")
    fun getDetailPet(
        @Query ("petId") petId: Int
    ) : Call<PetResponse>
}