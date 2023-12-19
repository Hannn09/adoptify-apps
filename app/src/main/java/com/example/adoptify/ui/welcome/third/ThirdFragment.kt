package com.example.adoptify.ui.welcome.third

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.adoptify.R
import com.example.adoptify.databinding.FragmentThirdBinding


class ThirdFragment : Fragment() {

    private var _thirdFragment: FragmentThirdBinding? = null

    private val thirdFragment get() = _thirdFragment!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _thirdFragment = FragmentThirdBinding.inflate(inflater, container, false)
        val view = thirdFragment.root

        val btnNext = thirdFragment.btnNext
        val viewPager = activity?.findViewById<ViewPager2>(R.id.welcomeViewPager)

        btnNext.setOnClickListener {
            viewPager?.currentItem = 3
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _thirdFragment = null
    }


}