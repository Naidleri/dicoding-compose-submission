package com.ned.favorite

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.ned.core.domain.model.Character
import com.ned.disneycharacter.ui.component.CharacterItem
import com.ned.ui.common.UiState
import org.koin.androidx.compose.koinViewModel

@Composable
fun FavoriteScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    viewModel: FavoriteViewModel = koinViewModel(),
    navigateToDetail: (Int) -> Unit
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getFavoriteCharacters()
            }
            is UiState.Success -> {
                if (uiState.data.isEmpty()) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Text(text = "Kamu belum menambahkan karakter ke favorite")
                    }
                }
                FavoriteContent(
                    character = uiState.data,
                    modifier = modifier,
                    navigateToDetail = navigateToDetail
                )
            }
            is UiState.Error -> {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(text = "Error fetch data")
                }
            }
        }
    }
}

@Composable
fun FavoriteContent(
    character: List<Character>,
    modifier: Modifier = Modifier,
    navigateToDetail: (Int) -> Unit
) {
   LazyVerticalGrid(
       columns = GridCells.Adaptive(160.dp),
       contentPadding = PaddingValues(16.dp),
       horizontalArrangement = Arrangement.spacedBy(16.dp),
       verticalArrangement = Arrangement.spacedBy(16.dp),
       modifier = modifier.fillMaxSize()
   ) {
        items(character) { item ->
            CharacterItem(
               image = item.image,
                name = item.name,
                isLoading = false,
                modifier = Modifier.clickable {
                    navigateToDetail(item.id)
                }
            )
        }
   }
}