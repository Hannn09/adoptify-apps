package com.example.adoptify.model

data class SessionModel(
    val token: String,
    val isLogin: Boolean = false
)