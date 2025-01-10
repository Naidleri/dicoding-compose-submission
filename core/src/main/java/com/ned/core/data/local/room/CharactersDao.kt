package com.ned.core.data.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ned.core.data.local.entity.CharactersEntity

@Dao
interface CharactersDao {
    @Query("SELECT * FROM characters WHERE favorite = 1 ORDER BY name ASC")
    suspend fun getAllCharacters():List<CharactersEntity>

    @Query("SELECT * FROM characters WHERE id = :id AND favorite = 1")
    suspend fun getCharacterById(id: Int): CharactersEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(characters: List<CharactersEntity>)

    @Query("DELETE FROM characters WHERE id = :id")
    suspend fun deleteCharacter(id: Int)
}