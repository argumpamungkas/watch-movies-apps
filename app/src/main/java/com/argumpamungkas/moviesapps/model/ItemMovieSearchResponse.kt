package com.argumpamungkas.moviesapps.model

data class ItemMovieSearchResponse(
    var results: List<ItemMovieSearchModel>,
    var total_pages: Int
)

data class ItemMovieSearchModel(
    val id: Int,
    val title: String,
    val overview: String,
    val poster_path: String,
    val vote_average: Double
)