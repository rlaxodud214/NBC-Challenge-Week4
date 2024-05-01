package com.example.retrofittest.repository

import com.example.retrofittest.data.model.SearchVideoResponse
import com.example.retrofittest.domain.model.SearchImageEntity

interface SearchRepository {

    suspend fun getSearchImage(
        query: String,
        sort: String,
        page: Int,
        size: Int,
    ): SearchImageEntity

    suspend fun getSearchVideo(
        query: String,
        sort: String,
        page: Int,
        size: Int,
    ): SearchVideoResponse
}