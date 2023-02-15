package com.argumpamungkas.moviesapps.network

import com.argumpamungkas.moviesapps.model.ItemMovieResponse
import com.argumpamungkas.moviesapps.model.ItemMovieSearchModel
import com.argumpamungkas.moviesapps.model.ItemMovieSearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiEndpoint {

    @GET("movie/popular")
    suspend fun getMoviesPopular(
        @Query("api_key") api_key: String,
        @Query("page") page: Int
    ): ItemMovieResponse

    @GET("movie/top_rated")
    suspend fun getMoviesTopRated(
        @Query("api_key") api_key: String,
        @Query("page") page: Int
    ): ItemMovieResponse

    @GET("movie/now_playing")
    suspend fun getMoviesNowPlaying(
        @Query("api_key") api_key: String,
        @Query("page") page: Int
    ): ItemMovieResponse

    @GET("movie/upcoming")
    suspend fun getMoviesUpcoming(
        @Query("api_key") api_key: String,
        @Query("page") page: Int
    ): ItemMovieResponse

    @GET("search/movie")
    suspend fun searchMovie(
        @Query("api_key") api_key: String,
        @Query("query") query: String,
        @Query("page") page: Int
    ): ItemMovieSearchResponse

}