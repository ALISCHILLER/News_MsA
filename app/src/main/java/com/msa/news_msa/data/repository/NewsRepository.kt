package com.msa.news_msa.data.repository

import com.msa.news_msa.BuildConfig
import com.msa.news_msa.data.local.dao.NewsArticleDao
import com.msa.news_msa.data.local.model.NewsArticleModel
import com.msa.news_msa.data.remote.NewsApiService
import com.msa.news_msa.data.remote.model.NewsResponse
import com.msa.news_msa.utils.ViewState
import com.msa.news_msa.utils.extensions.httpError
import com.msa.news_msa.utils.mapper.NewsMapper
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject


class NewsRepository @Inject constructor(
    private val newsArticleDao: NewsArticleDao,
    private val newsApiService: NewsApiService
)  : NewsUseCaseInterface, NewsMapper {
    override fun getNewsArticlesByCategory(category: String) : Flow<ViewState<List<NewsArticleModel>>> = flow {

        emit(ViewState.loading())

        val newsResponseFromServer = getTopHeadLinesForCategory(category)
        newsResponseFromServer.body()?.articles?.toCache(category)?.let(newsArticleDao::insert)

        val cachedNews = newsArticleDao.getAllArticlesForCategory(category)
        emitAll(cachedNews.map { ViewState.success(it) })

    }.flowOn(Dispatchers.IO)

    override suspend fun getTopHeadLinesForCategory(category: String): Response<NewsResponse> {
        return try {
            newsApiService.getTopHeadLinesForCategory(category +
                    "", "in", BuildConfig.NEWS_API_KEY)
        } catch (e: Exception) {
            httpError(404)
        }
    }

    override suspend fun bookMarkArticle(articleModel: NewsArticleModel) {
        withContext(Dispatchers.IO) {
            newsArticleDao.updateArticle(articleModel)
        }
    }
}
@Module
@InstallIn(SingletonComponent::class)
interface NewsRepositoryModule {
    @Binds fun it(it: NewsRepository) : NewsUseCaseInterface
}