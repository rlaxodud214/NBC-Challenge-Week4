package com.example.retrofittest.presentation.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.example.retrofittest.databinding.ActivityMainBinding
import com.example.retrofittest.presentation.ui.viewModel.SearchViewModel
import com.example.retrofittest.presentation.ui.viewModel.SearchViewModelFactory
import com.google.android.material.tabs.TabLayoutMediator

class SearchMainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val searchViewModel: SearchViewModel by viewModels() {
        SearchViewModelFactory()
    }

    val searchMainViewPagerAdapter: SearchMainViewPagerAdapter by lazy {
        SearchMainViewPagerAdapter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initView()
        initViewModel()
    }

    private fun initView() {
        with(binding) {
            viewPager.adapter = searchMainViewPagerAdapter

            TabLayoutMediator(tlMain, viewPager) { tab, position ->
                tab.setText(searchMainViewPagerAdapter.getTitle(position))
                tab.setIcon(searchMainViewPagerAdapter.getIcon(position))
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