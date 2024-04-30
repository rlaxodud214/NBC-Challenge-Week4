package com.example.retrofittest.data.model

import com.google.gson.annotations.SerializedName

data class MetaResponse(
    @SerializedName("totalCount") val totalCount: Int,
    @SerializedName("pageableCount") val pageableCount: Int,
    @SerializedName("isEnd") val isEnd: Boolean,
)