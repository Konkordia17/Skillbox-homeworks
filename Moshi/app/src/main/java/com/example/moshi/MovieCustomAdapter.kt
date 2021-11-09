package com.example.moshi

import com.squareup.moshi.FromJson
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.squareup.moshi.ToJson

class MovieCustomAdapter {
    @FromJson
    fun fromJson(customMovie: MovieInfoWrapper): Movie {
        return Movie(
            imdbID = customMovie.imdbID,
            title = customMovie.title,
            year = customMovie.year,
            rating = customMovie.rating,
            genre = customMovie.genre,
            poster = customMovie.poster,
            score = listToMap(customMovie.scores)
        )
    }

    @ToJson
    fun toJson(movie: Movie): MovieInfoWrapper {
        return MovieInfoWrapper(
            imdbID = movie.imdbID,
            title = movie.title,
            year = movie.year,
            rating = movie.rating,
            genre = movie.genre,
            poster = movie.poster,
            scores = movie.score.map { Score(it.key, it.value) }
        )
    }

    private fun listToMap(list: List<Score>): Map<String, String> {
        return list.map { it.source to it.value }.toMap()
    }

    @JsonClass(generateAdapter = true)
    data class MovieInfoWrapper(
        val imdbID: String = "",
        @Json(name = "Title")
        val title: String = "Фильм не найден",
        @Json(name = "Year")
        val year: Int = 0,
        @Json(name = "Rated")
        val rating: MovieRating = MovieRating.GENEERAL,
        @Json(name = "Genre")
        val genre: String = "",
        @Json(name = "Poster")
        val poster: String = "",
        @Json(name = "Ratings")
        val scores: List<Score>
    )
}