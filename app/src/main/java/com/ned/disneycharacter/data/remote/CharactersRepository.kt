package com.ned.disneycharacter.data.remote

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.ned.disneycharacter.data.remote.network.ApiService
import com.ned.disneycharacter.data.remote.response.DataItem

class CharactersRepository (private val apiService: ApiService) {

    suspend fun getCharacters(page: Int) : LiveData<PagingData<DataItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = page,
                prefetchDistance = 1,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                CharactersPagingSource(apiService)
            }
        ).liveData
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