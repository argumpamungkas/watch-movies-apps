package com.argumpamungkas.moviesapps.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.argumpamungkas.moviesapps.BuildConfig
import com.argumpamungkas.moviesapps.model.ItemMovieSearchResponse
import com.argumpamungkas.moviesapps.network.RepositoryMovie
import kotlinx.coroutines.launch
import org.koin.dsl.module


val moduleSearchViewModel = module {
    factory { SearchViewModel(get()) }
}

class SearchViewModel(
    private val repositoryMovie: RepositoryMovie
) : ViewModel() {

    private val _listUser = MutableLiveData<ItemMovieSearchResponse>()
    val listUser : LiveData<ItemMovieSearchResponse> = _listUser

    private val _message = MutableLiveData<String>()
    val message : LiveData<String> = _message

    private val _loading = MutableLiveData<Boolean>()
    val loading : LiveData<Boolean> = _loading

    fun fetchSearchMovie(query: String){
        _loading.value = true
        viewModelScope.launch {
            try {
                _listUser.value = repositoryMovie.fetchSearchMovie(BuildConfig.API_KEY, query, 1)
                _loading.value = false
            }catch (e:Exception){
                _message.value = "${e.message}"
                _loading.value = false
            }
        }
    }
}