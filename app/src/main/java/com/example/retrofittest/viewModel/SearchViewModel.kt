package com.example.retrofittest.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofittest.model.KaKaoSearchResponse
import com.example.retrofittest.repository.SearchImageRepository
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {

    private var _searchWord = MutableLiveData<String>()
    val searchWord: LiveData<String> = _searchWord

    private var _imageData = MutableLiveData<KaKaoSearchResponse>()
    val imageData: LiveData<KaKaoSearchResponse> = _imageData

    private val repository = SearchImageRepository()

    fun setSearchWord(keyWord: String) {
        _searchWord.value = keyWord
    }

    fun setSearchImageData() {
        viewModelScope.launch {
            val keyWord = _searchWord.value.toString()

             _imageData.value = repository.getSearchImageData(keyWord)
            Log.d("API Call", _imageData.value.toString())
        }
    }
}