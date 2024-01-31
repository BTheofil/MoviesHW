package com.mbh.moviebrowser.store

import com.mbh.moviebrowser.domain.model.MovieDomain
import com.mbh.moviebrowser.domain.repository.MovieBrowserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieStore @Inject constructor(
    private val repository: MovieBrowserRepository
){
    val moviesWatcher: MutableStateFlow<List<MovieDomain>> = MutableStateFlow(emptyList())

    init {
        CoroutineScope(Dispatchers.IO).launch {
            val movies = repository.getTrendingMovies()
            moviesWatcher.value = movies
        }
    }

    fun searchMoviesById(id: Long): MovieDomain = moviesWatcher.value.first { movie -> movie.id == id }

    fun addRemoveMovieToFavorite(id: Long) {
        moviesWatcher.value = moviesWatcher.value.map {
            if (it.id == id) {
                it.copy(isFavorite = !it.isFavorite)
            } else {
                it
            }
        }
    }
}
