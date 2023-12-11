package com.example.adoptify.ui.settings

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.adoptify.R
import com.example.adoptify.databinding.ActivitySettingsBinding
import com.example.adoptify.ui.about.AboutActivity

class SettingsActivity : AppCompatActivity() {

    private lateinit var activitySettingsBinding: ActivitySettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activitySettingsBinding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(activitySettingsBinding.root)

        activitySettingsBinding.itemGeneral.tvInformation.setOnClickListener {
            startActivity(Intent(this, AboutActivity::class.java))
        }
    }
}