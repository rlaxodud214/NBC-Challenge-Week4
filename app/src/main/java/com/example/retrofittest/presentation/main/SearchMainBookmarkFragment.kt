package com.example.retrofittest.presentation.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.retrofittest.databinding.FragmentBookmarkBinding

class SearchMainBookmarkFragment : Fragment() {
    val binding: FragmentBookmarkBinding by lazy {
        FragmentBookmarkBinding.inflate(layoutInflater)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return binding.root
    }
}
