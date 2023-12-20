package com.example.adoptify.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Nominal(
    val nominal: String,
    val ribu: String,
    val description: String
) : Parcelable
