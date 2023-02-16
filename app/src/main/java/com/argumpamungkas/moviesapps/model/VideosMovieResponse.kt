package com.argumpamungkas.moviesapps.model

data class VideosMovieResponse(
    val id: Int,
    val results: List<VideosMovieModel>?
)

data class VideosMovieModel(
    val id: String,
    val name: String,
    val key: String,
    val site: String
)
