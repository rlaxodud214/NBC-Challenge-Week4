package com.example.retrofittest.presentation.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.retrofittest.R
import com.example.retrofittest.newInstance
import com.example.retrofittest.presentation.list.SearchListFragment

class SearchMainViewPagerAdapter(
    fragmentActivity: FragmentActivity
): FragmentStateAdapter(fragmentActivity) {

    private val fragments = listOf(
        SearchMainTabModel(
            fragment = newInstance<SearchListFragment>(),
            title = "Search",
            icon = R.drawable.ic_launcher_foreground
        ),
        SearchMainTabModel(
            fragment = newInstance<SearchMainBookmarkFragment>(),
            title = "Bookmark",
            icon = R.drawable.ic_launcher_foreground
        )
    )

    override fun getItemCount() = 2

    override fun createFragment(position: Int) = fragments[position].fragment

    fun getTitle(position: Int) = fragments[position].title
    fun getIcon(position: Int) = fragments[position].icon
}