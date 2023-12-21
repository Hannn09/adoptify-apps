package com.example.adoptify.ui.pet.list

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.adoptify.databinding.CardItemReccomendationBinding
import com.example.adoptify.service.response.DataAbandoned
import com.example.adoptify.ui.pet.detail.DetailListPetActivity

class AbandonedPetAdapter(private val listAbandoned: List<DataAbandoned>): RecyclerView.Adapter<AbandonedPetAdapter.ListViewHolder>() {
    class ListViewHolder(private val binding: CardItemReccomendationBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(pet: DataAbandoned) {
            binding.apply {
                Glide.with(itemView.context)
                    .load("https://storage.googleapis.com/adoptify-bucket/Database/image/" + pet.gambar)
                    .into(dummyPet)
                namePet.text = pet.jenis.split(" ").joinToString(" ") { it.capitalize() }
                petRas.text = pet.ras.split(" ").joinToString(" ") { it.capitalize() }

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailListPetActivity::class.java)
                    intent.putExtra(DetailListPetActivity.EXTRA_USER, pet.uid)
                    itemView.context.startActivity(intent)
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val itemReccomendationBinding = CardItemReccomendationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(itemReccomendationBinding)
    }

    override fun getItemCount(): Int = listAbandoned.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listAbandoned[position])
    }
}