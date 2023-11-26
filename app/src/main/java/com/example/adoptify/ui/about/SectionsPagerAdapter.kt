package com.example.adoptify.ui.about

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.adoptify.ui.about.app.AboutAppFragment
import com.example.adoptify.ui.about.developer.AboutDeveloperFragment

class SectionsPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when(position) {
            0 -> fragment = AboutAppFragment()
            1 -> fragment = AboutDeveloperFragment()
        }
        return fragment as Fragment
    }

}