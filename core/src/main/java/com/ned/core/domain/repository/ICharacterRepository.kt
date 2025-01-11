package com.ned.core.domain.repository

import androidx.paging.PagingData
import com.ned.core.domain.model.Character
import kotlinx.coroutines.flow.Flow

interface ICharacterRepository {
    fun getCharacters(): Flow<PagingData<Character>>
    fun getCharacterById(id: Int): Flow<Character>
    fun searchCharacterByName(name: String): Flow<List<Character>>
    suspend fun insertFavoriteCharacters(characters: Character)
    fun getFavoriteCharacters(): Flow<List<Character>>
    suspend fun isFavoriteCharacter(id: Int): Boolean
    suspend fun deleteFavoriteCharacter(id: Int)
}