package com.example.adoptify.service.response

import com.google.gson.annotations.SerializedName

data class PetResponse(
	@SerializedName("msg")
	val msg: String,

	@SerializedName("data")
	val data: List<DataItem>,

	@SerializedName("status")
	val status: Int
)

data class DataItem(
	@SerializedName("usia")
	val usia: String,

	@SerializedName("kesehatan")
	val kesehatan: String,

	@SerializedName("ras")
	val ras: String,

	@SerializedName("gambar")
	val gambar: String,

	@SerializedName("ket_kesehatan")
	val ketKesehatan: String,

	@SerializedName("users")
	val users: String,

	@SerializedName("alamat")
	val alamat: String,

	@SerializedName("uid")
	val uid: Int,

	@SerializedName("kontak")
	val kontak: String,

	@SerializedName("nama")
	val nama: String,

	@SerializedName("jenis")
	val jenis: String,

	@SerializedName("deskripsi")
	val deskripsi: String,

	@SerializedName("jenis_kelamin")
	val jenisKelamin: String? = null
)

