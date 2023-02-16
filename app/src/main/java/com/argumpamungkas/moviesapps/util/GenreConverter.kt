package com.argumpamungkas.moviesapps.util

import androidx.room.TypeConverter
import com.argumpamungkas.moviesapps.model.GenresMovie
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object GenreConverter {
    @TypeConverter
    fun fromGenreList(genres: List<GenresMovie>): String {
        return Gson().toJson(genres)
    }

    @TypeConverter
     fun toGenreList(genreString: String): List<GenresMovie>?{
        val listType = object : TypeToken<List<GenresMovie>>() {}.type
        return Gson().fromJson(genreString, listType)
    }
}