package com.ned.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ned.core.domain.usecase.CharacterUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import com.ned.core.domain.model.Character
import com.ned.ui.common.UiState

class FavoriteViewModel(
    private val characterUseCase: CharacterUseCase
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<Character>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<Character>>> get() = _uiState

    fun getFavoriteCharacters() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                characterUseCase.getFavoriteCharacters()
                    .catch { e ->
                        _uiState.value = UiState.Error(e.message ?: "An unexpected error occurred")
                    }
                    .collect { characters ->
                        _uiState.value = UiState.Success(characters)
                    }
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message ?: "An unexpected error occurred")
            }
        }
    }
}