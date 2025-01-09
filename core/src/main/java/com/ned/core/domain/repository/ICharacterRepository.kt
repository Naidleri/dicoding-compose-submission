package com.ned.core.domain.repository

import androidx.paging.PagingData
import com.ned.core.domain.model.Character
import kotlinx.coroutines.flow.Flow

interface ICharacterRepository {
    fun getCharacters(): Flow<PagingData<Character>>
    suspend fun getCharacterById(id: Int): Character
    fun searchCharacterByName(name: String): Flow<List<Character>>
    suspend fun insertFavoriteCharacters(characters: Character)
    suspend fun getFavoriteCharacters(): List<Character>
    suspend fun isFavoriteCharacter(id: Int): Boolean
    suspend fun deleteFavoriteCharacter(id: Int)
}