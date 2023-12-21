package com.example.adoptify.ui.tnc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.adoptify.ui.form.FormActivity
import com.example.adoptify.databinding.ActivityTermsnConditionBinding
import com.example.adoptify.ui.pet.detail.DetailListPetActivity.Companion.EXTRA_USER

class TermsnConditionActivity : AppCompatActivity() {

    private lateinit var termsnConditionBinding: ActivityTermsnConditionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        termsnConditionBinding = ActivityTermsnConditionBinding.inflate(layoutInflater)
        setContentView(termsnConditionBinding.root)

        val petId = intent.getIntExtra(EXTRA_USER, 1001)
        Log.d("TermnsCondition", "id: $petId")

        termsnConditionBinding.btnNext.setOnClickListener {
            val intent = Intent(this, FormActivity::class.java)
            intent.putExtra(EXTRA_USER, petId)
            startActivity(intent)
        }
    }

}