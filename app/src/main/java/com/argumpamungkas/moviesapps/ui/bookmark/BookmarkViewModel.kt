package com.argumpamungkas.moviesapps.ui.bookmark

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.koin.dsl.module

val moduleBookmarkViewModel = module {
    factory { BookmarkViewModel() }
}

class BookmarkViewModel : ViewModel() {

    val titleToolbar = "Bookmark"

    private val _text = MutableLiveData<String>().apply {
        value = "This is Bookmark Fragment"
    }
    val text: LiveData<String> = _text
}