package com.example.retrofittest.presentation.ui

import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.retrofittest.R

class SearchViewPagerAdapter(
    fragmentActivity: FragmentActivity
): FragmentStateAdapter(fragmentActivity) {

    private val fragments = listOf(
        SearchTabModel(
            fragment = ImageFragment.newInstance(),
            title = "Image",
            icon = R.drawable.ic_launcher_foreground
        ),
        SearchTabModel(
            fragment = VideoFragment.newInstance(),
            title = "Video",
            icon = R.drawable.ic_launcher_foreground
        )
    )

    override fun getItemCount() = 2

    override fun createFragment(position: Int) = fragments[position].fragment

    fun getTitle(position: Int) = fragments[position].title
    fun getIcon(position: Int) = fragments[position].icon
}