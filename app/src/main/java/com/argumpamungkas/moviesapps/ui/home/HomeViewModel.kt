package com.argumpamungkas.moviesapps.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.argumpamungkas.moviesapps.BuildConfig
import com.argumpamungkas.moviesapps.model.ItemMovieResponse
import com.argumpamungkas.moviesapps.network.RepositoryMovie
import kotlinx.coroutines.launch
import org.koin.dsl.module

val moduleHomeViewModel = module {
    factory { HomeViewModel(get()) }
}

class HomeViewModel(
    private val repositoryMovie: RepositoryMovie
) : ViewModel() {

    val titleToolbar = "Watch MTv"

    private val _listMovieUpcoming = MutableLiveData<ItemMovieResponse>()
    val listMovieUpcoming : LiveData<ItemMovieResponse> = _listMovieUpcoming

    private val _listMoviePopular = MutableLiveData<ItemMovieResponse>()
    val listMoviePopular : LiveData<ItemMovieResponse> = _listMoviePopular

    private val _listMovieTopRated = MutableLiveData<ItemMovieResponse>()
    val listMovieTopRated : LiveData<ItemMovieResponse> = _listMovieTopRated

    private val _listMovieNowPlaying = MutableLiveData<ItemMovieResponse>()
    val listMovieNowPlaying : LiveData<ItemMovieResponse> = _listMovieNowPlaying

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    init {
        fetchMovies()
    }

    private fun fetchMovies(){
        _loading.value = true
        viewModelScope.launch {
            try {
                _listMovieUpcoming.value = repositoryMovie.fetchMoviesUpcoming(BuildConfig.API_KEY, 1)
                _listMoviePopular.value = repositoryMovie.fetchMoviesPopular(BuildConfig.API_KEY, 1)
                _listMovieTopRated.value = repositoryMovie.fetchMoviesTopRated(BuildConfig.API_KEY, 1)
                _listMovieNowPlaying.value = repositoryMovie.fetchMoviesNowPlaying(BuildConfig.API_KEY, 1)
                _loading.value = false
            } catch (e: Exception) {
                e.message
                _loading.value = false
            }
        }
    }


}