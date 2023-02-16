package com.argumpamungkas.moviesapps.ui.detail.videos

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.argumpamungkas.moviesapps.BuildConfig
import com.argumpamungkas.moviesapps.model.Constant
import com.argumpamungkas.moviesapps.model.VideosMovieResponse
import com.argumpamungkas.moviesapps.network.RepositoryMovie
import com.argumpamungkas.moviesapps.persistence.SharedPreferences
import kotlinx.coroutines.launch
import org.koin.dsl.module

val moduleVideosViewModel = module {
    factory { VideosViewModel(get(), get()) }
}

class VideosViewModel(
    private val repositoryMovie: RepositoryMovie,
    application: Application
): AndroidViewModel(application) {

    private val sharedPref = SharedPreferences(application)

    private val _videosMovie = MutableLiveData<VideosMovieResponse>()
    val videosMovie : LiveData<VideosMovieResponse> = _videosMovie

    init {
        fetchVideosMovie()
    }

    private fun fetchVideosMovie(){
        viewModelScope.launch {
            try {
                val movieId = sharedPref.getMovieId(Constant.MOVIE_ID)
                _videosMovie.value = repositoryMovie.fetchVideosMovie(movieId, BuildConfig.API_KEY)
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }
}