package com.mbh.moviebrowser.presentation.movieDetails

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.mbh.moviebrowser.domain.model.MovieDomain
import com.mbh.moviebrowser.presentation.components.ProgressIndicator

@Composable
fun MovieDetailsScreen(
    viewModel: MovieDetailsViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    MovieDetailsScreenUI(
        uiState,
        viewModel::onFavoriteClicked,
    )
}

@Composable
fun MovieDetailsScreenUI(
    movie: MovieDomain?,
    onFavoriteClicked: () -> Unit,
) {
    if (movie == null) {
        ProgressIndicator()
        return
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AsyncImage(
            model = movie.coverUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
        )
        Spacer(modifier = Modifier.height(24.dp))
        val image = if (movie.isFavorite) {
            painterResource(id = android.R.drawable.btn_star_big_on)
        } else {
            painterResource(id = android.R.drawable.btn_star_big_off)
        }
        Image(
            painter = image,
            contentDescription = null,
            modifier = Modifier.clickable(onClick = onFavoriteClicked),
        )
        Text(
            text = movie.title,
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary,
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = movie.overview ?: "",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.primary,
        )
    }
}

@Composable
@Preview(
    name = "phone",
    device = "spec:shape=Normal,width=360,height=640,unit=dp,dpi=480",
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
fun MovieDetailsScreenUIPreview() {
    MovieDetailsScreenUI(
        movie = MovieDomain(
            id = 123L,
            title = "Example Movie",
            genres = "Action, Adventure, Sci-Fi",
            overview = "This is an overview of the example movie. It's full of action, adventure and sci-fi elements.",
            coverUrl = "https://image.tmdb.org/t/p/w300/qW4crfED8mpNDadSmMdi7ZDzhXF.jpg",
            rating = 4.5f,
            isFavorite = false,
        ),
        onFavoriteClicked = {},
    )
}
