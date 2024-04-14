package com.example.retrofittest.api

import com.example.retrofittest.model.KaKaoSearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface SearchApi {
    @GET("v2/search/image")
    suspend fun getSearchImage(
        @Query("query") query: String,
        @Header("Authorization") authorization: String,
    ): KaKaoSearchResponse
}