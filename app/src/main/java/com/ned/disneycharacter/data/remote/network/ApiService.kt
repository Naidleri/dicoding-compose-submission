package com.ned.disneycharacter.data.remote.network

import com.ned.disneycharacter.data.remote.response.CharacterDetailResponse
import com.ned.disneycharacter.data.remote.response.CharacterResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("character")
    suspend fun getCharacters(
        @Query("page") page: Int,
        @Query("pageSize") size: Int
    ): CharacterResponse

    @GET("character/{id}")
    suspend fun getCharacterById(
        @Path("id") id: Int
    ): CharacterDetailResponse

    @GET("character")
    suspend fun searchCharacterByName(
        @Query("name") name: String
    ): CharacterResponse
}