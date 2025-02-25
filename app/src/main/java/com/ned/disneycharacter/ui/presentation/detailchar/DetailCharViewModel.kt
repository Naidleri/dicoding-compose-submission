package com.ned.disneycharacter.ui.presentation.detailchar

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ned.core.domain.model.Character
import com.ned.core.domain.usecase.CharacterUseCase
import com.ned.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class DetailCharViewModel (
    private val characterUseCase: CharacterUseCase
) : ViewModel() {
    private val _uiState : MutableStateFlow<UiState<Character>> = MutableStateFlow(UiState.Loading)
    val uiState : StateFlow<UiState<Character>> get() = _uiState

    private val _isFavorite = MutableStateFlow(false)
    val isFavorite: StateFlow<Boolean> get() = _isFavorite


    fun getCharacterById(id: Int) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                val dataItem = characterUseCase.getCharacterById(id).first()
                _uiState.value = UiState.Success(dataItem)

                val isFav = characterUseCase.isFavoriteCharacter(id)

                _isFavorite.value = isFav

            } catch (e: Exception) {
                Log.e("DetailCharViewModel", "Error fetching character", e)
                _uiState.value = UiState.Error(e.message ?: "An unexpected error occurred")
            }
        }
    }



    fun saveCharacterToFavorites(character: Character) {
        viewModelScope.launch {
            characterUseCase.insertFavoriteCharacters(character)
            _isFavorite.value = true
        }
    }

    fun deleteCharacterFromFavorites(id: Int) {
        viewModelScope.launch {
            characterUseCase.deleteFavoriteCharacter(id)
            _isFavorite.value = false
        }
    }
}