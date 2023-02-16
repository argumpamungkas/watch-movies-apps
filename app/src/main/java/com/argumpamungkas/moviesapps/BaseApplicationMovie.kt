package com.argumpamungkas.moviesapps

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.argumpamungkas.moviesapps.network.moduleNetwork
import com.argumpamungkas.moviesapps.network.moduleRepository
import com.argumpamungkas.moviesapps.ui.bookmark.moduleBookmarkFragment
import com.argumpamungkas.moviesapps.ui.bookmark.moduleBookmarkViewModel
import com.argumpamungkas.moviesapps.ui.detail.moduleDetailMovieActivity
import com.argumpamungkas.moviesapps.ui.detail.moduleDetailMovieViewModel
import com.argumpamungkas.moviesapps.ui.detail.overview.moduleOverviewFragment
import com.argumpamungkas.moviesapps.ui.detail.overview.moduleOverviewViewModel
import com.argumpamungkas.moviesapps.ui.home.moduleHomeFragment
import com.argumpamungkas.moviesapps.ui.home.moduleHomeViewModel
import com.argumpamungkas.moviesapps.ui.moduleSplashScreenActivity
import com.argumpamungkas.moviesapps.ui.moviescategory.moduleMoviesCategory
import com.argumpamungkas.moviesapps.ui.moviescategory.moduleMoviesCategoryViewModel
import com.argumpamungkas.moviesapps.ui.search.moduleSearchFragment
import com.argumpamungkas.moviesapps.ui.search.moduleSearchViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class BaseApplicationMovie: Application() {

    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        startKoin {
            androidLogger()
            androidContext(this@BaseApplicationMovie)
            modules(
                moduleNetwork,
                moduleRepository,
                moduleSplashScreenActivity,
                moduleHomeViewModel,
                moduleHomeFragment,
                moduleSearchViewModel,
                moduleSearchFragment,
                moduleBookmarkViewModel,
                moduleBookmarkFragment,
                moduleMoviesCategory,
                moduleMoviesCategoryViewModel,
                moduleDetailMovieActivity,
                moduleDetailMovieViewModel,
                moduleOverviewFragment,
                moduleOverviewViewModel
            )

        }
    }
}