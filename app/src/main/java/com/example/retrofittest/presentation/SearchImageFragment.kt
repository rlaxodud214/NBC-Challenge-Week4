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
import com.example.retrofittest.presentation.list.SearchListItem
import com.example.retrofittest.presentation.ui.viewModel.SearchViewModel
import com.example.retrofittest.presentation.ui.viewModel.SearchViewModelFactory

class SearchImageFragment : Fragment() {
    private val binding by lazy {
        FragmentImageBinding.inflate(layoutInflater)
    }

    private val searchViewModel: SearchViewModel by activityViewModels() {
        SearchViewModelFactory()
    }

    private val searchListAdapter: SearchListAdapter by lazy {
        SearchListAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchViewModel.searchListUiState.observe(viewLifecycleOwner) { uiState ->
            searchListAdapter.submitList(uiState.items)
        }

        with(binding.rvImage) {
            adapter = searchListAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    companion object {
        private var INSTANCE: SearchImageFragment? = null

        fun newInstance(): SearchImageFragment {
            return synchronized(SearchImageFragment::class.java) {
                val instance = INSTANCE ?: SearchImageFragment()

                if (INSTANCE == null) {
                    INSTANCE = instance
                }

                instance
            }
        }
    }
}