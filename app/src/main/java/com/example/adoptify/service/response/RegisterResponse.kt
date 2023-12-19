package com.example.adoptify.service.response

import com.google.gson.annotations.SerializedName

data class RegisterResponse(
    @field:SerializedName("status")
    val status: Int?,

    @field:SerializedName("msg")
    val message: String? = null
)
