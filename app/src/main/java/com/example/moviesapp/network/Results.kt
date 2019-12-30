package com.example.moviesapp.network

import com.squareup.moshi.Json

data class Results(
    val title: String
    , @Json(name = "release_date") val releaseDate: String
    , @Json(name = "poster_path") val posterPath: String
    , @Json(name = "vote_average") val voteAverage: String
    , @Json(name = "overview") val overView: String
    , val id: Int
)