package com.ned.disneycharacter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ned.disneycharacter.data.remote.CharactersRepository

/*
class ViewModelFactory (private val repository: CharactersRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CharacterViewModel::class.java)) {
            return CharacterViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}*/
