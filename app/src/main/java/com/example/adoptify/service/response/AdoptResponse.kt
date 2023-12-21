package com.example.adoptify.service.response

import com.google.gson.annotations.SerializedName

data class AdoptResponse(
	@SerializedName("msg")
	val msg: String,

	@SerializedName("kartuIdentitas")
	val kartuIdentitasUrl: String,

	@SerializedName("bukyiTransfer")
	val buktiTransferUrl: String,

	@SerializedName("status")
	val status: Int
)

