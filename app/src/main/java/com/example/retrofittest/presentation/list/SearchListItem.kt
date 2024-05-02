package com.example.retrofittest.presentation.list

import java.util.Date

sealed interface SearchListItem {
    data class ImageItem(
        val title: String?,
        val thumbnail: String?,
        val date: String?
    ): SearchListItem

    data class VideoItem(
        val title: String?,
        val thumbnail: String?,
        val date: String?
    ): SearchListItem
}