package com.mbh.moviebrowser.data.repository

import android.util.Log
import com.mbh.moviebrowser.data.mapper.MovieDtoToMovieMapper
import com.mbh.moviebrowser.data.network.data_source.MovieBrowserApiSource
import com.mbh.moviebrowser.domain.model.MovieDomain
import com.mbh.moviebrowser.domain.repository.MovieBrowserRepository
import java.lang.Exception
import javax.inject.Inject

class MovieBrowserRepositoryImpl @Inject constructor(
    private val apiSource: MovieBrowserApiSource
) : MovieBrowserRepository {

    override suspend fun getTrendingMovies(): List<MovieDomain> = try {
        val responseMovie = apiSource.getTrendingMovies()
        val responseGenre = apiSource.getGenre()

        if (responseMovie.isSuccessful && responseGenre.isSuccessful) {
            MovieDtoToMovieMapper().map(responseMovie.body()!!, responseGenre.body()!!)
        } else {
            emptyList()
        }
    } catch (e: Exception) {
        Log.e("Repository exception: ", e.message.toString())
        emptyList()
    }
}