package com.example.retrofittest.presentation.list

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.retrofittest.databinding.ItemRvImageBinding
import com.example.retrofittest.databinding.ItemRvVideoBinding

// ref: https://developer.android.com/reference/androidx/recyclerview/widget/DiffUtil.ItemCallback
class SearchListAdapter : ListAdapter<SearchListItem, SearchListAdapter.ViewHolder>(UserDiffCallBack()) {
    class UserDiffCallBack : DiffUtil.ItemCallback<SearchListItem>() {
        // 현재 리스트에 노출하고 있는 아이템과 새로운 아이템이 서로 같은지 비교
        // 권장 사항 : Item의 파라미터에 고유한 ID 값이 있는 경우, 이 메서드는 ID를 기준으로 동일성을 반환 해야함
        override fun areItemsTheSame(oldItem: SearchListItem, newItem: SearchListItem): Boolean {
            return if (oldItem is SearchListItem.ImageItem && newItem is SearchListItem.ImageItem) {
                oldItem.title == newItem.title
            } else {
                oldItem == newItem
            }
        }

        // areContentsTheSame : 현재 리스트에 노출하고 있는 아이템과 새로운 아이템의 equals를 비교한다.
        // 역할 : 한 Item의 내용이 변경되었는지 감지 (ex 좋아요 state 감지)
        // 호출 경로 : areItemsTheSame 메서드가 true를 반환하는 경우
        override fun areContentsTheSame(
            oldItem: SearchListItem, newItem: SearchListItem,
        ): Boolean {
            return oldItem == newItem
        }
    }

    abstract class ViewHolder(
        root: View,
    ) : RecyclerView.ViewHolder(root) {
        abstract fun onBind(searchItem: SearchListItem)
    }

    enum class SearchItemViewType {
        IMAGE, VIDEO
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // viewType에 적합하게 IMAGE, VIDEO viewHolder를 반환 해야함
        return when (viewType) {
            SearchItemViewType.IMAGE.ordinal ->
                ImageViewHolder(
                    ItemRvImageBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )

            SearchItemViewType.VIDEO.ordinal ->
                VideoViewHolder(
                    ItemRvVideoBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )

            // TODO: UnknownViewHolder() 처리
            else -> throw RuntimeException("적합하지 않은 ViewType 입니다.")
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // 데이터 리스트가 없음,,? -> getItem() 메서드를 통해 현재 데이터 리스트를 가져옴
        Log.d("Test", getItem(position).toString())
        holder.onBind(getItem(position))
    }

    override fun getItemCount(): Int {
        Log.d("Test", "데이터는 ${currentList}")
        Log.d("Test", "데이터의 개수는 ${currentList.size}")

        return currentList.size
    }
    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is SearchListItem.ImageItem -> SearchItemViewType.IMAGE.ordinal
            is SearchListItem.VideoItem -> SearchItemViewType.VIDEO.ordinal
        }
    }

    class ImageViewHolder(
        val binding: ItemRvImageBinding,
    ) : ViewHolder(binding.root) {
        override fun onBind(searchItem: SearchListItem) {
            with(binding) {
                val searchImageItem = searchItem as SearchListItem.ImageItem

                Glide.with(binding.root)
                    .load(searchImageItem.thumbnail)
                    .into(ivImage)

                tvTitle.text = searchImageItem.title
                tvDatetime.text = searchImageItem.date.toString()
            }
        }
    }

    class VideoViewHolder(
        val binding: ItemRvVideoBinding,
    ) : ViewHolder(binding.root) {
        override fun onBind(searchItem: SearchListItem) {
            with(binding) {
                val searchVideoItem = searchItem as SearchListItem.VideoItem

                Glide.with(binding.root)
                    .load(searchVideoItem.thumbnail)
                    .into(ivImage)

                tvTitle.text = searchVideoItem.title
                tvDatetime.text = searchVideoItem.date.toString()
            }
        }
    }
}