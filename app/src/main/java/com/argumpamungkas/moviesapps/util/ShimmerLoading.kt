package com.argumpamungkas.moviesapps.util

import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerDrawable

fun shimmer(): ShimmerDrawable {
    val shimmer = Shimmer.AlphaHighlightBuilder()
        .setDuration(1000)
        .setBaseAlpha(0.7f)
        .setHighlightAlpha(0.6f)
        .setDirection(Shimmer.Direction.LEFT_TO_RIGHT)
        .setAutoStart(true)
        .build()

    val shimmerDraw = ShimmerDrawable()
    shimmerDraw.setShimmer(shimmer)
    return shimmerDraw
}