package com.example.adoptify.ui.welcome

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.adoptify.ui.welcome.first.FirstFragment
import com.example.adoptify.ui.welcome.second.SecondFragment

class DotsPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when(position) {
            0 -> fragment = FirstFragment()
            1 -> fragment = SecondFragment()
        }

        return fragment as Fragment
    }

}