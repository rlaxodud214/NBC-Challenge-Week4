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
            0 -> onSearchData()
            1 -> onSearchData()
        }
    }

    fun onSearchData() = viewModelScope.launch {
        runCatching {
            val query = _searchWord.value.toString()

            val imageResponse = searchGetImageUsecase(query)
            val videoResponse = searchGetVideoUsecase(query)

            val items = createItems(
                images = imageResponse,
                videos = videoResponse,
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

    private fun createItems(
        images: SearchImageEntity,
        videos: SearchVideoResponse
    ): List<SearchListItem>  {

        fun createImageItems(
            images: SearchImageEntity,
            videos: SearchVideoResponse
        ): List<SearchListItem> {
            val image = images.documents?.map { docs ->
                SearchListItem.ImageItem(
                    title = docs.collection,
                    thumbnail = docs.thumbnail_url,
                    date = docs.datetime
                )
            }.orEmpty()

            val video = videos.documents?.map { docs ->
                SearchListItem.VideoItem(
                    title = docs.title,
                    thumbnail = docs.thumbnail,
                    date =  docs.datetime
                )
            }.orEmpty()

            return image + video
        }

        // 기존의 List를 새로운 List로 반환 해줘야 한다.
        // 이유는 잘 모르겠지만, 새로운 List로 해줘야 DiffUtil의 메서드에서 false를 해주고, UI가 갱신 되는 듯?
        return arrayListOf<SearchListItem>().apply {
            addAll(createImageItems(images, videos))
        }.sortedByDescending {
            when(it) {
                is SearchListItem.ImageItem -> it.date
                is SearchListItem.VideoItem -> it.date
            }
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