package com.mbh.moviebrowser.presentation.movieList.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mbh.moviebrowser.domain.model.MovieDomain
import com.mbh.moviebrowser.presentation.movieList.MovieListScreen

const val MOVIE_LIST_ROUTE = "list"

fun NavGraphBuilder.movieListNavigation(onDetailsClicked: (MovieDomain) -> Unit) {
    composable(route = MOVIE_LIST_ROUTE) {
        MovieListScreen(onDetailsClicked = onDetailsClicked)
    }
}