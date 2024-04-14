package com.example.retrofittest.model

data class KaKaoSearchResponse(
    val meta: Meta = Meta(),
    val documents: List<ImageDocument> = listOf(ImageDocument()),
)

data class Meta(
    val totalCount: Int = -1,
    val pageableCount: Int = -1,
    val isEnd: Boolean = false,
)

data class ImageDocument(
    val title: String = "",
    val url: String = "",
    val datetime: String = "",
)