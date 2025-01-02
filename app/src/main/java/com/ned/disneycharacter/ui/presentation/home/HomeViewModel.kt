package com.ned.disneycharacter.ui.presentation.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.ned.disneycharacter.data.remote.CharactersRepository
import com.ned.disneycharacter.data.remote.response.DataItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: CharactersRepository) : ViewModel() {
    private val _query = MutableStateFlow("")
    val query: StateFlow<String> = _query

    private val _searchResults = MutableStateFlow<List<DataItem>>(emptyList())
    val searchResults: StateFlow<List<DataItem>> = _searchResults

    val characters: Flow<PagingData<DataItem>> = repository
        .getCharacters()
        .cachedIn(viewModelScope)

    fun updateQuery(newQuery: String) {
        _query.value = newQuery
        if (newQuery.isBlank()) {
            _searchResults.value = emptyList()
            return
        }

        viewModelScope.launch {
            try {
                repository.searchCharacterByName(newQuery)
                    .collect { character ->
                        _searchResults.value = character
                    }
            } catch (e: Exception) {
                Log.e("HomeViewModel", "Error during search: ${e.message}")
                _searchResults.value = emptyList()
            }
        }
    }
}