package com.example.retrofittest.presentation.ui.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.retrofittest.data.model.SearchVideoResponse
import com.example.retrofittest.data.repository.SearchRepositoryImpl
import com.example.retrofittest.network.RetrofitClient
import com.example.retrofittest.domain.model.SearchImageEntity
import com.example.retrofittest.domain.usecase.SearchGetImageUsecase
import com.example.retrofittest.domain.usecase.SearchGetVideoUsecase
import com.example.retrofittest.presentation.list.SearchListItem
import com.example.retrofittest.presentation.list.SearchListUiState
import kotlinx.coroutines.launch

class SearchViewModel(
    private val searchGetImageUsecase: SearchGetImageUsecase,
    private val searchGetVideoUsecase: SearchGetVideoUsecase
) : ViewModel() {

    private var _searchWord = MutableLiveData<String>()
    val searchWord: LiveData<String> = _searchWord

    private var _searchListUiState = MutableLiveData<SearchListUiState>()
    val searchListUiState: LiveData<SearchListUiState> = _searchListUiState

    private var _videoData = MutableLiveData<SearchVideoResponse>()
    val videoData: LiveData<SearchVideoResponse> = _videoData

    fun setSearchWord(keyWord: String) {
        _searchWord.value = keyWord
    }

    fun setSearchData(position: Int) {
        when (position) {
            0 -> onSearchImage()
            1 -> onSearchVideo()
        }
    }

    fun onSearchImage() = viewModelScope.launch {
        runCatching {
            val keyWord = _searchWord.value.toString()

            val res = searchGetImageUsecase(keyWord)

            val items = createItems(
                images = res
            )

            _searchListUiState.value = SearchListUiState(
                items = items,
                isLoading = false
            )
        }.onSuccess {
            Log.d("API CALL", "Image API 호출이 성공했습니다.")
        }.onFailure {
            Log.d("API CALL", "Image API 호출이 실패했습니다.")
        }
    }

    fun onSearchVideo() = viewModelScope.launch {
        val keyWord = _searchWord.value.toString()

        runCatching {
            val res = searchGetVideoUsecase(keyWord)
            _videoData.value = res

        }.onFailure {
            Log.d("API CALL", "Video API 호출이 실패했습니다.")
        }
    }

    private fun createItems(
        images: SearchImageEntity
    ): List<SearchListItem>  {

        fun createImageItems(
            images: SearchImageEntity
        ): List<SearchListItem.ImageItem> {
            return images.documents?.map { docs ->
                SearchListItem.ImageItem(
                    title = docs.collection,
                    thumbnail = docs.thumbnail_url,
                    date = docs.datetime
                )
            }.orEmpty()
        }

        return createImageItems(images).sortedByDescending {
            it.date
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