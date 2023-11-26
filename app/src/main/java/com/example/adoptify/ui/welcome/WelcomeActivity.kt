package com.example.adoptify.ui.welcome

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.example.adoptify.R
import com.example.adoptify.databinding.ActivityWelcomeBinding

class WelcomeActivity : AppCompatActivity() {

    private lateinit var welcomeActivityBinding: ActivityWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        welcomeActivityBinding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(welcomeActivityBinding.root)

        val dotsIndicator = welcomeActivityBinding.dotsIndicator
        val dotsPagerAdapter = DotsPagerAdapter(this)
        val viewPager: ViewPager2 = welcomeActivityBinding.welcomeViewPager
        viewPager.adapter = dotsPagerAdapter
        dotsIndicator.attachTo(viewPager)
    }
}