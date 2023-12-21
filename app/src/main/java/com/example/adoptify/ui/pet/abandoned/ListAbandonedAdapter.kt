package com.example.adoptify.ui.pet.abandoned

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.adoptify.databinding.ItemListPetBinding
import com.example.adoptify.service.response.DataAbandoned
import com.example.adoptify.service.response.DataItem
import com.example.adoptify.ui.pet.detail.DetailListPetActivity
import com.example.adoptify.ui.pet.list.ListPetAdapter

class ListAbandonedAdapter(private val listPet: List<DataAbandoned>) : RecyclerView.Adapter<ListAbandonedAdapter.ListViewHolder>(){
    class ListViewHolder(private val binding: ItemListPetBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(pet: DataAbandoned) {
            binding.apply {
                Glide.with(itemView.context)
                    .load("https://storage.googleapis.com/adoptify-bucket/Database/image/" + pet.gambar)
                    .into(itemImage)
                petName.text = pet.jenis.split(" ").joinToString(" ") { it.capitalize() }
                petRas.text = pet.ras.split(" ").joinToString(" ") { it.capitalize() }
                petYears.text = pet.deskripsi.split(" ").joinToString(" ") { it.capitalize() }
                locationPet.text = pet.alamat.split(" ").joinToString(" ") { it.capitalize() }

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailListPetActivity::class.java)
                    intent.putExtra(DetailListPetActivity.EXTRA_USER, pet.uid)
                    itemView.context.startActivity(intent)
                }

            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val itemListPetBinding = ItemListPetBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(itemListPetBinding)
    }

    override fun getItemCount(): Int = listPet.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listPet[position])
    }


}