package com.example.adoptify.ui.welcome.fourth

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.adoptify.R
import com.example.adoptify.databinding.FragmentFourthBinding
import com.example.adoptify.ui.login.LoginActivity


class FourthFragment : Fragment() {

    private var _fourthFragment: FragmentFourthBinding? = null
    private val fourthFragment get() = _fourthFragment!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _fourthFragment = FragmentFourthBinding.inflate(inflater, container, false)
        val view = fourthFragment.root

        val btnStart = fourthFragment.btnNext

        btnStart.setOnClickListener {
            val intent = Intent(requireContext(), LoginActivity::class.java)
            requireContext().startActivity(intent)
        }

        return view
    }

}