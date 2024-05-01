package com.example.retrofittest.presentation.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.retrofittest.data.model.VideoDocumentResponse
import com.example.retrofittest.databinding.ItemRvBinding

class SearchVideoAdapter(val dataSet: List<VideoDocumentResponse>) :
    RecyclerView.Adapter<SearchVideoAdapter.ViewHolder>() {
    class ViewHolder(val binding: ItemRvBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(videoDocumentResponse: VideoDocumentResponse) {
            with(binding) {
                Glide.with(binding.root)
                    .load(videoDocumentResponse.url)
                    .into(ivImage)

                tvCollection.text = videoDocumentResponse.title
                tvDisplaySitename.text = videoDocumentResponse.author
                tvDatetime.text = videoDocumentResponse.datetime
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