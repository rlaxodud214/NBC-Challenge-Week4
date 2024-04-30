package com.example.retrofittest.data.model

import com.google.gson.annotations.SerializedName

data class SearchVideoResponse(
    @SerializedName("metaResponse") val metaResponse: MetaResponse,
    @SerializedName("documents") var documents: List<VideoDocumentResponse>,
)

data class VideoDocumentResponse(
    @SerializedName("title") val title: String,
    @SerializedName("url") val url: String,
    @SerializedName("datetime") val datetime: String,
    @SerializedName("playTime") val playTime: Int,
    @SerializedName("thumbnail") val thumbnail: String,
    @SerializedName("author") val author: String,
)