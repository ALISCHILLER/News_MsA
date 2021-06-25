package com.msa.news_msa.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.msa.news_msa.R
import com.msa.news_msa.data.local.model.NewsArticleModel
import com.msa.news_msa.databinding.FragmentNewsDetailBinding
import com.msa.news_msa.utils.extensions.loadImageUrl
import dagger.hilt.android.AndroidEntryPoint


/**
 * A simple [Fragment] subclass.
 * Use the [NewsDetail_Fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class NewsDetail_Fragment : Fragment() {
    private var _vBinding: FragmentNewsDetailBinding? = null
    private val vBinding get() = _vBinding!!

    companion object {
        fun getInstance(bundle: Bundle) : NewsDetail_Fragment{
            val fragment = NewsDetail_Fragment()
            fragment.arguments = bundle
            return fragment
        }
    }
    lateinit var newsArticleModel: NewsArticleModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        initVariables()
        _vBinding = FragmentNewsDetailBinding.inflate(inflater, container, false)
        return vBinding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var requestOptions = RequestOptions()
        requestOptions = requestOptions.transform(CenterCrop())
        newsArticleModel.run {
            vBinding.publishedTimeTextView.text = publishedAt
            vBinding.newsImageView.loadImageUrl(urlToImage, requestOptions)
            vBinding.newsTitleTv.text = title
            vBinding.newsDescriptionTv.text = description
        }

    }
    private fun initVariables() {
        newsArticleModel = arguments?.getSerializable("detail") as NewsArticleModel
    }

}