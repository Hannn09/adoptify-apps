package com.example.adoptify.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.example.adoptify.MainActivity
import com.example.adoptify.R
import com.example.adoptify.databinding.ActivityLoginBinding
import com.example.adoptify.model.SessionModel
import com.example.adoptify.ui.home.HomeFragment
import com.example.adoptify.ui.register.RegisterActivity
import com.example.adoptify.utils.ViewModelFactory

class LoginActivity : AppCompatActivity() {

    private lateinit var activityLoginBinding: ActivityLoginBinding
    private val loginViewModel by viewModels<LoginViewModel> { ViewModelFactory.getInstance(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityLoginBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(activityLoginBinding.root)

        activityLoginBinding.apply {
            register.setOnClickListener {
                moveToRegister()
            }
            btnLogin.setOnClickListener {
                if (emailEditText.length() != 0 && passwordEditText.length() != 0) {
                    showLoading()
                    loginUser()
                    moveToMain()
                }
            }
        }

    }

    private fun moveToMain() {
        loginViewModel.loginResponse.observe(this) { response ->
            if (response.accessToken != null) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

    private fun moveToRegister() {
        startActivity(Intent(this, RegisterActivity::class.java))
    }

    private fun loginUser() {
        activityLoginBinding.apply {
            loginViewModel.login(emailEditText.text.toString(), passwordEditText.text.toString())
        }

        loginViewModel.loginResponse.observe(this) { response ->
            saveSession(
                SessionModel(
                    "bearer " + (response?.accessToken.toString()),
                    true
                )
            )
        }
    }

    private fun showLoading() {
        loginViewModel.isLoading.observe(this) {
            activityLoginBinding.progressBar.visibility = if (it) View.VISIBLE else View.GONE
        }
    }

    private fun saveSession(session: SessionModel) {
        loginViewModel.saveSession(session)
    }


}