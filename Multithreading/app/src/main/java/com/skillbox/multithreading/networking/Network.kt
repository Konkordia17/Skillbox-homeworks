package com.skillbox.multithreading.networking

import android.os.Handler
import android.os.Looper
import com.skillbox.multithreading.threading.ThreadingFragment
import okhttp3.Authenticator
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.io.IOException
import java.util.*

object Network {

    const val MOVIE_API_KEY = "885b81c6"

    private val mainHandler = Handler(Looper.getMainLooper())

    private val client = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        })
        .build()

    private val retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl("http://www.omdbapi.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getMovieById(movieId: String): Movie? {
        return try {
            api().getMovieById(movieId, MOVIE_API_KEY).execute().body()
        } catch (e: IOException) {
            // Проблемы с интернет соединением
            null
        }
    }

    fun fetchMovies(movieIds: List<String>, onMoviesFetched: (movies: List<Movie>) -> Unit) {
        Thread {
            val allMovies = Collections.synchronizedList(mutableListOf<Movie>())
            val threads = movieIds.chunked(1).map { movieChunk ->
                Thread {
                    val movies = movieChunk.mapNotNull { movieId ->
                        getMovieById(movieId)
                    }
                    allMovies.addAll(movies)
                }
            }
            threads.forEach { it.start() }
            threads.forEach { it.join() }

            onMoviesFetched(allMovies)

        }.start()

    }

    private fun api(): MovieApi {
        return retrofit.create()
    }
}