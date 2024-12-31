package com.ned.disneycharacter.ui.presentation.detailchar

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ned.disneycharacter.data.local.entity.CharactersEntity
import com.ned.disneycharacter.data.remote.CharactersRepository
import com.ned.disneycharacter.data.remote.response.DataItem
import com.ned.disneycharacter.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailCharViewModel (
    private val repository: CharactersRepository
) : ViewModel() {
    private val _uiState : MutableStateFlow<UiState<DataItem>> = MutableStateFlow(UiState.Loading)
    val uiState : StateFlow<UiState<DataItem>> get() = _uiState

    private val _isFavorite = MutableStateFlow(false)
    val isFavorite: StateFlow<Boolean> get() = _isFavorite


    fun getCharacterById(id: Int) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                Log.d("DetailCharViewModel", "Fetching character with id: $id")
                val dataItem = repository.getCharacterById(id)
                Log.d("DetailCharViewModel", "Received data: $dataItem")
                _uiState.value = UiState.Success(dataItem)

                // Check favorite status after loading character
                val isFav = repository.isFavoriteCharacter(id)
                Log.d("DetailCharViewModel", "Is Favorite: $isFav")
                _isFavorite.value = isFav

            } catch (e: Exception) {
                Log.e("DetailCharViewModel", "Error fetching character", e)
                _uiState.value = UiState.Error(e.message ?: "An unexpected error occurred")
            }
        }
    }

    suspend fun getFavoriteCharacters(): List<CharactersEntity> {
        return repository.getFavoriteCharacters()
    }

    fun saveCharacterToFavorites(character: DataItem) {
        viewModelScope.launch {
            repository.insertFavoriteCharacters(character)
            _isFavorite.value = true
        }
    }

    fun deleteCharacterFromFavorites(id: Int) {
        viewModelScope.launch {
            repository.deleteFavoriteCharacter(id)
            _isFavorite.value = false
        }
    }

    private fun checkFavoriteStatus(id: Int) {
        viewModelScope.launch {
            _isFavorite.value = repository.isFavoriteCharacter(id)
        }
    }

    suspend fun isFavoriteCharacter(id: Int) : Boolean {
        return repository.isFavoriteCharacter(id)
    }


}