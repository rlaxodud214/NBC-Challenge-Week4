package com.example.retrofittest.api

import android.provider.CalendarContract.Instances
import com.example.retrofittest.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val INSTANCE = initInstance()
    fun getInstance() = INSTANCE

    private fun initInstance(): Retrofit {
        // OkHttp를 통해서 각 Api Call 메서드가 아닌 Retrofit 객체 자체에 Header 값을 줄 수 있었다.

        val client = OkHttpClient.Builder()
            .addInterceptor { chain ->
                val original = chain.request()
                val request = original.newBuilder()
                    .header("Authorization", BuildConfig.KAKAO_API_KEY)
                    .build()
                chain.proceed(request)
            }
            .build()

        return Retrofit.Builder()
            .baseUrl(BuildConfig.KAKAO_API_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}