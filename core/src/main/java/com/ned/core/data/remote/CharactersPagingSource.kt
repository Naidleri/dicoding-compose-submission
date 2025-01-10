package com.ned.core.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ned.core.data.remote.network.ApiService
import com.ned.core.domain.model.Character

class CharactersPagingSource(private val apiService: ApiService) : PagingSource<Int, Character>() {
    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        return try {
            val position = params.key ?: 1
            val response = apiService.getCharacters(page = position, size = params.loadSize)

            val domainCharacter = response.data.map { dataItem ->
                Character(
                    id = dataItem.id,
                    name = dataItem.name,
                    image = dataItem.imageUrl,
                    films = dataItem.films,
                    tvShows = dataItem.tvShows,
                    shortFilms = dataItem.shortFilms,
                    videoGames = dataItem.videoGames,
                    createdAt = dataItem.createdAt,
                    updatedAt = dataItem.updatedAt,
                    url = dataItem.url,
                    sourceUrl = dataItem.sourceUrl,
                    parkAttractions = dataItem.parkAttractions,
                    allies = dataItem.allies,
                    enemies = dataItem.enemies,
                    __v = dataItem.v,
                    favorite = false
                )
            }

            val nextPage = response.info.nextPage?.let { url ->
                url.substringAfter("page=").substringBefore("&").toIntOrNull()
            }
            val prevPage = response.info.previousPage?.let { url ->
                url.substringAfter("page=").substringBefore("&").toIntOrNull()
            }

            LoadResult.Page(
                data = domainCharacter,
                prevKey = prevPage,
                nextKey = nextPage
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }
}