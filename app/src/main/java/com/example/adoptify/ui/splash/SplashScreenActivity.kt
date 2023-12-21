package com.example.adoptify.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.activity.viewModels
import com.example.adoptify.MainActivity
import com.example.adoptify.R
import com.example.adoptify.ui.login.LoginActivity
import com.example.adoptify.ui.login.LoginViewModel
import com.example.adoptify.ui.welcome.WelcomeActivity
import com.example.adoptify.utils.ViewModelFactory

class SplashScreenActivity : AppCompatActivity() {

    private val SPLASH_TIME: Long = 3000
    private var token = ""
    private val loginViewModel by viewModels<LoginViewModel> { ViewModelFactory.getInstance(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        @Suppress("DEPRECATION")
        Handler().postDelayed({
           checkToken()
        }, SPLASH_TIME)
    }

    private fun checkToken() {
        loginViewModel.getSession().observe(this) {
            token = it.token
            if (!it.isLogin) {
                startActivity(Intent(this, WelcomeActivity::class.java))
                finish()
            } else {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }
    }
}