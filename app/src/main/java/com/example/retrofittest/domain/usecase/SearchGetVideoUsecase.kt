package com.example.retrofittest.domain.usecase

import com.example.retrofittest.data.model.SearchVideoResponse
import com.example.retrofittest.domain.model.SearchImageEntity
import com.example.retrofittest.repository.SearchRepository

class SearchGetVideoUsecase(
    private val searchRepository: SearchRepository,
) {
    // invoke 연산자를 정의함 -> 사용법: 객체 뒤에 "()" 붙이면 자동으로 invoke 함수 호출됨
    suspend operator fun invoke(
        query: String,
        sort: String = "accuracy",
        page: Int = 1,
        size: Int = 15,
    ): SearchVideoResponse {
        // 어딘가 있는 구현체 함수 호출
        return searchRepository.getSearchVideo(
            query = query,
            sort = sort,
            page = page,
            size = size,
        )
    }
}