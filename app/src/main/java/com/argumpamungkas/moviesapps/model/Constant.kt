package com.argumpamungkas.moviesapps.model

import com.argumpamungkas.moviesapps.R

class Constant {

    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/"
        const val POSTER_PATH = "https://www.themoviedb.org/t/p/original"
        const val GENRES = "https://api.themoviedb.org/3/discover/movie?api_key=dfd9bce9f7fb40b772f01616b4d2d78c&with_genres=Action"
        const val MOVIES_CATEGORY = "movies_category"
        const val MOVIE_ID = "movie_id"
        const val MOVIE_TITLE = "movie_title"
        const val KEY_VIDEOS = "key_videos"

        val TAB_TITLES = intArrayOf(
            R.string.overview,
            R.string.videos
        )
    }

}