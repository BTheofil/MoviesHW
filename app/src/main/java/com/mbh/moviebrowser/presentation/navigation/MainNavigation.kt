package com.mbh.moviebrowser.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.mbh.moviebrowser.presentation.movieDetails.navigation.movieDetailsNavigation
import com.mbh.moviebrowser.presentation.movieDetails.navigation.navigateToMovieDetails
import com.mbh.moviebrowser.presentation.movieList.navigation.MOVIE_LIST_ROUTE
import com.mbh.moviebrowser.presentation.movieList.navigation.movieListNavigation

@Composable
fun MainNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = MOVIE_LIST_ROUTE) {
        movieListNavigation(
            onDetailsClicked = navController::navigateToMovieDetails
        )

        movieDetailsNavigation()
    }
}