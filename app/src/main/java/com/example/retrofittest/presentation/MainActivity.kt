package com.example.retrofittest.presentation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.example.retrofittest.databinding.ActivityMainBinding
import com.example.retrofittest.viewModel.SearchViewModel

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val searchViewModel: SearchViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        getDataFromKakao()
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

}