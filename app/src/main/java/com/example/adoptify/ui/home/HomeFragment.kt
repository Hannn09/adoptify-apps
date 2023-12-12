package com.example.adoptify.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.adoptify.R
import com.example.adoptify.databinding.FragmentHomeBinding
import com.example.adoptify.model.dummyBanner
import com.example.adoptify.ui.pet.list.ListPetActivity


class HomeFragment : Fragment() {

    private var _homeFragment: FragmentHomeBinding? = null

    private val homeFragment get() = _homeFragment!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _homeFragment = FragmentHomeBinding.inflate(inflater, container, false)
        val view = homeFragment.root

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewPager = homeFragment.itemCaraousel
        val dotsPagerAdapter = homeFragment.dotsIndicator

        viewPager.apply {
            clipChildren = false
            clipToPadding = false
            (getChildAt(0) as RecyclerView).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
            adapter = CaraouselAdapter(dummyBanner)
        }

        dotsPagerAdapter.attachTo(viewPager)

        val btnCat = homeFragment.btnCategories.btnCat

        btnCat.setOnClickListener {
            startActivity(Intent(requireActivity(), ListPetActivity::class.java))
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _homeFragment = null
    }


}