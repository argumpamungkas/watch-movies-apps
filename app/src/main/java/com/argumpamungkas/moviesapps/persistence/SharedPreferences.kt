package com.argumpamungkas.moviesapps.persistence

import android.content.Context
import android.content.SharedPreferences

class SharedPreferences(var context: Context) {
    private val PREF_NAME = "save_id"
    private val sharedPref: android.content.SharedPreferences
    private val editor: SharedPreferences.Editor

    init {
        sharedPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        editor = sharedPref.edit()
    }

    fun putMovieId(key: String, value: Int){
        editor.putInt(key, value)
            .apply()
    }

    fun getMovieId(key: String) : Int {
        return sharedPref.getInt(key, 0)
    }

    fun clear(){
        editor.clear()
            .apply()
    }
}