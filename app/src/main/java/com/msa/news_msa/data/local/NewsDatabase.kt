package com.msa.news_msa.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.msa.news_msa.data.local.dao.NewsArticleDao
import com.msa.news_msa.data.local.model.NewsArticleModel

@Database(
    entities = [NewsArticleModel::class],
    version = 3,
    exportSchema = true
)
abstract class NewsDatabase: RoomDatabase() {
    abstract fun newsArticlesDao(): NewsArticleDao
}