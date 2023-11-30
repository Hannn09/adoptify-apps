package com.example.adoptify.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.adoptify.databinding.ItemCarouselBinding
import com.example.adoptify.model.ItemBanner

class CaraouselAdapter(private val caraouselDataList: List<ItemBanner>) : RecyclerView.Adapter<CaraouselAdapter.CaraouselItemViewHolder>() {
    class CaraouselItemViewHolder(private val itemCarouselBinding: ItemCarouselBinding) : RecyclerView.ViewHolder(itemCarouselBinding.root) {
        fun bind(item: ItemBanner) {
            itemCarouselBinding.apply {
                petHouse.setImageResource(item.image)
                namePetHouse.text = item.shelterName
                descPet.text = item.descShelter
                distance.text = item.distanceShelter
                rating.text = item.ratingShelter
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