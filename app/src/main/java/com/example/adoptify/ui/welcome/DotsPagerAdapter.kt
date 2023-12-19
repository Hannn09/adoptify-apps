package com.example.adoptify.ui.welcome

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.adoptify.ui.welcome.first.FirstFragment
import com.example.adoptify.ui.welcome.fourth.FourthFragment
import com.example.adoptify.ui.welcome.second.SecondFragment
import com.example.adoptify.ui.welcome.third.ThirdFragment

class DotsPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when(position) {
            0 -> fragment = FirstFragment()
            1 -> fragment = SecondFragment()
            2 -> fragment = ThirdFragment()
            3 -> fragment = FourthFragment()
        }

        return fragment as Fragment
    }

}