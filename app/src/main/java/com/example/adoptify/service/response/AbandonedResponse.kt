package com.example.adoptify.service.response

import com.google.gson.annotations.SerializedName

data class AbandonedResponse(
	@SerializedName("msg")
	val msg: String,

	@SerializedName("data")
	val data: List<DataAbandoned>,

	@SerializedName("status")
	val status: Int
)

data class DataAbandoned(
	@SerializedName("uid")
	val uid: Int,

	@SerializedName("kontak")
	val kontak: String,

	@SerializedName("ras")
	val ras: String,

	@SerializedName("jenis")
	val jenis: String,

	@SerializedName("deskripsi")
	val deskripsi: String,

	@SerializedName("gambar")
	val gambar: String,

	@SerializedName("link_gmaps")
	val linkGmaps: String,

	@SerializedName("users")
	val users: String,

	@SerializedName("alamat")
	val alamat: String
)

