package com.ned.disneycharacter.injection

import com.ned.core.domain.usecase.CharacterInteractor
import com.ned.core.domain.usecase.CharacterUseCase
import com.ned.disneycharacter.ui.presentation.detailchar.DetailCharViewModel
import com.ned.disneycharacter.ui.presentation.favorite.FavoriteViewModel
import com.ned.disneycharacter.ui.presentation.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<CharacterUseCase> { CharacterInteractor(get()) }
}
val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailCharViewModel(get()) }
    viewModel { FavoriteViewModel(get()) }
}