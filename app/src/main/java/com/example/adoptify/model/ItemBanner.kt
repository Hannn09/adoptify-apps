package com.example.adoptify.model

import android.os.Parcelable
import com.example.adoptify.R
import kotlinx.parcelize.Parcelize

@Parcelize
data class ItemBanner(
    val image: Int,
    val shelterName: String,
    val descShelter: String,
    val distanceShelter: String,
    val ratingShelter: String,
) : Parcelable

val dummyBanner = listOf(
    ItemBanner(R.drawable.caraousel_item, "Cat House CF", "Pusat penampungan kucing & lainnya", "200 meter", "5.0"),
    ItemBanner(R.drawable.caraousel_item, "Cat House CF", "Pusat penampungan kucing & lainnya", "200 meter", "5.0"),
    ItemBanner(R.drawable.caraousel_item, "Cat House CF", "Pusat penampungan kucing & lainnya", "200 meter", "5.0")
)

