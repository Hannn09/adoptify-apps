package com.example.adoptify.ui.pet.list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.adoptify.R
import com.example.adoptify.databinding.ActivityListPetBinding
import com.google.android.material.bottomsheet.BottomSheetDialog

class ListPetActivity : AppCompatActivity() {

    private lateinit var activityListPetBinding: ActivityListPetBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityListPetBinding = ActivityListPetBinding.inflate(layoutInflater)
        setContentView(activityListPetBinding.root)

        var btnShowFilter = activityListPetBinding.header.btnFilter

        btnShowFilter.setOnClickListener {
            val dialog = BottomSheetDialog(this)

            val view = layoutInflater.inflate(R.layout.bottom_sheet_dialog_filter, null)

            val btnApply = view.findViewById<Button>(R.id.btn_apply)

            btnApply.setOnClickListener {
                dialog.dismiss()
            }

            dialog.setCancelable(false)

            dialog.setContentView(view)

            dialog.show()
        }
    }
}