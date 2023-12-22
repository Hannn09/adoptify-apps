package com.example.adoptify.ui.donation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.adoptify.R
import com.example.adoptify.databinding.FragmentDonationDetailBinding
import com.example.adoptify.ui.donation.DonationDetail

class Donation : AppCompatActivity(), View.OnClickListener {

    private var _donationDetailFragment: FragmentDonationDetailBinding? = null
    private val donationDetailFragment get() = _donationDetailFragment!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_donation)

        val btnDonationDetail: Button = findViewById(R.id.btnDonation)
        btnDonationDetail.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnDonation -> {
                val moveIntent = Intent(this@Donation, DonationDetail::class.java)
                startActivity(moveIntent)
            }
        }
    }
}