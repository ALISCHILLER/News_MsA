package com.msa.news_msa.utils.mapper

import com.msa.news_msa.data.local.model.NewsArticleModel
import com.msa.news_msa.data.remote.model.ArticleModel
import com.msa.news_msa.utils.Mapper

interface NewsMapper : Mapper<NewsArticleModel, ArticleModel> {
    override fun NewsArticleModel.toRemote(): ArticleModel {
        return ArticleModel(
                null,
                author = author,
                title = title,
                description = description,
                url = url,
                urlToImage = urlToImage,
                publishedAt = publishedAt,
                content = content
        )
    }

    override fun ArticleModel.toCache(category: String): NewsArticleModel {
        return NewsArticleModel(
                author = author,
                title = title?: "Default Title",
                description = description,
                url = url,
                urlToImage = urlToImage,
                publishedAt = publishedAt,
                content = content,
                category = category,
                isBookmarked = false
        )
    }
}