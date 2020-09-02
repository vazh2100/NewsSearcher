package com.vazheninapps.newssearcher.pojo

import com.google.gson.annotations.SerializedName


data class Response(
    @SerializedName("status")
    val status: String? = null,
    @SerializedName("totalResults")
    private val totalResults: Int? = null,
    @SerializedName("articles")
    private val articles: List<Article>? = null

) {


}