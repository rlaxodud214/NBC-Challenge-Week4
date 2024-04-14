package com.example.retrofittest.api

import com.example.retrofittest.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    val BASE_URL: String
        get() = BuildConfig.KAKAO_API_URL

    val client = initInstance(BASE_URL)

    fun getInstance() = client

    fun initInstance(url: String) = Retrofit
        .Builder()
        .baseUrl(url)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}