package com.example.retrofittest.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.retrofittest.databinding.ItemRvPostBinding
import com.example.retrofittest.model.Plant

class PlantAdapter(val plants: List<Plant>) : RecyclerView.Adapter<PlantAdapter.viewHolder>() {
    class viewHolder(val binding: ItemRvPostBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(plant: Plant) {
            with(binding) {
                Glide.with(binding.root)
                    .load(plant.imageUrl)
                    .into(ivPost)

                tvPost.text = plant.name
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val binding = ItemRvPostBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return viewHolder(binding)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        holder.bind(plants[position])
    }

    override fun getItemCount() = plants.size
}