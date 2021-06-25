package com.msa.news_msa.data.remote.model

import com.google.gson.annotations.SerializedName

data class NewsResponse(
    @SerializedName("status")
    val status: String = "",

    @SerializedName("totalResults")
    val totalResults : Int = -1,

    @SerializedName("articles")
    val articles : List<ArticleModel> = emptyList()
)