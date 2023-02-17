package com.argumpamungkas.moviesapps.util

import android.widget.ImageView
import com.argumpamungkas.moviesapps.model.Constant
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

fun imageFormat(urlPath: String ,imageView: ImageView){
    val path = Constant.POSTER_PATH + urlPath
    Glide.with(imageView)
        .load(path)
        .apply(RequestOptions().diskCacheStrategy(DiskCacheStrategy.NONE))
        .placeholder(shimmer())
        .into(imageView)
}