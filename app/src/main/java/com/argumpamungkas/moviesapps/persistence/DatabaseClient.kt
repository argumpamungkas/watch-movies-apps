package com.argumpamungkas.moviesapps.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.argumpamungkas.moviesapps.model.ItemMovieDetailResponse
import com.argumpamungkas.moviesapps.network.DatabaseDao
import com.argumpamungkas.moviesapps.util.GenreConverter
import com.argumpamungkas.moviesapps.util.ProductionConverter

@Database(
    entities = [ItemMovieDetailResponse::class],
    version = 1,
    exportSchema = false
)

@TypeConverters(GenreConverter::class, ProductionConverter::class)
abstract class DatabaseClient: RoomDatabase() {
    abstract val databaseDao: DatabaseDao
}