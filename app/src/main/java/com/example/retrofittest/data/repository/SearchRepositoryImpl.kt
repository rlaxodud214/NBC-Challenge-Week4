package com.example.retrofittest.data.repository

import com.example.retrofittest.data.model.SearchVideoResponse
import com.example.retrofittest.data.remote.SearchRemoteDatasource
import com.example.retrofittest.presentation.model.SearchImageEntity
import com.example.retrofittest.presentation.model.toEntity
import com.example.retrofittest.repository.SearchRepository

class SearchRepositoryImpl(
    private val remoteDatasource: SearchRemoteDatasource,
) : SearchRepository {

    override suspend fun getSearchImage(
        query: String,
        sort: String,
        page: Int,
        size: Int,
    ): SearchImageEntity {
        return remoteDatasource.getSearchImage(
            query,
            sort,
            page,
            size
        ).toEntity()
    }

    override suspend fun getSearchVideo(
        query: String,
        sort: String,
        page: Int,
        size: Int,
    ): SearchVideoResponse {
        return remoteDatasource.getSearchVideo(
            query,
            sort,
            page,
            size
        )
    }
}