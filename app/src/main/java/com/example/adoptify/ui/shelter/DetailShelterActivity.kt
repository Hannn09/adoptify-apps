package com.example.adoptify.ui.shelter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.adoptify.R
import com.example.adoptify.databinding.ActivityDetailShelterBinding
import com.example.adoptify.utils.ViewModelFactory

class DetailShelterActivity : AppCompatActivity() {

    private lateinit var detailShelterBinding: ActivityDetailShelterBinding

    private val shelterViewModel by viewModels<ShelterViewModel> { ViewModelFactory.getInstance(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailShelterBinding = ActivityDetailShelterBinding.inflate(layoutInflater)
        setContentView(detailShelterBinding.root)

        shelterViewModel.getShelter()



        detailShelterBinding.apply {
            header.icArrowBack.setOnClickListener {
                onBackPressed()
            }

            showRecylerShelter()
            shelterViewModel.shelterResponse.observe(this@DetailShelterActivity) {
                rvPet.adapter = ShelterAdapter(it)
            }
            showLoading()
        }


    }

    private fun showLoading() {
        shelterViewModel.isLoading.observe(this) {
            detailShelterBinding.progressBar.visibility = if (it) View.VISIBLE else View.GONE
        }
    }

    private fun showRecylerShelter() {
        detailShelterBinding.rvPet.apply {
            layoutManager = LinearLayoutManager(this@DetailShelterActivity)
            setHasFixedSize(true)
        }
    }
}