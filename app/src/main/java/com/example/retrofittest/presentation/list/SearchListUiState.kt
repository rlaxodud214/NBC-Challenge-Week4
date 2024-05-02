package com.example.retrofittest.presentation.list

data class SearchListUiState(
    val items: List<SearchListItem>, // adapter에 들어갈 Datasource
    val isLoading: Boolean // API를 호출하는 중에 ProgressBar UI를 띄우기 위함
) {
    companion object {
        fun init() {
            SearchListUiState(
                items = emptyList(),
                isLoading = false
            )
        }
    }
}