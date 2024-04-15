package com.example.retrofittest.repository

import com.example.retrofittest.api.RetrofitInstance
import com.example.retrofittest.api.SearchApi
import com.example.retrofittest.model.KaKaoSearchImageResponse
import retrofit2.Call

class SearchImageRepository {
    private val client = RetrofitInstance.getInstance().create(SearchApi::class.java)

    suspend fun getSearchImageData(keyWord: String): KaKaoSearchImageResponse {
        return client.getSearchImage(keyWord, "recency")
//        return client.getSearchImage(BuildConfig.KAKAO_API_KEY, keyWord)
    }

    suspend fun getSearchImageDataCall(keyWord: String): Call<KaKaoSearchImageResponse> {
        return client.getSearchImageCall(keyWord, "recency")
//        return client.getSearchImageCall(BuildConfig.KAKAO_API_KEY, keyWord)
    }
}