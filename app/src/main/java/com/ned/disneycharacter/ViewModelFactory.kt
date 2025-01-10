package com.ned.disneycharacter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ned.core.domain.usecase.CharacterUseCase
import com.ned.disneycharacter.ui.presentation.detailchar.DetailCharViewModel
import com.ned.disneycharacter.ui.presentation.favorite.FavoriteViewModel
import com.ned.disneycharacter.ui.presentation.home.HomeViewModel

class ViewModelFactory (private val characterUseCase: CharacterUseCase) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(characterUseCase) as T
        }
        if (modelClass.isAssignableFrom(DetailCharViewModel::class.java)) {
            return DetailCharViewModel(characterUseCase) as T
        }
        if (modelClass.isAssignableFrom(FavoriteViewModel::class.java)) {
            return FavoriteViewModel(characterUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
