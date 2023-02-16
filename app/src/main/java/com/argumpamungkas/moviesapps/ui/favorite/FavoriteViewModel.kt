package com.argumpamungkas.moviesapps.ui.favorite

import androidx.lifecycle.ViewModel
import com.argumpamungkas.moviesapps.network.RepositoryMovie
import org.koin.dsl.module

val moduleFavoriteViewModel = module {
    factory { FavoriteViewModel(get()) }
}

class FavoriteViewModel(
    repositoryMovie: RepositoryMovie
) : ViewModel() {

    val titleToolbar = "Favorite"

    val movie = repositoryMovie.db.findAll()
}