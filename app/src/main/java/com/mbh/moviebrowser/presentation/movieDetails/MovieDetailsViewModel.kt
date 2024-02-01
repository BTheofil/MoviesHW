package com.mbh.moviebrowser.presentation.movieDetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.mbh.moviebrowser.domain.model.MovieDomain
import com.mbh.moviebrowser.store.MovieStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val movieStore: MovieStore,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState = MutableStateFlow(movieStore.searchMoviesById(savedStateHandle["movieId"]?: -1))
    val uiState: StateFlow<MovieDomain> = _uiState

    fun onFavoriteClicked() {
        movieStore.addRemoveMovieToFavorite(uiState.value.id)
        _uiState.update {
            it.copy(
                isFavorite = !uiState.value.isFavorite
            )
        }
    }
}
