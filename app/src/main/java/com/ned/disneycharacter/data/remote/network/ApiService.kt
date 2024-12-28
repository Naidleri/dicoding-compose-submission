package com.ned.disneycharacter.data.remote.network

import com.ned.disneycharacter.data.remote.response.CharacterResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("character")
    suspend fun getCharacters(
        @Query("page") page: Int,
        @Query("pageSize") size: Int
    ): CharacterResponse
}