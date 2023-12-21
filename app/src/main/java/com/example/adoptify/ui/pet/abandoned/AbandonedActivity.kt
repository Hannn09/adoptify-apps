package com.example.adoptify.ui.pet.abandoned

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.adoptify.R
import com.example.adoptify.databinding.ActivityAbandonedBinding
import com.example.adoptify.ui.pet.PetViewModel
import com.example.adoptify.utils.ViewModelFactory

class AbandonedActivity : AppCompatActivity() {

    private lateinit var abandonedBinding: ActivityAbandonedBinding

    private val petViewModel by viewModels<PetViewModel> { ViewModelFactory.getInstance(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        abandonedBinding = ActivityAbandonedBinding.inflate(layoutInflater)
        setContentView(abandonedBinding.root)

        petViewModel.getAbandonedPet()

        showLoading()
        showRecyclerListPet()
        petViewModel.abandonedResponse.observe(this) {
            abandonedBinding.rvPet.adapter = ListAbandonedAdapter(it)
        }

        abandonedBinding.header.icArrowBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun showRecyclerListPet() {
        abandonedBinding.rvPet.apply {
            layoutManager = LinearLayoutManager(this@AbandonedActivity)
            setHasFixedSize(true)
        }
    }

    private fun showLoading() {
        petViewModel.isLoading.observe(this) {
            abandonedBinding.progressBar.visibility = if (it) View.VISIBLE else View.GONE
        }
    }
}