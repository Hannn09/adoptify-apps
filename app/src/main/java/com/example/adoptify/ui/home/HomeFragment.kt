package com.example.adoptify.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.example.adoptify.R
import com.example.adoptify.databinding.FragmentHomeBinding
import com.example.adoptify.model.dummyBanner
import com.example.adoptify.ui.login.LoginActivity
import com.example.adoptify.ui.login.LoginViewModel
import com.example.adoptify.ui.pet.list.ListPetActivity
import com.example.adoptify.ui.welcome.WelcomeActivity
import com.example.adoptify.utils.ViewModelFactory


class HomeFragment : Fragment() {

    private var _homeFragment: FragmentHomeBinding? = null

    private val homeFragment get() = _homeFragment!!

    private val loginViewModel by activityViewModels<LoginViewModel> { ViewModelFactory.getInstance(requireActivity()) }

    private var token = ""



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
        val btnDog = homeFragment.btnCategories.btnDog
        val btnTurtle = homeFragment.btnCategories.btnTurtle
        val btnBird = homeFragment.btnCategories.btnBird
        val btnGecko = homeFragment.btnCategories.btnGecko
        val btnSugarGlider = homeFragment.btnCategories.btnSugarGlider

//        btnCat.setOnClickListener {
//            val intent = Intent(requireActivity(), ListPetActivity::class.java)
//            intent.putExtra(CATEGORY, "kucing")
//            startActivity(intent)
//        }

        setCategoryButtonClickListener(btnCat, "kucing")
        setCategoryButtonClickListener(btnDog, "anjing")
        setCategoryButtonClickListener(btnTurtle, "kura-kura")
        setCategoryButtonClickListener(btnBird, "burung")
        setCategoryButtonClickListener(btnGecko, "gecko")
        setCategoryButtonClickListener(btnSugarGlider, "sugar glider")


        loginViewModel.getSession().observe(viewLifecycleOwner) {
            token = it.token
            if (!it.isLogin) {
                startActivity(Intent(requireActivity(), WelcomeActivity::class.java))
                activity?.finish()
            }
        }

        homeFragment.btnFilter.setOnClickListener {
            loginViewModel.logout()
            startActivity(Intent(requireActivity(), LoginActivity::class.java))
        }

    }

    fun setCategoryButtonClickListener(button: ImageView, category: String) {
        button.setOnClickListener {
            val intent = Intent(requireActivity(), ListPetActivity::class.java)
            intent.putExtra(CATEGORY, category)
            startActivity(intent)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _homeFragment = null
    }

    companion object {
        private const val USERNAME = "username"
        const val CATEGORY = "category"
        private const val TAG = "HomeFragment"
    }

}