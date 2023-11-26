package com.example.adoptify.ui.about

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.annotation.StringRes
import androidx.viewpager2.widget.ViewPager2
import com.example.adoptify.R
import com.example.adoptify.databinding.ActivityAboutBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class AboutActivity : AppCompatActivity() {

    private lateinit var aboutActivity: ActivityAboutBinding
    private var indicatorWidth: Int = 0
    lateinit var indicator: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        aboutActivity = ActivityAboutBinding.inflate(layoutInflater)
        setContentView(aboutActivity.root)

        val sectionsPagerAdapter = SectionsPagerAdapter(this)
        val viewPager: ViewPager2 = aboutActivity.viewPager
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = aboutActivity.header.tabs
        TabLayoutMediator(tabs, viewPager) { tabs, position ->
            tabs.text = resources.getString(TAB_TITLES[position])
        }.attach()

        indicator = aboutActivity.header.indicator

        tabs.post {
            indicatorWidth = tabs.width / tabs.tabCount

            val indicatorParams = indicator.layoutParams as FrameLayout.LayoutParams
            indicatorParams.width = indicatorWidth
            indicator.layoutParams = indicatorParams
        }

        val leftMarginDp = 5

        val density = resources.displayMetrics.density
        val leftMarginPixels = (leftMarginDp * density).toInt()

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

                indicator.layoutParams = (indicator.layoutParams as FrameLayout.LayoutParams).apply {
                    leftMargin = ((positionOffset + position) * indicatorWidth).toInt() + leftMarginPixels
                }
            }

            override fun onPageSelected(position: Int) = Unit

            override fun onPageScrollStateChanged(state: Int) = Unit
        })

        supportActionBar?.elevation = 0f

    }

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_text_1,
            R.string.tab_text_2
        )
    }
}