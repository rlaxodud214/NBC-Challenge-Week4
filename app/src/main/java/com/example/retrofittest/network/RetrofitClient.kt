package com.example.retrofittest.network

import com.example.retrofittest.BuildConfig
import com.example.retrofittest.data.remote.SearchRemoteDatasource
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private val BASE_URL = BuildConfig.KAKAO_API_URL

    private val okHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor())
            .build()
    }

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val search: SearchRemoteDatasource by lazy {
        retrofit.create(SearchRemoteDatasource::class.java)
    }
}