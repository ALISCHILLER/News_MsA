package com.msa.news_msa.data.remote


import com.msa.news_msa.data.remote.model.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {

    @GET("top-headlines")
    suspend fun getTopHeadLinesForCategory(
            @Query("category") category: String,
            @Query("country") country: String,
            @Query("apiKey") apiKey: String): Response<NewsResponse>
}