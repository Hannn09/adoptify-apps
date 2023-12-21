package com.example.adoptify.service.response

import com.google.gson.annotations.SerializedName

data class ShelterResponse(
	@SerializedName("msg")
	val msg: String,

	@SerializedName("data")
	val data: List<DataShelter>,

	@SerializedName("status")
	val status: Int
)

data class DataShelter(
	@SerializedName("uid")
	val uid: Int,

	@SerializedName("rating")
	val rating: String,

	@SerializedName("kategori")
	val kategori: String,

	@SerializedName("nama_shelter")
	val namaShelter: String,

	@SerializedName("deskripsi")
	val deskripsi: String,

	@SerializedName("gambar")
	val gambar: String,

	@SerializedName("link_gmaps")
	val linkGmaps: String,

	@SerializedName("alamat")
	val alamat: String
)

