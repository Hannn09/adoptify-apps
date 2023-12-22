package com.example.adoptify.ui.shelter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.adoptify.databinding.ItemShelterBinding
import com.example.adoptify.service.response.DataShelter

class ShelterAdapter(private val listShelter: List<DataShelter>) : RecyclerView.Adapter<ShelterAdapter.ListViewHolder>() {
    class ListViewHolder(private val itemShelter: ItemShelterBinding) : RecyclerView.ViewHolder(itemShelter.root) {
        fun bind(item: DataShelter) {
            itemShelter.apply {
                Glide.with(itemView.context)
                    .load("https://storage.googleapis.com/adoptify-bucket/Database/image/" + item.gambar)
                    .into(imgShelter)
                namePetHouse.text = item.namaShelter.split(" ").joinToString(" ") { it.capitalize() }
                descPet.text = item.deskripsi.split(" ").joinToString(" ") { it.capitalize() }
                distance.text = item.kategori.split(" ").joinToString(" ") { it.capitalize() }
                rating.text = item.rating
                rate.text = item.rating
                nameShelter.text = item.namaShelter.split(" ").joinToString(" ") { it.capitalize() }
                subHeadline.text = item.alamat.split(" ").joinToString(" ") { it.capitalize() }
                shelterContent.text = item.deskripsi.split(" ").joinToString(" ") { it.capitalize() }

                btnMaps.setOnClickListener {
                    val googleMapsUrl = item.linkGmaps
                    val gmmIntentUri = Uri.parse(googleMapsUrl)
                    val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                    mapIntent.setPackage("com.google.android.apps.maps")

                    itemView.context.startActivity(mapIntent)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val itemShelter = ItemShelterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ShelterAdapter.ListViewHolder(itemShelter)
    }

    override fun getItemCount(): Int = listShelter.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listShelter[position])
    }
}