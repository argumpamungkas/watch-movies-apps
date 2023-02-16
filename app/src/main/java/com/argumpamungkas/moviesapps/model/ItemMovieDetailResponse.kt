package com.argumpamungkas.moviesapps.model

data class ItemMovieDetailResponse(
    val id: Int,
    val title: String,
    val overview: String,
    val backdrop_path: String,
    val poster_path: String,
    val release_date: String,
    val runtime: Int,
    val genres: List<GenresMovie>,
    val production_companies: List<ProductCompanies>,
    val vote_average: Double
)

data class GenresMovie(
    val id: Int,
    val name: String
)

data class ProductCompanies(
    val id: Int,
    val name: String
)