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
        return try {
            val position = params.key ?: 1
            val response = apiService.getCharacters(page = position, size = params.loadSize)

            val nextPage = response.info.nextPage?.let { url ->
                url.substringAfter("page=").substringBefore("&").toIntOrNull()
            }
            val prevPage = response.info.previousPage?.let { url ->
                url.substringAfter("page=").substringBefore("&").toIntOrNull()
            }

            LoadResult.Page(
                data = response.data,
                prevKey = prevPage,
                nextKey = nextPage
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

}