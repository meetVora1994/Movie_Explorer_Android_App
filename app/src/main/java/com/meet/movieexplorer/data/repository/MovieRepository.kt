package com.meet.movieexplorer.data.repository

import com.meet.movieexplorer.data.api.MovieApi
import com.meet.movieexplorer.data.model.Movie
import com.meet.movieexplorer.utils.NetworkResult

class MovieRepository(private val movieApi: MovieApi) {

    suspend fun searchMovies(query: String): NetworkResult<List<Movie>> {
        return try {
            val response = movieApi.searchMovies(
                apiKey = MovieApi.API_KEY,
                searchQuery = query
            )

            if (response.isSuccessful && response.body()?.response == "True") {
                val movies = response.body()?.movies ?: emptyList()
                NetworkResult.Success(movies)
            } else {
                val error = response.body()?.error ?: "Unknown error"
                NetworkResult.Error(error)
            }
        } catch (e: Exception) {
            NetworkResult.Error(e.message ?: "An exception occurred")
        }
    }
}