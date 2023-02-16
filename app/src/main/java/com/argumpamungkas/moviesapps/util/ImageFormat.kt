package com.argumpamungkas.moviesapps.util

import android.widget.ImageView
import com.argumpamungkas.moviesapps.model.Constant
import com.bumptech.glide.Glide

fun imageFormat(urlPath: String ,imageView: ImageView){
    val path = Constant.POSTER_PATH + urlPath
    Glide.with(imageView)
        .load(path)
        .placeholder(shimmer())
        .into(imageView)
}