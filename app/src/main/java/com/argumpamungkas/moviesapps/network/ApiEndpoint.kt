package com.argumpamungkas.moviesapps.network

import com.argumpamungkas.moviesapps.model.*
import retrofit2.http.GET
import retrofit2.http.Path
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

    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(
        @Path("movie_id") movie_id: Int,
        @Query("api_key") api_key: String
    ): ItemMovieDetailResponse

    @GET("movie/{movie_id}/videos")
    suspend fun getVideosMovie(
        @Path("movie_id") movie_id: Int,
        @Query("api_key") api_key: String
    ): VideosMovieResponse

}