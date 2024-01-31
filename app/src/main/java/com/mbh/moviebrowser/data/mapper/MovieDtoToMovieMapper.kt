package com.mbh.moviebrowser.data.mapper

import com.mbh.moviebrowser.domain.model.MovieDomain
import com.mbh.moviebrowser.domain.model.dto.GenreDto
import com.mbh.moviebrowser.domain.model.dto.MovieDto

class MovieDtoToMovieMapper {

    fun map(movieDto: MovieDto, genres: GenreDto): List<MovieDomain> = movieDto.results.map {
        MovieDomain(
            id = it.id.toLong(),
            title = it.title,
            genres = matchGenres(it.genre_ids, genres),
            overview = it.overview,
            coverUrl = "https://image.tmdb.org/t/p/w500" + it.poster_path,
            rating = it.vote_average.toFloat(),
            isFavorite = false
        )
    }


    private fun matchGenres(genreIds: List<Int>, genreDto: GenreDto): String {
        val genres = StringBuilder()
        genreIds.forEach { id ->
            genreDto.genres.forEach { dto ->
                if (dto.id == id) {
                    genres.append(dto.name + " ")
                }
            }
        }
        return genres.toString()
    }
}