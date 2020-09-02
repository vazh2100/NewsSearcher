package com.vazheninapps.newssearcher.api


import com.vazheninapps.newssearcher.pojo.Response
import io.reactivex.Single

import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface NewsService {

    @Headers(HEADER_AUTHORIZATION)
    @GET("everything")
    fun getNews(
        @Query(QUERY_Q) q: String,
        @Query(QUERY_LANGUAGE) language: String,
        @Query(QUERY_PAGE_SIZE) pageSize: Int = 10,
        @Query(QUERY_PAGE) page: Int = 1
    ): Single<Response>

    companion object {
        private const val API_KEY = "e7a4d3493ec84a1a9232789bf7a943cf"

        private const val HEADER_AUTHORIZATION = "Authorization: $API_KEY"

        private const val QUERY_Q = "q"
        private const val QUERY_LANGUAGE = "language"
        private const val QUERY_PAGE_SIZE = "pageSize"
        private const val QUERY_PAGE = "page"

    }


}