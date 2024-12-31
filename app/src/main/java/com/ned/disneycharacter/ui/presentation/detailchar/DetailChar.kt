package com.ned.disneycharacter.ui.presentation.detailchar

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.compose.AppTheme
import com.ned.disneycharacter.R
import com.ned.disneycharacter.ViewModelFactory
import com.ned.disneycharacter.injection.Injection
import com.ned.disneycharacter.ui.common.UiState
import com.ned.disneycharacter.ui.component.CharacterSection

@Composable
fun DetailChar(
    charId: Int,
    viewModel: DetailCharViewModel = viewModel(
        factory = ViewModelFactory(
            Injection.CharacterInjectionRepository(context = LocalContext.current)
        )
    ),
   navigateBack: () -> Unit
) {
    Log.d("DetailChar", "Received charId: $charId")
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getCharacterById(charId)
            }
            is UiState.Success -> {
                DetailCharContent(
                    image = uiState.data.imageUrl ?: "",
                    name = uiState.data.name,
                    films = uiState.data.films,
                    tvShows = uiState.data.tvShows,
                    shortFilms = uiState.data.shortFilms,
                    videoGames = uiState.data.videoGames,
                    onBackClick = navigateBack
                )
            }
            is UiState.Error -> {
                Log.e("DetailChar", "Error loading character data")
            }
        }
    }
}

@Composable
fun DetailCharContent(
    image: String,
    name: String,
    films: List<String>?,
    tvShows: List<String>?,
    shortFilms: List<String>?,
    videoGames: List<String>?,
    onBackClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        Box {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(image)
                    .crossfade(true)
                    .placeholder(R.drawable.placeholder_image)
                    .error(R.drawable.placeholder_image)
                    .build(),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1.78f),
                contentScale = ContentScale.FillWidth
            )
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = stringResource(R.string.back),
                modifier = Modifier
                    .padding(start = 16.dp, top = 16.dp)
                    .clickable {
                        onBackClick()
                    }
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = name,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .padding(16.dp)
        )
        CharacterSection(
            title = "Films",
            items = films,
            emptyMessage = "Tidak ada info film dari karakter ini"
        )
        CharacterSection(
            title = "TV Shows",
            items = tvShows,
            emptyMessage = "Tidak ada info tv show dari karakter ini"
        )
        CharacterSection(
            title = "Short Films",
            items = shortFilms,
            emptyMessage = "Tidak ada info short film dari karakter ini"
        )
        CharacterSection(
            title = "Video Games",
            items = videoGames,
            emptyMessage = "Tidak ada info video game dari karakter ini"
        )
    }
}

@Preview (
    device = "id:pixel_6",
    showSystemUi = true,
    showBackground = true)
@Composable
private fun DetailContentPrev() {
    AppTheme {
        DetailCharContent(
            image = "https://upload.wikimedia.org/wikipedia/en/d/d4/Mickey_Mouse.png",
            name = "Mickey Mouse",
            films = listOf("Fantasia", "Fantasia 2000"),
            tvShows = listOf("The Mickey Mouse Club", "Mickey Mouse Works"),
            shortFilms = listOf("Steamboat Willie", "Plane Crazy"),
            videoGames = listOf("Kingdom Hearts", "Disney Infinity"),
        )
    }
}