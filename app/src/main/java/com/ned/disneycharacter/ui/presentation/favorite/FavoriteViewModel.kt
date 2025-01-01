package com.ned.disneycharacter.ui.presentation.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ned.disneycharacter.data.local.entity.CharactersEntity
import com.ned.disneycharacter.data.remote.CharactersRepository
import com.ned.disneycharacter.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FavoriteViewModel (
   private val repository: CharactersRepository
): ViewModel() {
    private val _uiState : MutableStateFlow<UiState<List<CharactersEntity>>> = MutableStateFlow(UiState.Loading)
    val uiState : StateFlow<UiState<List<CharactersEntity>>> get() = _uiState

    fun getFavoriteCharacters() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                val data = repository.getFavoriteCharacters()
                _uiState.value = UiState.Success(data)
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message ?: "An unexpected error occurred")
            }
        }
    }
}