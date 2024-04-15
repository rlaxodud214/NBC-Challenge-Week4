package com.example.retrofittest.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofittest.adapter.SearchImageAdapter
import com.example.retrofittest.databinding.ActivityMainBinding
import com.example.retrofittest.viewModel.SearchViewModel
import kotlin.math.log

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val searchViewModel: SearchViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        getDataFromKakao()
        initAdapter()
    }

    fun getDataFromKakao() {
        with(binding) {
            etSearchWord.addTextChangedListener {
                val text = etSearchWord.text.toString()
                searchViewModel.setSearchWord(text)
            }

            btnSearch.setOnClickListener {
                searchViewModel.setSearchImageData()
                searchViewModel.setSearchImageDataCall()
            }
        }
    }

    private fun initAdapter() {
        searchViewModel.imageData.observe(this) {
            val searchImageAdapter = SearchImageAdapter(it.documents)

            with(binding.rvImage) {
                adapter = searchImageAdapter
                layoutManager = LinearLayoutManager(this@MainActivity)
            }
        }
    }
}