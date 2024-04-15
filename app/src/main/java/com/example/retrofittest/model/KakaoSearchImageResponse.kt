package com.example.retrofittest.model

import com.google.gson.annotations.SerializedName

data class KaKaoSearchImageResponse(
    val meta: Meta,
    var documents: List<ImageDocument>,
)

data class ImageDocument(
    // 만약 변수명을 다르게 쓰고 싶다면 SerializedName을 사용해서 명시해주면 된다.
    @SerializedName("collection")
    val collection: String,
    @SerializedName("datetime")
    val datetime: String,
    val display_sitename: String,
    val doc_url: String,
    val height: Int,
    val width: Int,
    val image_url: String,
    val thumbnail_url: String,
)