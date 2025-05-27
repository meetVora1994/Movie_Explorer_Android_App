package com.meet.movieexplorer.data.api

import com.meet.movieexplorer.data.model.SearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {
    @GET("/")
    suspend fun searchMovies(
        @Query("apikey") apiKey: String,
        @Query("s") searchQuery: String,
        @Query("type") type: String = "movie"
    ): Response<SearchResponse>
    
    companion object {
        const val BASE_URL = "https://www.omdbapi.com/"
        const val API_KEY = "9e6f5113"
    }
}