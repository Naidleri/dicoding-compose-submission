package com.ned.disneycharacter.data.remote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ned.disneycharacter.data.remote.network.ApiService
import com.ned.disneycharacter.data.remote.response.DataItem
import kotlinx.coroutines.flow.Flow

class CharactersRepository (private val apiService: ApiService) {

    fun getCharacters() : Flow<PagingData<DataItem>> {
       return Pager(
           config = PagingConfig(
               pageSize = 20,
               enablePlaceholders = false
           ),
           pagingSourceFactory = { CharactersPagingSource(apiService) }
       ).flow
    }

    companion object {
        @Volatile
        private var instance: CharactersRepository? = null

        fun getInstance(apiService: ApiService): CharactersRepository =
            instance ?: synchronized(this) {
                instance ?: CharactersRepository(apiService).also { instance = it }
            }.also { instance = it }
    }
}