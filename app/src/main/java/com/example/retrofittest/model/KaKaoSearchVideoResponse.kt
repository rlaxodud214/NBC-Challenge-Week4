package com.example.retrofittest.model

import com.google.gson.annotations.SerializedName

data class KaKaoSearchVideoResponse(
    val meta: Meta,
    var documents: List<VideoDocument>,
)

data class VideoDocument(
    @SerializedName("title")
    val title: String,
    val url: String,
    val datetime: String,
    val playTime: Int,
    val thumbnail: String,
    val author: String,
)