package com.msa.news_msa.data.remote.model

import com.google.gson.annotations.SerializedName

data class ArticleModel(
        @SerializedName("source")
        val source: SourceModel? = null,

        @SerializedName("author")
        val author: String? = "",

        @SerializedName("title")
        val title: String? = "",

        @SerializedName("description")
        val description: String? = "",

        @SerializedName("url")
        val url: String? = "",

        @SerializedName("urlToImage")
        val urlToImage : String? = "",

        @SerializedName("publishedAt")
        val publishedAt: String? = "",

        @SerializedName("content")
        val content: String? = ""

)

data class SourceModel(
        @SerializedName("name")
        val sourceName: String? = ""
)
