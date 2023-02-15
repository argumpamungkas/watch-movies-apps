package com.argumpamungkas.moviesapps.ui.moviescategory

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.argumpamungkas.moviesapps.BuildConfig
import com.argumpamungkas.moviesapps.model.ItemMovieResponse
import com.argumpamungkas.moviesapps.network.RepositoryMovie
import kotlinx.coroutines.launch
import org.koin.dsl.module

val moduleMoviesCategoryViewModel = module {
    factory { MoviesCategoryViewModel(get()) }
}

class MoviesCategoryViewModel(
    val repositoryMovie: RepositoryMovie
): ViewModel() {

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

    fun fetchMovieUpcoming(){
        _loading.value = true
        viewModelScope.launch {
            try {
                _listMovieUpcoming.value = repositoryMovie.fetchMoviesUpcoming(BuildConfig.API_KEY, 1)
                _loading.value = false
            }catch (e:Exception){
                e.printStackTrace()
                _loading.value = false
            }
        }
    }

    fun fetchMoviePopular(){
        _loading.value = true
        viewModelScope.launch {
            try {
                _listMoviePopular.value = repositoryMovie.fetchMoviesPopular(BuildConfig.API_KEY, 1)
                _loading.value = false
            }catch (e:Exception){
                e.printStackTrace()
                _loading.value = false
            }
        }
    }

    fun fetchMovieTopRated(){
        _loading.value = true
        viewModelScope.launch {
            try {
                _listMovieTopRated.value = repositoryMovie.fetchMoviesTopRated(BuildConfig.API_KEY, 1)
                _loading.value = false
            }catch (e:Exception){
                e.printStackTrace()
                _loading.value = false
            }
        }
    }

    fun fetchMovieNowPlaying(){
        _loading.value = true
        viewModelScope.launch {
            try {
                _listMovieNowPlaying.value = repositoryMovie.fetchMoviesNowPlaying(BuildConfig.API_KEY, 1)
                _loading.value = false
            }catch (e:Exception){
                e.printStackTrace()
                _loading.value = false
            }
        }
    }

}