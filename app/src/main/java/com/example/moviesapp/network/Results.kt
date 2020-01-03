package com.example.moviesapp.network

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Results(
    val title: String
    , @Json(name = "release_date") val releaseDate: String
    , @Json(name = "poster_path") val posterPath: String
    , @Json(name = "vote_average") val voteAverage: String
    , @Json(name = "overview") val overView: String
    , val id: Int
) : Parcelable