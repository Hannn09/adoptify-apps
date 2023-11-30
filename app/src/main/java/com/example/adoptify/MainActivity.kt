package com.example.adoptify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.get
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.adoptify.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        val navController = findNavController(R.id.fragment)

        val navView: BottomNavigationView = activityMainBinding.navView


        navView.setupWithNavController(navController)

        activityMainBinding.navView.itemIconTintList = null

        activityMainBinding.fabBtn.setOnClickListener {
            navController.navigate(R.id.petFragment)
        }
    }
}