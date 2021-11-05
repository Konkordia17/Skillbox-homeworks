package com.example.networking

import okhttp3.Interceptor
import okhttp3.Response

class CustomHeaderInterceptor(
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val origUrl = originalRequest.url
        val url = origUrl.newBuilder()
            .addQueryParameter("apiKey", SearchMovieRepository.MOVIE_API_KEY)
            .build()

        val request = originalRequest.newBuilder().url(url).build()

        return chain.proceed(request)
    }
}