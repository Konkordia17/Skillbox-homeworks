package com.example.moshi

import okhttp3.Call
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor

object Network {

    private val client = OkHttpClient.Builder()
        .addNetworkInterceptor(CustomHeaderInterceptor())
        .addNetworkInterceptor(
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        )
        .build()

    fun getSearchMovieCall(title: String): Call {
        val url = HttpUrl.Builder()
            .scheme("http")
            .host("www.omdbapi.com")
            .addQueryParameter("t", title)
            .build()

        val request = Request.Builder()
            .get()
            .url(url)
            .build()
        return client.newCall(request)
    }
}
