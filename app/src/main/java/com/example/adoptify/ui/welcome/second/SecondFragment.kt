package com.example.adoptify.ui.welcome.second

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.adoptify.R
import com.example.adoptify.databinding.FragmentSecondBinding
import com.example.adoptify.ui.login.LoginActivity


class SecondFragment : Fragment() {

    private var _secondFragment: FragmentSecondBinding? = null

    private val secondFragment get() = _secondFragment!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _secondFragment = FragmentSecondBinding.inflate(inflater, container, false)
        val view = secondFragment.root

        val btnStart = secondFragment.btnStart
        val viewPager = activity?.findViewById<ViewPager2>(R.id.welcomeViewPager)

        btnStart.setOnClickListener {
            viewPager?.currentItem = 2
        }


        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _secondFragment = null
    }


}