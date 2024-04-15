package com.example.retrofittest.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.retrofittest.databinding.ItemRvImageBinding
import com.example.retrofittest.model.ImageDocument

class SearchImageAdapter(val dataSet: List<ImageDocument>) :
    RecyclerView.Adapter<SearchImageAdapter.ViewHolder>() {
    class ViewHolder(val binding: ItemRvImageBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(imageDocument: ImageDocument) {
            with(binding) {
                Glide.with(binding.root)
                    .load(imageDocument.image_url)
                    .into(ivImage)

                tvCollection.text = imageDocument.collection
                tvDisplaySitename.text = imageDocument.display_sitename
                tvDatetime.text = imageDocument.datetime
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ViewHolder {
        val binding = ItemRvImageBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            bind(dataSet[position])
        }
    }

    override fun getItemCount() = dataSet.size
}