package com.argumpamungkas.moviesapps.network

import com.argumpamungkas.moviesapps.model.ItemMovieDetailResponse
import com.argumpamungkas.moviesapps.model.ItemMovieResponse
import com.argumpamungkas.moviesapps.model.ItemMovieSearchResponse
import com.argumpamungkas.moviesapps.model.VideosMovieResponse
import org.koin.dsl.module

val moduleRepository = module {
    factory { RepositoryMovie(get(), get()) }
}

class RepositoryMovie(
    private val api: ApiEndpoint,
    val db: DatabaseDao
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

    suspend fun fetchVideosMovie(
        movie_id: Int,
        api_key: String
    ): VideosMovieResponse {
        return api.getVideosMovie(movie_id, api_key)
    }

    suspend fun findMovie(movie: ItemMovieDetailResponse): Int = db.findMovie(movie.id)
    suspend fun addMovie(movie: ItemMovieDetailResponse) = db.addMovie(movie)
    suspend fun removeMovie(movie: ItemMovieDetailResponse) = db.removeMovie(movie)

}