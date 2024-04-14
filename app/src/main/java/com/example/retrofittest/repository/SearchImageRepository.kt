package com.example.retrofittest.repository

import android.util.Log
import com.example.retrofittest.BuildConfig
import com.example.retrofittest.api.RetrofitInstance
import com.example.retrofittest.api.SearchApi
import com.example.retrofittest.model.KaKaoSearchResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchImageRepository {
    private val client = RetrofitInstance.getInstance().create(SearchApi::class.java)

    suspend fun getSearchImageData(keyWord: String): KaKaoSearchResponse {
        return client.getSearchImage(keyWord)
    }

    suspend fun getSearchImageDataCall(keyWord: String): Call<KaKaoSearchResponse> {
        return client.getSearchImageCall(keyWord)
    }
}