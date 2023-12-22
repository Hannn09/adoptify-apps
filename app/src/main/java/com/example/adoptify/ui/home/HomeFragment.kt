package com.example.adoptify.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.adoptify.databinding.FragmentHomeBinding
import com.example.adoptify.model.dummyBanner
import com.example.adoptify.ui.donation.Donation
import com.example.adoptify.ui.login.LoginActivity
import com.example.adoptify.ui.login.LoginViewModel
import com.example.adoptify.ui.pet.PetViewModel
import com.example.adoptify.ui.pet.abandoned.AbandonedActivity
import com.example.adoptify.ui.pet.list.AbandonedPetAdapter
import com.example.adoptify.ui.pet.list.ListPetActivity
import com.example.adoptify.ui.shelter.DetailShelterActivity
import com.example.adoptify.ui.shelter.ShelterViewModel
import com.example.adoptify.ui.welcome.WelcomeActivity
import com.example.adoptify.utils.ViewModelFactory


class HomeFragment : Fragment() {

    private var _homeFragment: FragmentHomeBinding? = null

    private val homeFragment get() = _homeFragment!!

    private val loginViewModel by activityViewModels<LoginViewModel> {
        ViewModelFactory.getInstance(
            requireActivity()
        )
    }

    private val petViewModel by activityViewModels<PetViewModel> {
        ViewModelFactory.getInstance(
            requireActivity()
        )
    }

    private val shelterViewModel by activityViewModels<ShelterViewModel> {
        ViewModelFactory.getInstance(
            requireActivity()
        )
    }

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

        val viewPager: ViewPager2 = homeFragment.itemCaraousel
        val dotsPagerAdapter = homeFragment.dotsIndicator

        shelterViewModel.getShelter()

        viewPager.apply {
            clipChildren = false
            clipToPadding = false
            (getChildAt(0) as RecyclerView).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
            shelterViewModel.shelterResponse.observe(viewLifecycleOwner) {
                val shelterList = shelterViewModel.shelterResponse.value ?: emptyList()

                adapter = CaraouselAdapter(shelterList.take(5))

                dotsPagerAdapter.attachTo(viewPager)
            }
        }

        val btnDonation = homeFragment.donation.btnDonation
        setDonationButtonClickListener(btnDonation, "donation")

        val btnCat = homeFragment.btnCategories.btnCat
        val btnDog = homeFragment.btnCategories.btnDog
        val btnTurtle = homeFragment.btnCategories.btnTurtle
        val btnBird = homeFragment.btnCategories.btnBird
        val btnGecko = homeFragment.btnCategories.btnGecko
        val btnSugarGlider = homeFragment.btnCategories.btnSugarGlider


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

        petViewModel.getAbandonedPet()

        showRecyclerList()
        petViewModel.abandonedResponse.observe(requireActivity()) {
            val abandonedList = petViewModel.abandonedResponse.value ?: emptyList()

            homeFragment.rvAbandoned.adapter = AbandonedPetAdapter(abandonedList.take(4))
        }

        homeFragment.btnAll.setOnClickListener {
            val intent = Intent(requireActivity(), AbandonedActivity::class.java)
            requireActivity().startActivity(intent)
        }

        homeFragment.icAll.setOnClickListener {
            val intent = Intent(requireActivity(), DetailShelterActivity::class.java)
            requireActivity().startActivity(intent)
        }

    }

    fun setDonationButtonClickListener(btnDonation: ImageView, donation: String) {
        btnDonation.setOnClickListener {
            val intent = Intent(requireActivity(), Donation::class.java)
            intent.putExtra(DONATION, donation)
            startActivity(intent)
        }
    }

    fun setCategoryButtonClickListener(button: ImageView, category: String) {
        button.setOnClickListener {
            val intent = Intent(requireActivity(), ListPetActivity::class.java)
            intent.putExtra(CATEGORY, category)
            startActivity(intent)
        }
    }

    private fun showRecyclerList() {
        homeFragment.rvAbandoned.apply {
            layoutManager =
                LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _homeFragment = null
    }

    companion object {
        private const val USERNAME = "username"
        const val CATEGORY = "category"
        const val DONATION = "donation"
        private const val TAG = "HomeFragment"
    }

}