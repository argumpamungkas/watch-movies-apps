package com.argumpamungkas.moviesapps.util

import androidx.room.TypeConverter
import com.argumpamungkas.moviesapps.model.ProductCompanies
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object ProductionConverter {
    @TypeConverter
    fun fromGenreList(product: List<ProductCompanies>): String {
        return Gson().toJson(product)
    }

    @TypeConverter
    fun toGenreList(productString: String): List<ProductCompanies>?{
        val listType = object : TypeToken<List<ProductCompanies>>() {}.type
        return Gson().fromJson(productString, listType)
    }
}