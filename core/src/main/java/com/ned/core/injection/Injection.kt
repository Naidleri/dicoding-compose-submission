package com.ned.core.injection

import android.content.Context
import com.ned.core.data.local.room.CharactersDatabase
import com.ned.core.data.remote.CharactersRepository
import com.ned.core.data.remote.network.ApiConfig
import com.ned.core.domain.repository.ICharacterRepository
import com.ned.core.domain.usecase.CharacterInteractor
import com.ned.core.domain.usecase.CharacterUseCase

object Injection {
    fun CharacterInjectionRepository (context: Context) : ICharacterRepository {
        val apiService = ApiConfig.getApiService()
        val database = CharactersDatabase.getInstance(context)
        val dao = database.charactersDao()
        return CharactersRepository.getInstance(apiService,dao)
    }

    fun provideCharacterUseCase(context: Context): CharacterUseCase {
        val apiService = ApiConfig.getApiService()
        val database = CharactersDatabase.getInstance(context)
        val dao = database.charactersDao()
        val repository = CharactersRepository.getInstance(apiService, dao)

        return CharacterInteractor(repository)
    }
}