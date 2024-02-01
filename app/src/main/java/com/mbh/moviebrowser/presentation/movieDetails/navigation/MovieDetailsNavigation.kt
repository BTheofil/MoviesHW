package com.mbh.moviebrowser.presentation.movieDetails.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.mbh.moviebrowser.domain.model.MovieDomain
import com.mbh.moviebrowser.presentation.movieDetails.MovieDetailsScreen

const val MOVIE_DETAILS_ROUTE = "details"
const val MOVIE_DETAILS_ARGUMENT_KEY = "movieId"

fun NavController.navigateToMovieDetails(movieDomain: MovieDomain) {
    this.navigate(MOVIE_DETAILS_ROUTE + "/${movieDomain.id}")
}

fun NavGraphBuilder.movieDetailsNavigation() {
    composable(
        route = "$MOVIE_DETAILS_ROUTE/{$MOVIE_DETAILS_ARGUMENT_KEY}",
        arguments = listOf(navArgument(MOVIE_DETAILS_ARGUMENT_KEY) { type = NavType.LongType })
    ) {
        MovieDetailsScreen()
    }
}