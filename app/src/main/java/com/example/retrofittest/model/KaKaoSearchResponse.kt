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
    val collection: String,
    val datetime: String,
    val display_sitename: String,
    val doc_url: String,
    val height: Int,
    val width: Int,
    val image_url: String,
    val thumbnail_url: String,
)