package com.meet.movieexplorer.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.meet.movieexplorer.data.model.Movie
import com.meet.movieexplorer.data.repository.MovieRepository
import com.meet.movieexplorer.utils.NetworkResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class MovieSearchUiState(
    val isLoading: Boolean = false,
    val movies: List<Movie> = emptyList(),
    val errorMessage: String? = null,
    val hasSearched: Boolean = false
)

class MovieSearchViewModel(
    private val repository: MovieRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(MovieSearchUiState())
    val uiState: StateFlow<MovieSearchUiState> = _uiState.asStateFlow()

    fun searchMovies(query: String) {
        if (query.isBlank()) {
            _uiState.value = _uiState.value.copy(
                errorMessage = "Please enter a movie name",
                hasSearched = true
            )
            return
        }

        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                isLoading = true,
                errorMessage = null,
                hasSearched = true
            )

            when (val result = repository.searchMovies(query)) {
                is NetworkResult.Success -> {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        movies = result.data,
                        errorMessage = null
                    )
                }
                is NetworkResult.Error -> {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        movies = emptyList(),
                        errorMessage = result.message
                    )
                }
                is NetworkResult.Loading -> {
                    // Optional: handle loading state explicitly
                }
            }
        }
    }

    fun clearError() {
        _uiState.value = _uiState.value.copy(errorMessage = null)
    }
}