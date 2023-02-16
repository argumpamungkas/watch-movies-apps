package com.argumpamungkas.moviesapps.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "movie_favorite")
data class ItemMovieDetailResponse(
    @PrimaryKey
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
): Serializable

data class GenresMovie(
    val id: Int,
    val name: String
): Serializable

data class ProductCompanies(
    val id: Int,
    val name: String
): Serializable