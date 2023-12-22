package com.example.adoptify.ui.donation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.adoptify.R
import com.example.adoptify.databinding.ActivityConfirmationDonationBinding
import com.example.adoptify.databinding.ActivityDonationBinding
import com.example.adoptify.databinding.FragmentDonationDetailBinding
import com.example.adoptify.ui.donation.DonationDetail
import com.example.adoptify.ui.home.CaraouselAdapter
import com.example.adoptify.ui.pet.PetViewModel
import com.example.adoptify.ui.shelter.ShelterViewModel
import com.example.adoptify.utils.ViewModelFactory

class Donation : AppCompatActivity() {


    private lateinit var donationBinding: ActivityDonationBinding

    private val shelterViewModel by viewModels<ShelterViewModel> { ViewModelFactory.getInstance(this) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        donationBinding = ActivityDonationBinding.inflate(layoutInflater)
        setContentView(donationBinding.root)


        donationBinding.donate.btnDonation.setOnClickListener {
            val moveIntent = Intent(this@Donation, DonationDetail::class.java)
            startActivity(moveIntent)
        }

        val viewPager: ViewPager2 = donationBinding.itemCaraousel
        val dotsPagerAdapter = donationBinding.dotsIndicator

        shelterViewModel.getShelter()

        viewPager.apply {
            clipChildren = false
            clipToPadding = false
            (getChildAt(0) as RecyclerView).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
            shelterViewModel.shelterResponse.observe(this@Donation) {
                val shelterList = shelterViewModel.shelterResponse.value ?: emptyList()

                adapter = CaraouselAdapter(shelterList.take(5))

                dotsPagerAdapter.attachTo(viewPager)
            }
        }

    }

}