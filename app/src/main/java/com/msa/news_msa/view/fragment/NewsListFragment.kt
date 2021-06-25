package com.msa.news_msa.view.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.msa.news_msa.R
import com.msa.news_msa.databinding.NewsFragmentBinding
import com.msa.news_msa.utils.NewsConstants
import com.msa.news_msa.utils.ViewState
import com.msa.news_msa.utils.extensions.inTransaction
import com.msa.news_msa.utils.extensions.observeNotNull
import com.msa.news_msa.view.adapter.NewsListingAdapter
import com.msa.news_msa.viewModel.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
/**
 * A simple [Fragment] subclass.
 * Use the [NewsListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class NewsListFragment : Fragment() {


    //viewBinding
    private var _vBinding:NewsFragmentBinding ? = null
    private val vBinding get() = _vBinding!!

    // viewmodel store owner is Fragment
    private val newsViewModel: NewsViewModel by viewModels()
    private var cateogory: String = "Sports"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
     //   initVariables()
        _vBinding = NewsFragmentBinding.inflate(inflater, container, false)
        return vBinding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //mToolbar.title = cateogory
        val newsListAdapter = NewsListingAdapter(context) { position, action ->
            handleAction(position, action)
        }

        vBinding.rvNewsListing.apply {
            adapter = newsListAdapter
            layoutManager = LinearLayoutManager(context)
            val divider = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
            ContextCompat.getDrawable(context, R.drawable.news_divider)?.let {
                divider.setDrawable(
                    it
                )
            }
            addItemDecoration(divider)
        }
        newsViewModel.getNewsForCategory(cateogory)
        newsViewModel.getNewsLiveData().observeNotNull(this) { state ->
            when (state) {
                is ViewState.Success -> {
                  vBinding.rvProgress.visibility = View.GONE
                    newsListAdapter.submitList(state.data)
                }
                is ViewState.Loading -> vBinding.rvProgress.visibility = View.VISIBLE
                is ViewState.Error -> Log.d("RES: ", "Error...")
            }
        }

    }

    private fun handleAction(position: Int, action: Int) {
        when(action) {
            NewsConstants.OPEN_NEWS_DETAIL -> openNewsDetail(position)
            NewsConstants.BOOKMARK_NEWS_ITEM -> bookmarkArticle(position)
        }
    }

    private fun bookmarkArticle(position: Int) {
        ((newsViewModel.getNewsLiveData()
            .value as ViewState.Success).data[position]).let {
            it.isBookmarked = !it.isBookmarked
            newsViewModel.bookMarkArticle(it)
        }
    }
    private fun openNewsDetail(position: Int) {
        Timber.e("Item clicked $position + $cateogory ==> ")
        Timber.e(((newsViewModel.getNewsLiveData().value as ViewState.Success).data[position]).title)
        val bundle = Bundle().apply {
            putSerializable("detail", (newsViewModel.getNewsLiveData().value as ViewState.Success).data[position])
        }
        val newsDetailFragment = NewsDetail_Fragment.getInstance(bundle)
        activity?.supportFragmentManager?.inTransaction {
            addToBackStack(this@NewsListFragment.javaClass.name)
            add(R.id.content_frame, newsDetailFragment)
        }
    }

}