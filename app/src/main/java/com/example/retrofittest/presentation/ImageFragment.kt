package com.example.retrofittest.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofittest.adapter.SearchImageAdapter
import com.example.retrofittest.databinding.FragmentImageBinding
import com.example.retrofittest.viewModel.SearchViewModel

class ImageFragment : Fragment() {
    private val binding by lazy {
        FragmentImageBinding.inflate(layoutInflater)
    }

    private val searchViewModel: SearchViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchViewModel.imageData.observe(viewLifecycleOwner) {
            val searchImageAdapter = SearchImageAdapter(it.documents)

            with(binding.rvImage) {
                adapter = searchImageAdapter
                layoutManager = LinearLayoutManager(context)
            }
        }
    }

    companion object {
        private var INSTANCE: ImageFragment? = null

        fun newInstance(): ImageFragment {
            return synchronized(ImageFragment::class.java) {
                val instance = INSTANCE ?: ImageFragment()

                if (INSTANCE == null) {
                    INSTANCE = instance
                }

                instance
            }
        }
    }
}