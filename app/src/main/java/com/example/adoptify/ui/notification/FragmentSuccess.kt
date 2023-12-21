package com.example.adoptify.ui.notification

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.adoptify.R
import com.example.adoptify.databinding.FragmentSuccessBinding
import com.example.adoptify.ui.pet.PetFragment


class FragmentSuccess : Fragment() {

    private var _successFragment: FragmentSuccessBinding? = null

    private val successFragment get() = _successFragment!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _successFragment = FragmentSuccessBinding.inflate(inflater, container, false)
        val view = successFragment.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        successFragment.btnDone.setOnClickListener {
            startActivity(Intent(requireActivity(), PetFragment::class.java))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _successFragment = null
    }

}