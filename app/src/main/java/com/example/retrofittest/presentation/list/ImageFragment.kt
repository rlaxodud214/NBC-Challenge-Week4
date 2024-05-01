package com.example.retrofittest.presentation.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofittest.databinding.FragmentImageBinding
import com.example.retrofittest.presentation.ui.viewModel.SearchViewModel
import com.example.retrofittest.presentation.ui.viewModel.SearchViewModelFactory

class ImageFragment : Fragment() {
    private val binding by lazy {
        FragmentImageBinding.inflate(layoutInflater)
    }

    private val searchViewModel: SearchViewModel by activityViewModels() {
        SearchViewModelFactory()
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
            val data = it.documents ?: listOf()
            val searchImageAdapter = SearchImageAdapter(data)

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