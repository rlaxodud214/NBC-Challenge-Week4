package com.example.retrofittest.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    // val BASE_URL1 = "https://jsonplaceholder.typicode.com"
    val BASE_URL = "https://raw.githubusercontent.com"
    val client = initInstance(BASE_URL)

    fun getInstance() = client

    fun initInstance(url: String) = Retrofit
        .Builder()
        .baseUrl(url)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}