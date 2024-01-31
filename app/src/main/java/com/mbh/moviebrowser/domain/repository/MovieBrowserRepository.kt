package com.mbh.moviebrowser.domain.repository

import com.mbh.moviebrowser.domain.model.MovieDomain

interface MovieBrowserRepository {

    suspend fun getTrendingMovies(): List<MovieDomain>
}