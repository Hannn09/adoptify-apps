package com.example.adoptify.ui.register

import android.content.Intent
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.example.adoptify.R
import com.example.adoptify.databinding.ActivityRegisterBinding
import com.example.adoptify.ui.login.LoginActivity
import com.example.adoptify.utils.ViewModelFactory

class RegisterActivity : AppCompatActivity() {

    private lateinit var registerBinding: ActivityRegisterBinding
    private val registerViewModel by viewModels<RegisterViewModel> {
        ViewModelFactory.getInstance(this)
    }

    private var mAlertDialog: AlertDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerBinding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(registerBinding.root)

        registerBinding.icArrow.setOnClickListener {
            onBackPressed()
        }

        val txtName = registerBinding.nameEditText
        val txtEmail = registerBinding.emailEditText
        val txtPassword = registerBinding.passwordEditText

        registerBinding.btnRegister.setOnClickListener {
            if (txtEmail.length() != 0 && txtName.length() != 0 && txtPassword.length() != 0)  {
                showLoading()
                registerUser()
                dialogResult()
            } else {
                Toast.makeText(this, "Fill it All Field!", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun showLoading() {
        registerViewModel.isLoading.observe(this) {
            if (it) {
                registerBinding.progressBar.visibility = View.VISIBLE
            } else {
                registerBinding.progressBar.visibility = View.GONE

            }
        }
    }

    private fun registerUser() {
        registerBinding.apply {
            registerViewModel.registerUser(nameEditText.text.toString(), emailEditText.text.toString(), passwordEditText.text.toString())
        }
    }

    private fun dialogResult() {
        registerViewModel.isShow.observe(this) { isSuccess ->
            if (isSuccess) {
                showDialog()
            } else {
                showDialogError()
            }
        }
    }

    private fun showDialog() {
        val builder = AlertDialog.Builder(this, R.style.CustomAlertDialog).create()
        val view = layoutInflater.inflate(R.layout.custom_alert_dialog, null)
        val button = view.findViewById<Button>(R.id.btnDismiss)
        builder.setView(view)
        button.setOnClickListener {
            builder.dismiss()
            navigateToLogin()
            mAlertDialog = null
        }
        builder.setCanceledOnTouchOutside(false)
        builder.show()
    }

    private fun showDialogError() {
        val builder = AlertDialog.Builder(this, R.style.CustomAlertDialog).create()
        val view = layoutInflater.inflate(R.layout.custom_error_alert, null)
        val button = view.findViewById<Button>(R.id.btnDismiss)
        builder.setView(view)
        button.setOnClickListener {
            builder.dismiss()
            mAlertDialog = null
        }
        builder.setCanceledOnTouchOutside(false)
        builder.show()
    }

    private fun navigateToLogin() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

}