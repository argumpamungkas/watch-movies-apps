package com.argumpamungkas.moviesapps.ui.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.argumpamungkas.moviesapps.BuildConfig
import com.argumpamungkas.moviesapps.model.Constant
import com.argumpamungkas.moviesapps.model.ItemMovieDetailResponse
import com.argumpamungkas.moviesapps.network.RepositoryMovie
import com.argumpamungkas.moviesapps.persistence.SharedPreferences
import kotlinx.coroutines.launch
import org.koin.dsl.module

val moduleDetailMovieViewModel = module {
    factory { DetailMovieViewModel(get(), get()) }
}

class DetailMovieViewModel(
    private val repositoryMovie: RepositoryMovie,
    application: Application
): AndroidViewModel(application) {

    private val sharedPref = SharedPreferences(application)

    private val _movieDetail = MutableLiveData<ItemMovieDetailResponse>()
    val movieDetail : LiveData<ItemMovieDetailResponse> = _movieDetail

    init {
        fetchMovieDetail()
    }

    private fun fetchMovieDetail(){
        viewModelScope.launch {
            try {
                val movieId = sharedPref.getMovieId(Constant.MOVIE_ID)
                _movieDetail.value = repositoryMovie.fetchMovieDetail(movieId, BuildConfig.API_KEY)
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        sharedPref.clear()
    }
}