package com.ned.disneycharacter.ui.presentation.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.ned.core.domain.model.Character
import com.ned.core.domain.usecase.CharacterUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val characterUseCase: CharacterUseCase) : ViewModel() {
    private val _query = MutableStateFlow("")
    val query: StateFlow<String> = _query

    private val _searchResults = MutableStateFlow<List<Character>>(emptyList())
    val searchResults: StateFlow<List<Character>> = _searchResults

    val characters: Flow<PagingData<Character>> = characterUseCase
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
                characterUseCase.searchCharacterByName(newQuery)
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