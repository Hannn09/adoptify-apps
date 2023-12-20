package com.example.adoptify.ui.donation.nominal

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.adoptify.R
import com.example.adoptify.model.Nominal

class ListNominalAdapter(private val listNominal: ArrayList<Nominal>) : RecyclerView.Adapter<ListNominalAdapter.ListViewHolder>() {
    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNominal: TextView = itemView.findViewById(R.id.choose_nominal)
        val tvRibu: TextView = itemView.findViewById(R.id.ribu)
        val tvDescription: TextView = itemView.findViewById(R.id.tvDescription)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_nominal, parent, false)
        return ListViewHolder(view)    }

    override fun getItemCount(): Int = listNominal.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (nominal, ribu, description) = listNominal[position]
        holder.tvNominal.text = nominal
        holder.tvRibu.text = ribu
        holder.tvDescription.text = description    }
}