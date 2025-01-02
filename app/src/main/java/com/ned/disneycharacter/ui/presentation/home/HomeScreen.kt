package com.ned.disneycharacter.ui.presentation.home

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.ned.disneycharacter.ViewModelFactory
import com.ned.disneycharacter.data.remote.response.DataItem
import com.ned.disneycharacter.injection.Injection
import com.ned.disneycharacter.ui.component.CharacterItem
import com.ned.disneycharacter.ui.component.SearchBar

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory(Injection.CharacterInjectionRepository(context = LocalContext.current))
    ),
    navigateToDetail: (Int) -> Unit
) {
    val characters = viewModel.characters.collectAsLazyPagingItems()
    val query by viewModel.query.collectAsState()
    val searchResults by viewModel.searchResults.collectAsState()

    Column(
        modifier = Modifier
    ) {
        SearchBar(
            query = query,
            onQueryChange = viewModel::updateQuery,
            modifier = Modifier
        )

        when {
            query.isBlank() -> {
                HomeContent(
                    character = characters,
                    modifier = modifier,
                    navigateToDetail = navigateToDetail
                )
            }
            searchResults.isNotEmpty() -> {
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(160.dp),
                    contentPadding = PaddingValues(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = modifier
                ) {
                    items(searchResults.size) { index ->
                        val item = searchResults[index]
                        CharacterItem(
                            image = item.imageUrl,
                            name = item.name,
                            modifier = Modifier.clickable {
                                navigateToDetail(item.id)
                            }
                        )
                    }
                }
            }
            else -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Tidak ada karakter")
                }
            }
        }
    }
}

@Composable
fun HomeContent(
    character: LazyPagingItems<DataItem>,
    modifier: Modifier = Modifier,
    navigateToDetail: (Int) -> Unit
) {
    val loadState = character.loadState

    Log.d("HomeContent", "LoadState refresh: ${loadState.refresh}")
    Log.d("HomeContent", "LoadState append: ${loadState.append}")

    when {
        loadState.refresh is LoadState.Loading -> {
            LoadingScreen()
        }
        loadState.refresh is LoadState.Error -> {
            val error = (loadState.refresh as LoadState.Error).error
            ErrorScreen(message = error.localizedMessage ?: "Unknown error")
        }
        else -> {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(160.dp),
                contentPadding = PaddingValues(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = modifier
            ) {
                items(character.itemCount) { index ->
                    val item = character[index]
                    item?.let {
                        CharacterItem(
                            image = it.imageUrl,
                            name = it.name,
                            modifier = Modifier.clickable {
                                navigateToDetail(it.id)
                            }
                        )
                    }
                }
            }

            if (loadState.append is LoadState.Loading) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            if (loadState.append is LoadState.Error) {
                val error = (loadState.append as LoadState.Error).error
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = error.localizedMessage ?: "Error loading additional data",
                        color = Color.Red
                    )
                }
            }
        }
    }

}

@Composable
fun LoadingScreen() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun ErrorScreen(message: String) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = message,
            color = Color.Red
        )
    }
}
