package com.example.adoptify.ui.donation

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.adoptify.R
import com.example.adoptify.databinding.FragmentDonationDetailBinding
import com.example.adoptify.databinding.FragmentDonationNominalBinding
import com.example.adoptify.model.ImageItem
import com.example.adoptify.ui.donation.nominal.NominalDonation
import java.util.UUID

class DonationDetail : AppCompatActivity(), View.OnClickListener {

    private var _donationNominalFragment: FragmentDonationNominalBinding? = null
    private val donationNominalFragment get() = _donationNominalFragment!!

    private lateinit var viewPager2: ViewPager2
        private lateinit var pageChangeListener: ViewPager2.OnPageChangeCallback

        private val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        ).apply {
            setMargins(8,0,8,0)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_donation_detail)

        val btnDonationNominal: Button = findViewById(R.id.btnNominal)
        btnDonationNominal.setOnClickListener(this)

        val viewpager2 = findViewById<ViewPager2>(R.id.viewpager2)

        val imageList = arrayListOf(
            ImageItem(
                UUID.randomUUID().toString(),
                "android.resource://" + packageName + "/" + R.drawable.image1
                ),
            ImageItem(
                UUID.randomUUID().toString(),
                "android.resource://" + packageName + "/" + R.drawable.image2
                ),
            )

        val imageAdapter = ImageAdapter()
        viewpager2.adapter = imageAdapter
        imageAdapter.submitList(imageList)

        val slideDotLL = findViewById<LinearLayout>(R.id.slideDotLL)
        val dotsImage = Array(imageList.size) { ImageView(this) }

        dotsImage.forEach {
            it.setImageResource(
                R.drawable.non_active_dot
            )
            slideDotLL.addView(it,params)
        }

        // default first dot selected
        dotsImage[0].setImageResource(R.drawable.active_dot)

        pageChangeListener = object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                dotsImage.mapIndexed { index, imageView ->
                    if (position == index){
                        imageView.setImageResource(
                            R.drawable.active_dot
                        )
                    }else{
                        imageView.setImageResource(R.drawable.non_active_dot)
                    }
                }
                super.onPageSelected(position)
            }
        }
        viewpager2.registerOnPageChangeCallback(pageChangeListener)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnNominal -> {
                val moveIntent = Intent(this@DonationDetail, NominalDonation::class.java)
                startActivity(moveIntent)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewPager2.unregisterOnPageChangeCallback(pageChangeListener)
    }
}