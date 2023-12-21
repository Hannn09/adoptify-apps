package com.example.adoptify.service.api

import com.example.adoptify.service.response.AbandonedResponse
import com.example.adoptify.service.response.AdoptResponse
import com.example.adoptify.service.response.LoginResponse
import com.example.adoptify.service.response.PetResponse
import com.example.adoptify.service.response.RegisterResponse
import com.example.adoptify.service.response.ShelterResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
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

    @Multipart
    @POST("adopt")
    fun insertAdopt(
        @Part("nama") nama: RequestBody,
        @Part("email") email: RequestBody,
        @Part("alamat") alamat: RequestBody,
        @Part("prov") prov: RequestBody,
        @Part("pos") pos: RequestBody,
        @Part kartuIdentitas: MultipartBody.Part,
        @Part buktiTransfer: MultipartBody.Part,
        @Part("petId") petId: Int
    ) : Call<AdoptResponse>

    @GET("pet-recommendations")
    fun getPetReccomendation(
        @Query("petId") petId: Int,
        @Query("recomType") recomType: String,
    ) : Call<PetResponse>

    @GET("abandoned")
    fun getAbandonedPet(): Call<AbandonedResponse>

    @GET("shelter")
    fun getShelter(): Call<ShelterResponse>

    @GET("shelter-detail")
    fun getDetailShelter(
        @Query("shelterId") shelterId: Int
    ): Call<ShelterResponse>
}