package com.example.retrofittest.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofittest.model.KaKaoSearchResponse
import com.example.retrofittest.repository.SearchImageRepository
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

    fun setSearchImageDataCall() {
        viewModelScope.launch {
            val keyWord = _searchWord.value.toString()

             repository.getSearchImageDataCall(keyWord).enqueue(object: Callback<KaKaoSearchResponse> {
                 override fun onResponse(
                     call: Call<KaKaoSearchResponse>,
                     response: Response<KaKaoSearchResponse>,
                 ) {
                     Log.d("API Call", response.toString())
                 }

                 override fun onFailure(call: Call<KaKaoSearchResponse>, t: Throwable) {
                     TODO("Not yet implemented")
                 }
             })
        }
    }
}