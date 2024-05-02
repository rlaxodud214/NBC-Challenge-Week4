package com.example.retrofittest.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofittest.databinding.FragmentImageBinding
import com.example.retrofittest.presentation.list.SearchListAdapter
import com.example.retrofittest.presentation.ui.viewModel.SearchViewModel
import com.example.retrofittest.presentation.ui.viewModel.SearchViewModelFactory

class SearchVideoFragment : Fragment() {
    private val binding by lazy {
        FragmentImageBinding.inflate(layoutInflater)
    }

    private val searchViewModel: SearchViewModel by activityViewModels() {
        SearchViewModelFactory()
    }

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

        searchViewModel.videoData.observe(viewLifecycleOwner) {
            val searchVideoAdapter = SearchListAdapter()

            with(binding.rvImage) {
                adapter = searchVideoAdapter
                layoutManager = LinearLayoutManager(context)
            }
        }
    }

    companion object {
        private var INSTANCE: SearchVideoFragment? = null

        fun newInstance(): SearchVideoFragment {
            return synchronized(SearchVideoFragment::class.java) {
                val instance = INSTANCE ?: SearchVideoFragment()

                if (INSTANCE == null) {
                    INSTANCE = instance
                }

                instance
            }
        }
    }
}