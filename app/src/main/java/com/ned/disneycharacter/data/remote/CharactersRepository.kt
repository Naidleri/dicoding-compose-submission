package com.ned.disneycharacter.data.remote

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ned.disneycharacter.data.local.entity.CharactersEntity
import com.ned.disneycharacter.data.local.room.CharactersDao
import com.ned.disneycharacter.data.remote.network.ApiService
import com.ned.disneycharacter.data.remote.response.DataItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class CharactersRepository (
    private val apiService: ApiService,
    private val charDao : CharactersDao
    ) {

    fun getCharacters() : Flow<PagingData<DataItem>> {
       return Pager(
           config = PagingConfig(
               pageSize = 20,
               enablePlaceholders = false
           ),
           pagingSourceFactory = { CharactersPagingSource(apiService) }
       ).flow
    }

    suspend fun getCharacterById(id: Int) : DataItem {
        val response = apiService.getCharacterById(id)
        Log.d("CharactersRepository", "Response: ${response.data}")
        return response.data
    }

    fun searchCharacterByName(name: String) : Flow<List<DataItem>> = flow {
        val response = apiService.searchCharacterByName(name)
        emit(response.data)
    }.catch { e ->
        Log.e("CharactersRepository", "Error searching character: ${e.message}")
        throw e
    }
    suspend fun insertFavoriteCharacters(characters:DataItem) {
        val characterEntity = CharactersEntity(
            id = characters.id,
            name = characters.name,
            image = characters.imageUrl,
            films = characters.films,
            tvShows = characters.tvShows,
            shortFilms = characters.shortFilms,
            videoGames = characters.videoGames,
            createdAt = characters.createdAt,
            updatedAt = characters.updatedAt,
            url = characters.url,
            sourceUrl = characters.sourceUrl,
            parkAttraction = characters.parkAttractions,
            allies = characters.allies,
            enemies = characters.enemies,
            v = characters.v,
            favorite = true
        )
        charDao.insertAll(listOf(characterEntity))
    }

    suspend fun getFavoriteCharacters() : List<CharactersEntity> {
        return charDao.getAllCharacters()
    }

    suspend fun isFavoriteCharacter(id: Int): Boolean {
        val character = charDao.getCharacterById(id)
        return character != null
    }


    suspend fun deleteFavoriteCharacter(id: Int) {
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

        fun getInstance(apiService: ApiService,charDao: CharactersDao): CharactersRepository =
            instance ?: synchronized(this) {
                instance ?: CharactersRepository(apiService, charDao).also { instance = it }
            }.also { instance = it }
    }
}