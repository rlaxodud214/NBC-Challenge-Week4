package com.example.retrofittest.repository

import com.example.retrofittest.data.model.SearchVideoResponse
import com.example.retrofittest.presentation.model.SearchImageEntity

interface SearchRepository {

    suspend fun getSearchImage(
        query: String,
        sort: String = "accuracy",
        page: Int = 1,
        size: Int = 80,
    ): SearchImageEntity

    suspend fun getSearchVideo(
        query: String,
        sort: String = "accuracy",
        page: Int = 1,
        size: Int = 15,
    ): SearchVideoResponse
}