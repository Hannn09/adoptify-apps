package com.example.adoptify.ui.welcome.first

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.adoptify.R
import com.example.adoptify.databinding.FragmentFirstBinding


class FirstFragment : Fragment() {

    private var _firstFragemnt: FragmentFirstBinding? = null

    private val firstFragment get() = _firstFragemnt!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _firstFragemnt = FragmentFirstBinding.inflate(inflater, container, false)
        val view = firstFragment.root


        val btnNext = firstFragment.btnNext
        val viewPager = activity?.findViewById<ViewPager2>(R.id.welcomeViewPager)

        btnNext.setOnClickListener {
            viewPager?.currentItem = 1
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _firstFragemnt = null
    }

}