package com.example.retrofittest.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofittest.model.KaKaoSearchImageResponse
import com.example.retrofittest.model.KaKaoSearchVideoResponse
import com.example.retrofittest.repository.SearchImageRepository
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchViewModel : ViewModel() {

    private var _searchWord = MutableLiveData<String>()
    val searchWord: LiveData<String> = _searchWord

    private var _imageData = MutableLiveData<KaKaoSearchImageResponse>()
    val imageData: LiveData<KaKaoSearchImageResponse> = _imageData

    private var _videoData = MutableLiveData<KaKaoSearchVideoResponse>()
    val videoData: LiveData<KaKaoSearchVideoResponse> = _videoData

    private var _currentTabPosition = MutableLiveData<Int>()
    val currentTabPosition: LiveData<Int> = _currentTabPosition

    private val repository = SearchImageRepository()

    fun setSearchWord(keyWord: String) {
        _searchWord.value = keyWord
    }

    fun setCurrentTabPosition(position: Int) {
        _currentTabPosition.value = position
    }

    fun setSearchData() {
        when(currentTabPosition.value) {
            0 -> setSearchImageData()
            1 -> setSearchVideoData()
        }
    }

    fun setSearchImageData() {
        viewModelScope.launch {
            val keyWord = _searchWord.value.toString()

            repository.getSearchImage(keyWord)
                .enqueue(object : Callback<KaKaoSearchImageResponse> {
                    override fun onResponse(
                        call: Call<KaKaoSearchImageResponse>,
                        response: Response<KaKaoSearchImageResponse>,
                    ) {
                        if (response.code() == 200) {
                            _imageData.value = response.body()
                        }
                        Log.d("API Call", "response : ${response}")
                    }

                    override fun onFailure(call: Call<KaKaoSearchImageResponse>, t: Throwable) {
                        TODO("Not yet implemented")
                    }
                })
        }
    }

    fun setSearchVideoData() {
        viewModelScope.launch {
            val keyWord = _searchWord.value.toString()

            repository.getSearchVideo(keyWord)
                .enqueue(object : Callback<KaKaoSearchVideoResponse> {
                    override fun onResponse(
                        call: Call<KaKaoSearchVideoResponse>,
                        response: Response<KaKaoSearchVideoResponse>,
                    ) {
                        Log.d("API Call", "response : ${response}")

                        if (response.code() == 200) {
                            _videoData.value = response.body()
                        }
                    }

                    override fun onFailure(call: Call<KaKaoSearchVideoResponse>, t: Throwable) {
                        TODO("Not yet implemented")
                    }
                })
        }
    }
}