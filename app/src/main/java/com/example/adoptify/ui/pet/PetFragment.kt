package com.example.adoptify.ui.pet

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.adoptify.R
import com.example.adoptify.databinding.FragmentPetBinding


class PetFragment : Fragment() {

    private var _petFragment: FragmentPetBinding? = null

    private val petFragment get() = _petFragment!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _petFragment = FragmentPetBinding.inflate(inflater, container, false)
        val view = petFragment.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _petFragment = null
    }

}