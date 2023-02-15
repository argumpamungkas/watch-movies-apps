package com.argumpamungkas.moviesapps.network

import com.argumpamungkas.moviesapps.model.Constant
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val moduleNetwork = module {
    single { provideOkHttp() }
    single { provideRetrofit(get()) }
    single { provideApi(get()) }
}

private fun provideOkHttp(): OkHttpClient {
    return OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .build()
}

private fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(Constant.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
}

private fun provideApi(retrofit: Retrofit): ApiEndpoint = retrofit.create(ApiEndpoint::class.java)