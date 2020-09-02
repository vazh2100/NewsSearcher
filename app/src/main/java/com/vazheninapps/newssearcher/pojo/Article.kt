package com.vazheninapps.newssearcher.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class Article(
    @SerializedName("author")
    var author: String? = null,
    @SerializedName("title")
    var title: String? = null,
    @SerializedName("description")
    var description: String? = null,
    @SerializedName("url")
    var url: String? = null,
    @SerializedName("urlToImage")
    var urlToImage: String? = null
)