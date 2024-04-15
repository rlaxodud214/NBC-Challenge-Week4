package com.example.retrofittest.presentation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.retrofittest.R
import com.example.retrofittest.databinding.ActivityMainBinding
import com.example.retrofittest.viewModel.SearchViewModel
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val searchViewModel: SearchViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setFragment(ImageFragment.newInstance())

        initView()
        initViewModel()
    }

    private fun initView() {
        with(binding) {
            tlMain.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    tab?.connectFragment()
                    searchViewModel.setCurrentTabPosition(tab?.position!!)
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {}
                override fun onTabReselected(tab: TabLayout.Tab?) {}
            })
        }
    }

    private fun initViewModel() = with(binding) {
        binding.etSearchWord.addTextChangedListener {
            val text = etSearchWord.text.toString()
            searchViewModel.setSearchWord(text)
        }

        btnSearch.setOnClickListener {
            searchViewModel.setSearchData()
        }
    }

    private fun TabLayout.Tab.connectFragment() = with(binding) {
        val tabPosition = this@connectFragment.position

        when (tabPosition) {
            0 -> setFragment(ImageFragment.newInstance())
            1 -> setFragment(VideoFragment.newInstance())
        }
    }

    private fun setFragment(fragment: Fragment) {
        supportFragmentManager.commit {
            replace(R.id.fl_frag, fragment)
            setReorderingAllowed(true)
            addToBackStack(null)
        }
    }
}