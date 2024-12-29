package com.ned.disneycharacter.ui.presentation.detailchar

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

    fun getCharacterById(id: Int) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = try {
                Log.d("DetailCharViewModel", "Fetching character with id: $id")
                val dataItem = repository.getCharacterById(id)
                Log.d("DetailCharViewModel", "Received data: $dataItem")
                UiState.Success(dataItem)
            } catch (e: Exception) {
                Log.e("DetailCharViewModel", "Error fetching character", e)
                UiState.Error(e.message ?: "An unexpected error occurred")
            }
        }
    }
}