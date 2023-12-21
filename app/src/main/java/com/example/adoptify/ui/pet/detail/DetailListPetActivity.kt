package com.example.adoptify.ui.pet.detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.adoptify.databinding.ActivityDetailListPetBinding
import com.example.adoptify.service.response.DataItem
import com.example.adoptify.ui.pet.PetViewModel
import com.example.adoptify.ui.tnc.TermsnConditionActivity
import com.example.adoptify.utils.ViewModelFactory

class DetailListPetActivity : AppCompatActivity() {

    private lateinit var detailListPetBinding: ActivityDetailListPetBinding
    private val petViewModel by viewModels<PetViewModel> { ViewModelFactory.getInstance(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailListPetBinding = ActivityDetailListPetBinding.inflate(layoutInflater)
        setContentView(detailListPetBinding.root)

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


        showRecommendationList()
        petViewModel.recommendationPet.observe(this) {
            detailListPetBinding.rvReccomendation.adapter = DetailRecomAdapter(it)
        }

        detailListPetBinding.apply {
            icArrow.setOnClickListener {
                onBackPressed()
            }

            footer.btnAdopt.setOnClickListener {
                val intent = Intent(this@DetailListPetActivity, TermsnConditionActivity::class.java)
                intent.putExtra(EXTRA_USER, detail)
                startActivity(intent)
            }

           radio.setOnCheckedChangeListener { group, checkedId ->
               val radioButton: RadioButton = findViewById(checkedId)
               val selectedValue: String = radioButton.text.toString()

               petViewModel.getRecommendationPet(detail, selectedValue)
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
            informationPet.genderPet.text =
                data.jenisKelamin?.split(" ")?.joinToString(" ") { it.capitalize() }
            informationPet.rasPet.text = data.ras.split(" ").joinToString(" ") { it.capitalize() }
            fullDescPet.text = data.deskripsi.split(" ").joinToString(" ") { it.capitalize() }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        detailListPetBinding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun showRecommendationList() {
        detailListPetBinding.rvReccomendation.apply {
            layoutManager = LinearLayoutManager(this@DetailListPetActivity, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
        }
    }

    companion object {
        const val EXTRA_USER = "extra_user"
        const val TAG = "DetailListPetActivity"
    }
}