package com.argumpamungkas.moviesapps.util

import java.text.SimpleDateFormat
import java.util.*

fun dateFormat(date: String): String {
    return if (date.isEmpty())""
    else {
        val currentFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val toFormat = SimpleDateFormat("dd - MM - yyyy", Locale.getDefault())
        val dateParse = currentFormat.parse(date)
        toFormat.format(dateParse as Date)
    }
}

fun justYear(date: String): String {
    return if (date.isEmpty())""
    else {
        val currentFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val toFormat = SimpleDateFormat("yyyy", Locale.getDefault())
        val dateParse = currentFormat.parse(date)
        toFormat.format(dateParse as Date)
    }
}