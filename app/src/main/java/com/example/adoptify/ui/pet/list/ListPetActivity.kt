package com.example.adoptify.ui.pet.list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.adoptify.R
import com.example.adoptify.databinding.ActivityListPetBinding
import com.example.adoptify.ui.pet.PetViewModel
import com.example.adoptify.utils.ViewModelFactory
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.example.adoptify.ui.home.HomeFragment.Companion.CATEGORY

class ListPetActivity : AppCompatActivity() {

    private lateinit var activityListPetBinding: ActivityListPetBinding
    private val petViewModel by viewModels<PetViewModel> { ViewModelFactory.getInstance(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityListPetBinding = ActivityListPetBinding.inflate(layoutInflater)
        setContentView(activityListPetBinding.root)

        var btnShowFilter = activityListPetBinding.header.btnFilter

        btnShowFilter.setOnClickListener {
            val dialog = BottomSheetDialog(this)

            val view = layoutInflater.inflate(R.layout.bottom_sheet_dialog_filter, null)

            val btnApply = view.findViewById<Button>(R.id.btn_apply)

            btnApply.setOnClickListener {
                dialog.dismiss()
            }

            dialog.setCancelable(false)

            dialog.setContentView(view)

            dialog.show()
        }

        val category = intent.getStringExtra(CATEGORY).toString()
        petViewModel.getPet(category)

        showRecyclerListPet()
        petViewModel.listPet.observe(this) {
            activityListPetBinding.rvPet.adapter = ListPetAdapter(it)
        }

        petViewModel.isLoading.observe(this) {
            showLoading(it)
        }

        activityListPetBinding.header.icArrowBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun showRecyclerListPet() {
        activityListPetBinding.rvPet.apply {
            layoutManager = LinearLayoutManager(this@ListPetActivity)
            setHasFixedSize(true)
        }
    }

    private fun showLoading(isLoading: Boolean) {
        activityListPetBinding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }


}