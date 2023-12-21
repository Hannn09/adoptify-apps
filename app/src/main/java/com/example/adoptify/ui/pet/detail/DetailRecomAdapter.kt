package com.example.adoptify.ui.pet.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.adoptify.databinding.CardItemReccomendationBinding
import com.example.adoptify.service.response.DataItem

class DetailRecomAdapter(private val listRecommendation: List<DataItem>) : RecyclerView.Adapter<DetailRecomAdapter.ListViewHolder>() {
    class ListViewHolder(private val binding: CardItemReccomendationBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(pet: DataItem) {
            binding.apply {
                Glide.with(itemView.context)
                    .load("https://storage.googleapis.com/adoptify-bucket/Database/image/" + pet.gambar)
                    .into(dummyPet)
                namePet.text = pet.nama.split(" ").joinToString(" ") { it.capitalize() }
                petRas.text = pet.ras.split(" ").joinToString(" ") { it.capitalize() }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val cardItemReccomendationBinding = CardItemReccomendationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(cardItemReccomendationBinding)
    }

    override fun getItemCount(): Int = listRecommendation.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listRecommendation[position])
    }
}