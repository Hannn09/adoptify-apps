package com.example.adoptify.ui.donation.nominal

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.adoptify.R
import com.example.adoptify.model.Nominal

class NominalDonation: AppCompatActivity() {
    private lateinit var rvNominal: RecyclerView
    private val list = ArrayList<Nominal>()


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.item_nominal)

        rvNominal = findViewById(R.id.rv_Nominal)
        rvNominal.setHasFixedSize(true)

        list.addAll(getListNominal())
        showRecyclerList()
    }

    private fun showRecyclerList() {
        rvNominal.layoutManager = LinearLayoutManager(this)
        val listNominalAdapter = ListNominalAdapter(list)
        rvNominal.adapter = listNominalAdapter
    }

    private fun getListNominal(): ArrayList<Nominal> {
        val dataNominal = resources.getStringArray(R.array.data_nominal)
        val dataRibu = resources.getStringArray(R.array.data_ribu)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val listNominal = ArrayList<Nominal>()
        for (i in dataNominal.indices) {
            val Nominal = Nominal(dataNominal[i], dataDescription[i], dataRibu[i])
            listNominal.add(Nominal)
        }
        return listNominal
    }
}