package com.example.retrofittest.api

import com.example.retrofittest.model.KaKaoSearchImageResponse
import com.example.retrofittest.model.KaKaoSearchVideoResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApi {

    @GET("v2/search/vclip")
    fun getSearchVideo(
        @Query("query") query: String,
        @Query("sort") sortType: String,
    ): Call<KaKaoSearchVideoResponse>

    @GET("v2/search/image")
    fun getSearchImage(
        @Query("query") query: String,
        @Query("sort") sortType: String,
    ): Call<KaKaoSearchImageResponse>

//    @GET("v2/search/image")
//    suspend fun getSearchImage(
//        @Query("query") query: String,
//        @Query("sort") sortType: String,
//    ): KaKaoSearchImageResponse
}
