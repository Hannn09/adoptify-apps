package com.example.adoptify.ui.donation.nominal

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.adoptify.R
import com.example.adoptify.databinding.FragmentKonfirmationDonationBinding
import com.example.adoptify.ui.donation.ConfirmationDonation


class NominalDonation: AppCompatActivity(), View.OnClickListener {

    private var _donationKonfirmationFragment: FragmentKonfirmationDonationBinding? = null
    private val donationKonfirmationFragment get() = _donationKonfirmationFragment!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_donation_nominal)

        val btnDonationNominal: Button = findViewById(R.id.btnFixDonasi)
        btnDonationNominal.setOnClickListener(this)
    }
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnFixDonasi -> {
                val moveIntent = Intent(this@NominalDonation, ConfirmationDonation::class.java)
                startActivity(moveIntent)
            }
        }
    }

}