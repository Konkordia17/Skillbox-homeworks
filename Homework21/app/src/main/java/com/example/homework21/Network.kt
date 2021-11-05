package com.example.homework21

import okhttp3.Call
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor

object Network {

    val client = OkHttpClient.Builder()
        .addNetworkInterceptor(HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY))
        .build()

    //        http://www.omdbapi.com/?apikey=[yourkey]&s=

    fun getSearchMovieCall(text:String): Call {
    val url = HttpUrl.Builder()
        .scheme("http")
        .host("www.omdbapi.com")
        .addQueryParameter("apikey", API_KEY)
        .addQueryParameter("s", text)
        .build()

    val request = Request.Builder()
        .get()
        .build()
return client.newCall(request)
    }
}