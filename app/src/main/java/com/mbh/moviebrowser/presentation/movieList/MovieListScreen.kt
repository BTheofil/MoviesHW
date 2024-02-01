package com.mbh.moviebrowser.presentation.movieList

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.mbh.moviebrowser.domain.model.MovieDomain
import com.mbh.moviebrowser.presentation.components.ProgressIndicator
import com.mbh.moviebrowser.presentation.movieList.components.MovieListItem

@Composable
fun MovieListScreen(
    viewModel: MovieListViewModel = hiltViewModel(),
    onDetailsClicked: (MovieDomain) -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    MovieListScreenUI(uiState.movies) {
        onDetailsClicked(it)
    }
}

@Composable
fun MovieListScreenUI(
    movies: List<MovieDomain>,
    onDetailsClicked: (MovieDomain) -> Unit
) {
    if (movies.isEmpty()) {
        ProgressIndicator()
        return
    }

    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        items(movies) { item ->
            MovieListItem(
                movie = item,
                onDetailsClicked,
            )
        }
    }
}

@Composable
@Preview(
    name = "phone",
    device = "spec:shape=Normal,width=360,height=640,unit=dp,dpi=480",
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
fun MovieListScreenUIPreview() {
    MovieListScreenUI(
        listOf(
            MovieDomain(
                id = 455476,
                title = "Knights of the Zodiac",
                genres = "Action, Sci-fi",
                overview = "When a headstrong street orphan, Seiya, in search of his abducted sister unwittingly taps into hidden powers, he discovers he might be the only person alive who can protect a reincarnated goddess, sent to watch over humanity. Can he let his past go and embrace his destiny to become a Knight of the Zodiac?",
                coverUrl = "https://image.tmdb.org/t/p/w500/qW4crfED8mpNDadSmMdi7ZDzhXF.jpg",
                rating = 6.5f,
                isFavorite = true,
            ),
            MovieDomain(
                id = 385687,
                title = "Fast X",
                genres = "Action",
                overview = "Over many missions and against impossible odds, Dom Toretto and his family have outsmarted, out-nerved and outdriven every foe in their path. Now, they confront the most lethal opponent they've ever faced: A terrifying threat emerging from the shadows of the past who's fueled by blood revenge, and who is determined to shatter this family and destroy everything—and everyone—that Dom loves, forever.",
                coverUrl = "https://image.tmdb.org/t/p/w500/fiVW06jE7z9YnO4trhaMEdclSiC.jpg",
                rating = 7.4f,
                isFavorite = false,
            ),
        ),
        onDetailsClicked = {},
    )
}
