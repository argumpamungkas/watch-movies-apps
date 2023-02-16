package com.argumpamungkas.moviesapps.util

fun runtimeFormat(runtime: Int): String {
    val hours = runtime / 60
    val minutes = runtime % 60
    return "${hours}h ${minutes}m"
}