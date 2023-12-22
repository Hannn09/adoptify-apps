package com.example.adoptify.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.adoptify.databinding.ItemCarouselBinding
import com.example.adoptify.model.ItemBanner
import com.example.adoptify.service.response.DataShelter

class CaraouselAdapter(private val caraouselDataList: List<DataShelter>) : RecyclerView.Adapter<CaraouselAdapter.CaraouselItemViewHolder>() {
    class CaraouselItemViewHolder(private val itemCarouselBinding: ItemCarouselBinding) : RecyclerView.ViewHolder(itemCarouselBinding.root) {
        fun bind(item: DataShelter) {
            itemCarouselBinding.apply {
                Glide.with(itemView.context)
                    .load("https://storage.googleapis.com/adoptify-bucket/Database/image/" + item.gambar)
                    .into(petHouse)
                namePetHouse.text = item.namaShelter.split(" ").joinToString(" ") { it.capitalize() }
                descPet.text = item.deskripsi.split(" ").joinToString(" ") { it.capitalize() }
                distance.text = item.kategori.split(" ").joinToString(" ") { it.capitalize() }
                rating.text = item.rating
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CaraouselItemViewHolder {
        val itemCarouselBinding =ItemCarouselBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CaraouselItemViewHolder(itemCarouselBinding)
    }

    override fun getItemCount(): Int = caraouselDataList.size

    override fun onBindViewHolder(holder: CaraouselItemViewHolder, position: Int) {
        holder.bind(caraouselDataList[position])
    }
}