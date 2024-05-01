package com.example.retrofittest.presentation.main

import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.retrofittest.R
import com.example.retrofittest.presentation.list.ImageFragment
import com.example.retrofittest.presentation.list.VideoFragment

class SearchMainViewPagerAdapter(
    fragmentActivity: FragmentActivity
): FragmentStateAdapter(fragmentActivity) {

    private val fragments = listOf(
        SearchMainTabModel(
            fragment = ImageFragment.newInstance(),
            title = "Image",
            icon = R.drawable.ic_launcher_foreground
        ),
        SearchMainTabModel(
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