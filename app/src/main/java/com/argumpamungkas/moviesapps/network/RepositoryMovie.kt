package com.argumpamungkas.moviesapps.network

import com.argumpamungkas.moviesapps.model.ItemMovieDetailResponse
import com.argumpamungkas.moviesapps.model.ItemMovieResponse
import com.argumpamungkas.moviesapps.model.ItemMovieSearchResponse
import org.koin.dsl.module

val moduleRepository = module {
    factory { RepositoryMovie(get()) }
}

class RepositoryMovie(
    private val api: ApiEndpoint
) {

    suspend fun fetchMoviesUpcoming(
        api_key: String,
        page: Int
    ): ItemMovieResponse {
        return api.getMoviesUpcoming(api_key, page)
    }

    suspend fun fetchMoviesPopular(
        api_key: String,
        page: Int
    ): ItemMovieResponse {
        return api.getMoviesPopular(api_key, page)
    }

    suspend fun fetchMoviesTopRated(
        api_key: String,
        page: Int
    ): ItemMovieResponse {
        return api.getMoviesTopRated(api_key, page)
    }

    suspend fun fetchMoviesNowPlaying(
        api_key: String,
        page: Int
    ): ItemMovieResponse {
        return api.getMoviesNowPlaying(api_key, page)
    }

    suspend fun fetchSearchMovie(
        api_key: String,
        query: String,
        page: Int
    ): ItemMovieSearchResponse {
        return api.searchMovie(api_key, query, page)
    }

    suspend fun fetchMovieDetail(
        movie_id: Int,
        api_key: String
    ): ItemMovieDetailResponse {
        return api.getMovieDetail(movie_id, api_key)
    }

}