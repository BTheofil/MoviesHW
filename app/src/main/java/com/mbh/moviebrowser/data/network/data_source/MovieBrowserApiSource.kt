package com.mbh.moviebrowser.data.network.data_source

import com.mbh.moviebrowser.BuildConfig
import com.mbh.moviebrowser.domain.model.dto.GenreDto
import com.mbh.moviebrowser.domain.model.dto.MovieDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieBrowserApiSource {

    @GET("trending/movie/day")
    suspend fun getTrendingMovies(
        @Query("api_key") apiKey : String = BuildConfig.API_KEY,
    ): Response<MovieDto>

    @GET("genre/movie/list")
    suspend fun getGenre(
        @Query("api_key") apiKey : String = BuildConfig.API_KEY,
    ): Response<GenreDto>
}