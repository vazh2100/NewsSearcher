package com.vazheninapps.newssearcher.api


import com.vazheninapps.newssearcher.pojo.Response
import io.reactivex.Single

import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface NewsService {

    @Headers("Authorization: $apiKey")
    @GET("everything")
    fun getNews(@Query("q")  q:String = "android"): Single<Response>

    companion object{
       const val apiKey = "e7a4d3493ec84a1a9232789bf7a943cf"

    }



}