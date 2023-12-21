package com.example.adoptify.ui.form

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import com.example.adoptify.ui.pet.detail.DetailListPetActivity.Companion.EXTRA_USER
import com.example.adoptify.databinding.ActivityFormBinding
import com.example.adoptify.ui.notification.FragmentError
import com.example.adoptify.ui.notification.FragmentSuccess
import com.example.adoptify.ui.pet.PetViewModel
import com.example.adoptify.utils.ViewModelFactory
import com.example.storyapp.utils.reduceImageFile
import com.example.storyapp.utils.uriToFile

class FormActivity : AppCompatActivity() {

    private lateinit var activityFormBinding: ActivityFormBinding
    private var currentImageUri: Uri? = null
    private val petViewModel by viewModels<PetViewModel> { ViewModelFactory.getInstance(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityFormBinding = ActivityFormBinding.inflate(layoutInflater)
        setContentView(activityFormBinding.root)


        val petId = intent.getIntExtra(EXTRA_USER, 1001)

        activityFormBinding.apply {

            val edtName= activityFormBinding.nameEditText
            val edtEmail = activityFormBinding.emailEditText
            val edtAddress = activityFormBinding.addressEditText
            val edtProv = activityFormBinding.provinsiEditText
            val edtPost = activityFormBinding.PostCodeEditText

            PetIdEditText.setText(petId.toString())
            PetIdEditText.isEnabled = false

            btnRemoveData.setOnClickListener {
                clearTextInput()
            }

            icArrow.setOnClickListener {
                onBackPressed()
            }

            btnAddIdCard.setOnClickListener {
                startGallery()
            }

            btnAddTransaction.setOnClickListener {
                startGallery()
            }

            btnSave.setOnClickListener {

                if (edtName.length() != 0 && edtEmail.length() != 0 && edtAddress.length() != 0 && edtProv.length() != 0 && edtPost.length() != 0) {
                    showLoading()
                    insertAdopt()
                    moveToSuccess()
                } else {
                    Toast.makeText(this@FormActivity, "Fill All Field!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


    private fun clearTextInput() {
        activityFormBinding.apply {
            nameEditText.text?.clear()
            emailEditText.text?.clear()
            addressEditText.text?.clear()
            provinsiEditText.text?.clear()
            PostCodeEditText.text?.clear()
        }
    }

    private val launcherGallery = registerForActivityResult(
        ActivityResultContracts.PickVisualMedia()
    ) { uri: Uri? ->
        if (uri != null) {
            currentImageUri = uri
            showImageIdCard()
            showImageTransaction()
        } else {
            Log.d("Photo Picker", "No media selected")
        }
    }

    private fun startGallery() {
        launcherGallery.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
    }

    private fun showImageIdCard() {
        currentImageUri?.let { uri ->
            val fileName = uri.lastPathSegment
            Log.d("Image URI", "showImageIdCard: $fileName")
            activityFormBinding.btnAddIdCard.text = fileName
        }
    }

    private fun showImageTransaction() {
        currentImageUri?.let { uri ->
            val fileName = uri.lastPathSegment
            Log.d("Image URI", "showImageTransaction: $fileName")
            activityFormBinding.btnAddTransaction.text = fileName
        }
    }

    private fun insertAdopt() {
        val edtName= activityFormBinding.nameEditText.text.toString()
        val edtEmail = activityFormBinding.emailEditText.text.toString()
        val edtAddress = activityFormBinding.addressEditText.text.toString()
        val edtProv = activityFormBinding.provinsiEditText.text.toString()
        val edtPost = activityFormBinding.PostCodeEditText.text.toString()
        val edtPetId = activityFormBinding.PetIdEditText.text.toString().toInt()

        currentImageUri?.let { uri ->
            val imageKartu = uriToFile(uri, this).reduceImageFile()
            val imageBukti = uriToFile(uri, this).reduceImageFile()

            petViewModel.insertAdopt(edtName, edtEmail, edtAddress,edtProv, edtPost, imageKartu, imageBukti, edtPetId)
        }

    }

    private fun showLoading() {
        petViewModel.isLoading.observe(this) {
            activityFormBinding.progressBar.visibility = if (it) View.VISIBLE else View.GONE

        }
    }

    private fun moveToSuccess() {
        petViewModel.adoptResponse.observe(this) {
            if (it.status == 200) {
                startActivity(Intent(this@FormActivity, FragmentSuccess::class.java))
                finish()
            } else {
                startActivity(Intent(this@FormActivity, FragmentError::class.java))
                finish()
            }
        }

    }
}