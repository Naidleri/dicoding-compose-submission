package com.ned.disneycharacter.injection

import android.content.Context
import com.ned.disneycharacter.data.local.room.CharactersDatabase
import com.ned.disneycharacter.data.remote.CharactersRepository
import com.ned.disneycharacter.data.remote.network.ApiConfig

object Injection {
    fun CharacterInjectionRepository (context: Context) : CharactersRepository {
        val apiService = ApiConfig.getApiService()
        val database = CharactersDatabase.getInstance(context)
        val dao = database.charactersDao()
        return CharactersRepository.getInstance(apiService,dao)
    }
}