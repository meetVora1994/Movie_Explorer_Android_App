package com.meet.movieexplorer.data.model

import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("Title")
    val title: String,
    @SerializedName("Year")
    val year: String,
    @SerializedName("Poster")
    val poster: String,
    @SerializedName("imdbID")
    val imdbId: String
)

data class SearchResponse(
    @SerializedName("Search")
    val movies: List<Movie>?,
    @SerializedName("totalResults")
    val totalResults: String?,
    @SerializedName("Response")
    val response: String,
    @SerializedName("Error")
    val error: String?
)