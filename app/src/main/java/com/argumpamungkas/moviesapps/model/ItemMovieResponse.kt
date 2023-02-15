package com.argumpamungkas.moviesapps.model

data class ItemMovieResponse(
    var results: List<ItemMovieModel>,
    var total_pages: Int
)

data class ItemMovieModel(
    var id: Int,
    var title: String,
    var poster_path: String,
    val genres: List<GenresMovie>?,
    val vote_average: Double?
)