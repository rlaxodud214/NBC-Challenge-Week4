package com.example.retrofittest.presentation.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.retrofittest.R
import com.example.retrofittest.databinding.ActivityMainBinding
import com.example.retrofittest.presentation.ui.viewModel.SearchViewModel
import com.example.retrofittest.presentation.ui.viewModel.SearchViewModelFactory
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val searchViewModel: SearchViewModel by viewModels() {
        SearchViewModelFactory()
    }

    val searchViewPagerAdapter: SearchViewPagerAdapter by lazy {
        SearchViewPagerAdapter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initView()
        initViewModel()
    }

    private fun initView() {
        with(binding) {
            viewPager.adapter = searchViewPagerAdapter

            TabLayoutMediator(tlMain, viewPager) { tab, position ->
                tab.setText(searchViewPagerAdapter.getTitle(position))
                tab.setIcon(searchViewPagerAdapter.getIcon(position))
            }.attach()
        }
    }

    private fun initViewModel() = with(binding) {
        binding.etSearchWord.addTextChangedListener {
            val text = etSearchWord.text.toString()
            searchViewModel.setSearchWord(text)
        }

        btnSearch.setOnClickListener {
            searchViewModel.setSearchData(tlMain.selectedTabPosition)
        }
    }
}