package com.argumpamungkas.moviesapps.persistence

import android.app.Application
import androidx.room.Room
import com.argumpamungkas.moviesapps.network.DatabaseDao
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val moduleDatabase = module {
    single { provideDatabase(androidApplication()) }
    single { provideMovie(get()) }
}

fun provideDatabase(application: Application): DatabaseClient {
    return Room.databaseBuilder(
        application,
        DatabaseClient::class.java,
        "movieDB.db"
    ).fallbackToDestructiveMigration()
        .build()
}

fun provideMovie(databaseClient: DatabaseClient): DatabaseDao {
    return databaseClient.databaseDao
}