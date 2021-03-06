package com.msa.news_msa.data.local.dao

import androidx.room.*
import com.msa.news_msa.data.local.model.NewsArticleModel
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsArticleDao {

    @Query("select * from news_article where category = :category order by publishedAt desc")
    fun getAllArticlesForCategory(category: String): Flow<List<NewsArticleModel>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(articles: List<NewsArticleModel>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateArticle(articleModel: NewsArticleModel)

    @Query("DELETE FROM news_article")
    fun clearAllArticles()

}