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

    private val _listMovie = MutableLiveData<ItemMovieSearchResponse>()
    val listMovie: LiveData<ItemMovieSearchResponse> = _listMovie

    private val _message = MutableLiveData<String>()
    val message: LiveData<String> = _message

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _loadingMore = MutableLiveData<Boolean>()
    val loadingMore: LiveData<Boolean> = _loadingMore

    var pageMovie = 1
    var totalPages = 1

    fun fetchSearchMovie(query: String, page: Int, firstLoad: Boolean) {
        if (!firstLoad) {
            fetchMovie(query, pageMovie)
        } else {
            pageMovie = 1
            fetchMovie(query, page)
        }
    }

    private fun fetchMovie(query: String, currentPage: Int) {
        if (currentPage == 1) {
            _loading.value = true
        } else {
            _loadingMore.value = true
        }
        viewModelScope.launch {
            try {
                val response =
                    repositoryMovie.fetchSearchMovie(BuildConfig.API_KEY, query, currentPage)
                _listMovie.value = response
                totalPages = response.total_pages
                pageMovie++
                _loading.value = false
                _loadingMore.value = false
            } catch (e: Exception) {
                _message.value = "${e.message}"
                _loadingMore.value = false
            }
        }
    }
}