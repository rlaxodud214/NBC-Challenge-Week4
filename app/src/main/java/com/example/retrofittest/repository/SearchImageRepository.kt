package com.example.retrofittest.repository

import com.example.retrofittest.api.RetrofitInstance
import com.example.retrofittest.api.SearchApi
import com.example.retrofittest.model.KaKaoSearchImageResponse
import com.example.retrofittest.model.KaKaoSearchVideoResponse
import retrofit2.Call

class SearchImageRepository {
    private val client = RetrofitInstance.getInstance().create(SearchApi::class.java)

    fun getSearchImage(keyWord: String): Call<KaKaoSearchImageResponse> {
        return client.getSearchImage(keyWord, "recency")
    }

    fun getSearchVideo(keyWord: String): Call<KaKaoSearchVideoResponse> {
        return client.getSearchVideo(keyWord, "recency")
    }
}