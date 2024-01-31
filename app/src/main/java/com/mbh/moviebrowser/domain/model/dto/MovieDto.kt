package com.mbh.moviebrowser.domain.model.dto

data class MovieDto(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)