package com.ned.core.data.remote

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ned.core.data.local.room.CharactersDao
import com.ned.core.data.remote.network.ApiService
import com.ned.core.domain.model.Character
import com.ned.core.domain.repository.ICharacterRepository
import com.ned.core.utils.CharacterMapper.toDomain
import com.ned.core.utils.CharacterMapper.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class CharactersRepository (
    private val apiService: ApiService,
    private val charDao : CharactersDao
): ICharacterRepository  {

    override fun getCharacters() : Flow<PagingData<Character>> {
       return Pager(
           config = PagingConfig(
               pageSize = 20,
               enablePlaceholders = false
           ),
           pagingSourceFactory = { CharactersPagingSource(apiService) }
       ).flow
    }

    override suspend fun getCharacterById(id: Int) : Character {
        val response = apiService.getCharacterById(id)
        Log.d("CharactersRepository", "Response: ${response.data}")
        return response.data.toDomain()
    }

    override fun searchCharacterByName(name: String) : Flow<List<Character>> = flow {
        val response = apiService.searchCharacterByName(name)
        emit(response.data.map { it.toDomain() })
    }.catch { e ->
        Log.e("CharactersRepository", "Error searching character: ${e.message}")
        throw e
    }


    override suspend fun insertFavoriteCharacters(characters:Character) {
        charDao.insertAll(listOf(characters.toEntity()))
    }

    override suspend fun getFavoriteCharacters(): List<Character> {
        return charDao.getAllCharacters().map { it.toDomain() }
    }

    override suspend fun isFavoriteCharacter(id: Int): Boolean {
        val character = charDao.getCharacterById(id)
        return character != null
    }


    override suspend fun deleteFavoriteCharacter(id: Int) {
        try {
            charDao.deleteCharacter(id)
        } catch (e: Exception) {
            Log.e("CharactersRepository", "Error deleting character", e)
            throw e
        }
    }

    companion object {
        @Volatile
        private var instance: CharactersRepository? = null

        fun getInstance(apiService:ApiService, charDao: CharactersDao): CharactersRepository =
            instance ?: synchronized(this) {
                instance ?: CharactersRepository(apiService, charDao).also { instance = it }
            }.also { instance = it }
    }
}