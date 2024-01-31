package com.mbh.moviebrowser.features.movieList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mbh.moviebrowser.domain.model.MovieDomain
import com.mbh.moviebrowser.store.MovieStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val store: MovieStore
) : ViewModel() {

    data class UiState(
        val movies: List<MovieDomain> = emptyList()
    )

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState

    init {
        viewModelScope.launch {
            store.moviesWatcher.collect{ movies ->
                _uiState.update {
                    it.copy(
                        movies = movies
                    )
                }
            }
        }
    }
}
