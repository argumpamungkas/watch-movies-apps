package com.argumpamungkas.moviesapps.ui.moviescategory

import android.util.Log
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
    private val repositoryMovie: RepositoryMovie
): ViewModel() {

    private val _listMovieUpcoming = MutableLiveData<ItemMovieResponse>()
    val listMovieUpcoming : LiveData<ItemMovieResponse> = _listMovieUpcoming

    private val _listMoviePopular = MutableLiveData<ItemMovieResponse>()
    val listMoviePopular : LiveData<ItemMovieResponse> = _listMoviePopular

    private val _listMovieTopRated = MutableLiveData<ItemMovieResponse>()
    val listMovieTopRated : LiveData<ItemMovieResponse> = _listMovieTopRated

    private val _listMovieNowPlaying = MutableLiveData<ItemMovieResponse>()
    val listMovieNowPlaying : LiveData<ItemMovieResponse> = _listMovieNowPlaying

    val isFirstLoad by lazy { MutableLiveData<Boolean>(true) }

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _loadingMore = MutableLiveData<Boolean>()
    val loadingMore: LiveData<Boolean> = _loadingMore

    var page = 1
    var totalPages = 1

    fun fetchMovieUpcoming(){
        if (isFirstLoad.value == true){
            _loading.value = true
        } else {
            _loadingMore.value = true
        }
        viewModelScope.launch {
            try {
                val response = repositoryMovie.fetchMoviesUpcoming(BuildConfig.API_KEY, page)
                _listMovieUpcoming.value = response
                totalPages = response.total_pages
                page++
                _loading.value = false
                _loadingMore.value = false
                isFirstLoad.value = false
            }catch (e:Exception){
                e.printStackTrace()
                _loading.value = false
                _loadingMore.value = false
                isFirstLoad.value = false
            }
        }
    }

    fun fetchMoviePopular(){
        if (isFirstLoad.value == true){
            _loading.value = true
        } else {
            _loadingMore.value = true
        }
        viewModelScope.launch {
            try {
                val response = repositoryMovie.fetchMoviesPopular(BuildConfig.API_KEY, page)
                _listMoviePopular.value = response
                totalPages = response.total_pages
                page++
                _loading.value = false
                _loadingMore.value = false
                isFirstLoad.value = false
            }catch (e:Exception){
                e.printStackTrace()
                _loading.value = false
                _loadingMore.value = false
                isFirstLoad.value = false
            }
        }
    }

    fun fetchMovieTopRated(){
        if (isFirstLoad.value == true){
            _loading.value = true
        } else {
            _loadingMore.value = true
        }
        viewModelScope.launch {
            try {
                val response = repositoryMovie.fetchMoviesTopRated(BuildConfig.API_KEY, page)
                _listMovieTopRated.value = response
                totalPages = response.total_pages
                page++
                _loading.value = false
                _loadingMore.value = false
                isFirstLoad.value = false
            }catch (e:Exception){
                e.printStackTrace()
                _loading.value = false
                _loadingMore.value = false
                isFirstLoad.value = false
            }
        }
    }

    fun fetchMovieNowPlaying(){
        if (isFirstLoad.value == true){
            _loading.value = true
        } else {
            _loadingMore.value = true
        }
        viewModelScope.launch {
            try {
                val response = repositoryMovie.fetchMoviesNowPlaying(BuildConfig.API_KEY, page)
                _listMovieNowPlaying.value = response
                totalPages = response.total_pages
                page++
                _loading.value = false
                _loadingMore.value = false
                isFirstLoad.value = false
            }catch (e:Exception){
                e.printStackTrace()
                _loading.value = false
                _loadingMore.value = false
                isFirstLoad.value = false
            }
        }
    }

}