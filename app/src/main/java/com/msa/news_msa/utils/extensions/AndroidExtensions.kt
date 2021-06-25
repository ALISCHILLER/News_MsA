package com.msa.news_msa.utils.extensions

import android.widget.ImageView
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.msa.news_msa.R

inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> Unit) {
    val fragmentTransaction = beginTransaction()
    fragmentTransaction.func()
    fragmentTransaction.commit()
}

fun ImageView.loadImageUrl(url: String?, requestOptions: RequestOptions) {
    Glide.with(this.context)
        .load(url)
        .placeholder(R.drawable.image_placeholder)
        .apply(requestOptions)
        .into(this)
}