package com.example.retrofittest.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.retrofittest.databinding.ItemRvBinding
import com.example.retrofittest.domain.model.ImageDocumentEntity

class SearchImageAdapter(val dataSet: List<ImageDocumentEntity>) :
    RecyclerView.Adapter<SearchImageAdapter.ViewHolder>() {
    class ViewHolder(val binding: ItemRvBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(imageDocumentEntity: ImageDocumentEntity) {
            with(binding) {
                Glide.with(binding.root)
                    .load(imageDocumentEntity.image_url)
                    .into(ivImage)

                tvCollection.text = imageDocumentEntity.collection
                tvDisplaySitename.text = imageDocumentEntity.display_sitename
                tvDatetime.text = imageDocumentEntity.datetime
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ViewHolder {
        val binding = ItemRvBinding.inflate(
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