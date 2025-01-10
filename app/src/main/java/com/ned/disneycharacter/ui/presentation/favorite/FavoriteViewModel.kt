package com.ned.disneycharacter.ui.presentation.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ned.core.domain.model.Character
import com.ned.core.domain.usecase.CharacterUseCase
import com.ned.disneycharacter.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FavoriteViewModel (
   private val characterUseCase: CharacterUseCase
): ViewModel() {
    private val _uiState : MutableStateFlow<UiState<List<Character>>> = MutableStateFlow(UiState.Loading)
    val uiState : StateFlow<UiState<List<Character>>> get() = _uiState

    fun getFavoriteCharacters() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                val data = characterUseCase.getFavoriteCharacters()
                _uiState.value = UiState.Success(data)
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message ?: "An unexpected error occurred")
            }
        }
    }
}