package com.msa.news_msa.data.repository

import com.msa.news_msa.data.local.model.NewsArticleModel
import com.msa.news_msa.data.remote.model.NewsResponse
import com.msa.news_msa.utils.ViewState
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface NewsUseCaseInterface {
    fun getNewsArticlesByCategory(category: String) : Flow<ViewState<List<NewsArticleModel>>>

    suspend fun bookMarkArticle(articleModel: NewsArticleModel)

    suspend fun getTopHeadLinesForCategory(category: String): Response<NewsResponse>
}