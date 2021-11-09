package com.example.moshi

import android.util.Log
import com.squareup.moshi.Moshi
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException
import java.lang.Exception

class SearchMovieRepository {
    fun searchMovie(
        title: String,
        callback: (Movie) -> Unit,
        errorCallback: (e: Throwable) -> Unit
    ): Call {
        return Network.getSearchMovieCall(title).apply {
            enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    Log.d("Server", "execute request error = ${e.message}", e)
                    errorCallback(e)
                }

                override fun onResponse(call: Call, response: Response) {
                    if (response.isSuccessful) {
                        val responseString = response.body?.string().orEmpty()
                        val movies = parseMovieResponse(responseString)
                        if (movies != null) {
                            callback(movies)
                        } else {
                            callback(Movie())
                        }
                    } else {
                        errorCallback(Throwable())
                    }
                }
            })
        }
    }

    private fun parseMovieResponse(responseBodyString: String): Movie? {
        val moshi = Moshi.Builder()
            .add(MovieCustomAdapter())
            .build()
        val adapter = moshi.adapter(Movie::class.java).nonNull()
        try {
            val movie = adapter.fromJson(responseBodyString)
            return movie
        } catch (e: Exception) {
            Log.d("Ser", "dggg")
        }
        return null
    }

    companion object {
        const val MOVIE_API_KEY = "885b81c6"
    }
}
