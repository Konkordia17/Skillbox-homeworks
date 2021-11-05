package com.example.networking

import android.util.Log
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

class SearchMovieRepository {
    fun searchMovie(
        title: String, year: String, type: String,
        callback: (List<Movie>) -> Unit,
        errorCallback: (e: Throwable) -> Unit
    ): Call {
        return Network.getSearchMovieCall(title, year, type).apply {
            enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    Log.d("Server", "execute request error = ${e.message}", e)
                    errorCallback(e)
                }

                override fun onResponse(call: Call, response: Response) {
                    if (response.isSuccessful) {
                        val responseString = response.body?.string().orEmpty()
                        val movies = parseMovieResponse(responseString)
                        callback(movies)
                    } else {
                        errorCallback(Throwable())
                    }
                }
            })
        }
    }

    private fun parseMovieResponse(responseBodyString: String): List<Movie> {
        try {
            val jsonObject = JSONObject(responseBodyString)
            val movieArray = jsonObject.getJSONArray("Search")
            val movies = (0 until movieArray.length()).map { index ->
                movieArray.getJSONObject(index)
            }.map { movieJsonObject ->
                val title = movieJsonObject.getString("Title")
                val type = movieJsonObject.getString("Type")
                val year = movieJsonObject.getString("Year")
                val idIMDB = movieJsonObject.getString("imdbID")
                val poster = movieJsonObject.getString("Poster")
                Movie(title, type, year, idIMDB, poster)
            }
            return movies
        } catch (e: JSONException) {
            Log.d("Server", "parse response error = ${e.message}", e)

            return emptyList()
        }
    }

    companion object {
        const val MOVIE_API_KEY = "885b81c6"
    }
}