package com.example.retrofittest.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.retrofittest.databinding.ItemRvBinding
import com.example.retrofittest.model.VideoDocument

class SearchVideoAdapter(val dataSet: List<VideoDocument>) :
    RecyclerView.Adapter<SearchVideoAdapter.ViewHolder>() {
    class ViewHolder(val binding: ItemRvBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(videoDocument: VideoDocument) {
            with(binding) {
                Glide.with(binding.root)
                    .load(videoDocument.url)
                    .into(ivImage)

                tvCollection.text = videoDocument.title
                tvDisplaySitename.text = videoDocument.author
                tvDatetime.text = videoDocument.datetime
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