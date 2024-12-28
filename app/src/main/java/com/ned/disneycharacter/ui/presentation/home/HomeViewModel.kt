package com.ned.disneycharacter.ui.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.ned.disneycharacter.data.remote.CharactersRepository
import com.ned.disneycharacter.data.remote.response.DataItem
import kotlinx.coroutines.flow.Flow

class HomeViewModel (private val repository: CharactersRepository) : ViewModel() {
    val characters: Flow<PagingData<DataItem>> = repository
        .getCharacters()
        .cachedIn(viewModelScope)
}