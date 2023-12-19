package com.example.adoptify.ui.pet.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.adoptify.R
import com.example.adoptify.databinding.ActivityDetailListPetBinding

class DetailListPetActivity : AppCompatActivity() {

    private lateinit var detailListPetBinding: ActivityDetailListPetBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailListPetBinding = ActivityDetailListPetBinding.inflate(layoutInflater)
        setContentView(detailListPetBinding.root)

        detailListPetBinding.icArrow.setOnClickListener {
            onBackPressed()
        }
    }
}