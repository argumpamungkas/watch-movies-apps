package com.argumpamungkas.moviesapps.ui.detail.overview

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.argumpamungkas.moviesapps.BuildConfig
import com.argumpamungkas.moviesapps.model.Constant
import com.argumpamungkas.moviesapps.model.ItemMovieDetailResponse
import com.argumpamungkas.moviesapps.network.RepositoryMovie
import com.argumpamungkas.moviesapps.persistence.SharedPreferences
import com.argumpamungkas.moviesapps.ui.detail.DetailMovieActivity
import kotlinx.coroutines.launch
import org.koin.dsl.module

val moduleOverviewViewModel = module {
    factory { OverviewViewModel(get(), get()) }
}

class OverviewViewModel(
    private val repositoryMovie: RepositoryMovie,
    application: Application
): AndroidViewModel(application) {

    val sharedPref = SharedPreferences(application)

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
            } catch (e:Exception){
                e.printStackTrace()
            }
        }
    }
}
