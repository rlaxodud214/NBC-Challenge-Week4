package com.example.retrofittest.presentation.ui.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.retrofittest.data.model.SearchImageResponse
import com.example.retrofittest.data.model.SearchVideoResponse
import com.example.retrofittest.data.repository.SearchRepositoryImpl
import com.example.retrofittest.network.RetrofitClient
import com.example.retrofittest.domain.model.SearchImageEntity
import com.example.retrofittest.domain.usecase.SearchGetImageUsecase
import com.example.retrofittest.domain.usecase.SearchGetVideoUsecase
import com.example.retrofittest.repository.SearchRepository
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchViewModel(
    private val searchGetImageUsecase: SearchGetImageUsecase,
    private val searchGetVideoUsecase: SearchGetVideoUsecase
) : ViewModel() {

    private var _searchWord = MutableLiveData<String>()
    val searchWord: LiveData<String> = _searchWord

    private var _imageData = MutableLiveData<SearchImageEntity>()
    val imageData: LiveData<SearchImageEntity> = _imageData

    private var _videoData = MutableLiveData<SearchVideoResponse>()
    val videoData: LiveData<SearchVideoResponse> = _videoData

    fun setSearchWord(keyWord: String) {
        _searchWord.value = keyWord
    }

    fun setSearchData(position: Int) {
        when (position) {
            0 -> setSearchImageData()
            1 -> setSearchVideoData()
        }
    }

    fun setSearchImageData() = viewModelScope.launch {
        val keyWord = _searchWord.value.toString()

        lateinit var res: SearchImageEntity

        runCatching {
            res = searchGetImageUsecase(keyWord)

            _imageData.value = res
        }.onSuccess {
            Log.d("API CALL", "Image API 호출이 성공했습니다.")
        }.onFailure {
            Log.d("API CALL", "Image API 호출이 실패했습니다.")
        }
    }

    fun setSearchVideoData() = viewModelScope.launch {
        val keyWord = _searchWord.value.toString()
        lateinit var res: SearchVideoResponse

        runCatching {
            res = searchGetVideoUsecase(keyWord)

            _videoData.value = res
        }.onFailure {
            Log.d("API CALL", "Video API 호출이 실패했습니다.")
        }
    }
}

class SearchViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        val repository = SearchRepositoryImpl(RetrofitClient.search)

        return SearchViewModel(
            SearchGetImageUsecase(repository),
            SearchGetVideoUsecase(repository)
        ) as T
    }
}