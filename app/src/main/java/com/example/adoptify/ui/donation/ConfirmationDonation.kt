package com.example.adoptify.ui.donation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.adoptify.R

class ConfirmationDonation : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirmation_donation)

        val btnDonationSuccess: Button = findViewById(R.id.btnKonfirmasi)

        btnDonationSuccess.setOnClickListener {
            val moveIntent = Intent(this@ConfirmationDonation, DonationSuccess::class.java)
            startActivity(moveIntent)
        }

    }
}