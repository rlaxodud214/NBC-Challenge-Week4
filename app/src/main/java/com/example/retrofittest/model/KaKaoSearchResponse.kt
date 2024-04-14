package com.example.retrofittest.model

data class KaKaoSearchResponse(
    val meta: Meta,
    val documents: List<ImageDocument>,
)

data class Meta(
    val totalCount: Int,
    val pageableCount: Int,
    val isEnd: Boolean,
)

data class ImageDocument(
    val title: String,
    val url: String,
    val datetime: String,
)