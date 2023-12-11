package com.example.adoptify.ui.profile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.adoptify.R
import com.example.adoptify.databinding.FragmentProfileBinding
import com.example.adoptify.ui.settings.SettingsActivity


class ProfileFragment : Fragment() {

    private var _profileFragment : FragmentProfileBinding? = null

    private val profileFragment get() = _profileFragment!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _profileFragment = FragmentProfileBinding.inflate(inflater, container, false)
        val view = profileFragment.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        profileFragment.icSetting.setOnClickListener {
            startActivity(Intent(requireActivity(), SettingsActivity::class.java))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _profileFragment = null
    }

}