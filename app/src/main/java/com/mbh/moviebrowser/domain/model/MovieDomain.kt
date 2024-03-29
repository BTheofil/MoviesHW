package com.mbh.moviebrowser.domain.model

data class MovieDomain(
    val id: Long,
    val title: String,
    val genres: String,
    val overview: String?,
    val coverUrl: String?,
    val rating: Float,
    val isFavorite: Boolean,
)
