package com.example.homework21

import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

class MovieRepository {
    fun searchMovie(text: String, callback: (List<RemoteMovie>) -> Unit) {


//        val client = OkHttpClient()
//        val movieList = listOf(
//            RemoteMovie(
//                "tt0076759",
//                "Star  Wars",
//                "1977",
//                "121 min"
//            )
//        )
        Thread {
            try {
                val response = Network.getSearchMovieCall(text).execute()
                val responseString = response.body?.string().orEmpty()
                val movies = parseMovieResponse(responseString)
                callback(movies)
            } catch (e: IOException) {
                callback(emptyList())
            }
        }
            .start()
    }


    private fun parseMovieResponse(responseBodyString: String): List<RemoteMovie> {
        return try {
            val jsonObject = JSONObject(responseBodyString)
            val movieArray = jsonObject.getJSONArray("Search")
            (0 until movieArray.length()).map {index ->
                movieArray.getJSONObject(index)
            } .map {
                movieJsonObject ->
                val title = movieJsonObject.getString("Title")
                val year = movieJsonObject.getString("Year")
                val id = movieJsonObject.getString("imdbID")

                RemoteMovie(id, title, year)
            }
        } catch (e: JSONException) {
            return emptyList()
        }
    }
}
