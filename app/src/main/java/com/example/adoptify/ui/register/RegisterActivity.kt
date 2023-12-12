package com.example.adoptify.ui.register

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.adoptify.R
import com.example.adoptify.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var registerBinding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerBinding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(registerBinding.root)

        registerBinding.icArrow.setOnClickListener {
            onBackPressed()
        }
    }
}