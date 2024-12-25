package com.ned.disneycharacter.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ned.disneycharacter.data.remote.network.ApiService
import com.ned.disneycharacter.data.remote.response.DataItem

class CharactersPagingSource(private val apiService: ApiService) : PagingSource<Int, DataItem>() {
    override fun getRefreshKey(state: PagingState<Int, DataItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DataItem> {
        val position = params.key ?: 1
        return try {
            val response = apiService.getCharacters(page = position,size = params.loadSize)
            val characters = response.data

            LoadResult.Page(
                data = characters,
                prevKey = if (position == 1) null else position - 1,
                nextKey = if (characters.isEmpty()) null else position + 1
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

}