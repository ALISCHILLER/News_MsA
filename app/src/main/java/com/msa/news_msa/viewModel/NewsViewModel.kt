package com.msa.news_msa.viewModel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.msa.news_msa.data.local.model.NewsArticleModel
import com.msa.news_msa.data.repository.NewsUseCaseInterface
import com.msa.news_msa.utils.ViewState
import com.msa.news_msa.utils.extensions.launchWithCatchError
import dagger.hilt.android.lifecycle.HiltViewModel

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsUseCaseInteractor: NewsUseCaseInterface
): ViewModel() {

    var newsListLiveData: LiveData<ViewState<List<NewsArticleModel>>> = MutableLiveData()

    fun getNewsForCategory(category: String) {
        newsListLiveData = newsUseCaseInteractor
        .getNewsArticlesByCategory(category).asLiveData()
    }

    fun getNewsLiveData() = newsListLiveData

    fun bookMarkArticle(articleModel: NewsArticleModel) {
        viewModelScope.launchWithCatchError(block = {
            newsUseCaseInteractor.bookMarkArticle(articleModel)
        }, errorBlock = {
            Timber.e(it)
        })
        /*viewModelScope.launch(exceptionHandler) {
            newsUseCaseInteractor.bookMarkArticle(articleModel)
        }*/
    }


}