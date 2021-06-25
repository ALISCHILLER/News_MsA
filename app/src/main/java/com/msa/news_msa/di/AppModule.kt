package com.msa.news_msa.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.msa.news_msa.data.local.NewsDatabase
import com.msa.news_msa.data.local.dao.NewsArticleDao
import com.msa.news_msa.data.remote.NewsApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providesOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .addNetworkInterceptor(StethoInterceptor())
           /* .addInterceptor { chain ->
                val request = chain.request().newBuilder().addHeader("X-Api-Key", BuildConfig.NEWS_API_KEY).build()
                chain.proceed(request)
            }*/
            .build()
    }

    @Singleton
    @Provides
    fun provideNewsService(): NewsApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideArticleDb(app:Application): NewsDatabase {
        return Room
                .databaseBuilder(app, NewsDatabase::class.java, "news.db")
                .fallbackToDestructiveMigration()
                .build()
    }

    @Singleton
    @Provides
    fun provideArticleDao(db: NewsDatabase) : NewsArticleDao = db.newsArticlesDao()

    private const val BASE_URL = "https://newsapi.org/v2/"
}