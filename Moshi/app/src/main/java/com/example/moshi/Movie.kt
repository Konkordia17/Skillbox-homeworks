package com.example.moshi

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Movie(
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
    val score: Map<String, String> = emptyMap()
)
