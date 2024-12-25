package com.ned.disneycharacter.injection

import com.ned.disneycharacter.data.remote.CharactersRepository
import com.ned.disneycharacter.data.remote.network.ApiConfig

object Injection {
    fun CharacterInjectionRepository () : CharactersRepository {
        val apiService = ApiConfig.getApiService()
        return CharactersRepository.getInstance(apiService)
    }
}