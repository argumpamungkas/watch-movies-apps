package com.argumpamungkas.moviesapps.network

import androidx.lifecycle.LiveData
import androidx.room.*
import com.argumpamungkas.moviesapps.model.ItemMovieDetailResponse

@Dao
interface DatabaseDao {

    @Query("SELECT * FROM movie_favorite ORDER BY title")
    fun findAll(): LiveData<List<ItemMovieDetailResponse>>

    @Query("SELECT COUNT(*) FROM movie_favorite WHERE id=:id")
    suspend fun findMovie(id: Int): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMovie(movie: ItemMovieDetailResponse)

    @Delete
    suspend fun removeMovie(movie: ItemMovieDetailResponse)
}