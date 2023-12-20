package com.example.adoptify.ui.pet.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import com.bumptech.glide.Glide
import com.example.adoptify.R
import com.example.adoptify.databinding.ActivityDetailListPetBinding
import com.example.adoptify.service.response.DataItem
import com.example.adoptify.ui.pet.PetViewModel
import com.example.adoptify.utils.ViewModelFactory

class DetailListPetActivity : AppCompatActivity() {

    private lateinit var detailListPetBinding: ActivityDetailListPetBinding
    private val petViewModel by viewModels<PetViewModel> { ViewModelFactory.getInstance(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailListPetBinding = ActivityDetailListPetBinding.inflate(layoutInflater)
        setContentView(detailListPetBinding.root)

        detailListPetBinding.icArrow.setOnClickListener {
            onBackPressed()
        }

        val detail = intent.getIntExtra(EXTRA_USER, 1001)
        petViewModel.getDetailPet(detail)


        petViewModel.isLoading.observe(this) {
            showLoading(it)
        }

        petViewModel.detailPet.observe(this) { dataItem ->
            for (data in dataItem) {
                displayDetail(data)
            }
        }
    }

    private fun displayDetail(data: DataItem) {
        detailListPetBinding.apply {
            Glide.with(this@DetailListPetActivity)
                .load("https://storage.googleapis.com/adoptify-bucket/Database/image/" + data.gambar)
                .into(imagePet)

            namePet.text = data.nama.split(" ").joinToString(" ") { it.capitalize() }
            nameLocation.text = data.alamat.split(" ").joinToString(" ") { it.capitalize() }
            informationPet.agePet.text = data.usia.split(" ").joinToString(" ") { it.capitalize() }
            informationPet.genderPet.text = data.jenisKelamin?.split(" ")?.joinToString(" ") { it.capitalize() }
            informationPet.rasPet.text = data.ras.split(" ").joinToString(" ") { it.capitalize() }
            fullDescPet.text = data.deskripsi.split(" ").joinToString(" ") { it.capitalize() }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        detailListPetBinding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    companion object {
        const val EXTRA_USER = "extra_user"
        const val TAG = "DetailListPetActivity"
    }
}