package com.ned.disneycharacter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ned.disneycharacter.data.remote.CharactersRepository
import com.ned.disneycharacter.ui.presentation.detailchar.DetailCharViewModel
import com.ned.disneycharacter.ui.presentation.home.HomeViewModel

class ViewModelFactory (private val repository: CharactersRepository) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(repository) as T
        }
        if (modelClass.isAssignableFrom(DetailCharViewModel::class.java)) {
            return DetailCharViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
