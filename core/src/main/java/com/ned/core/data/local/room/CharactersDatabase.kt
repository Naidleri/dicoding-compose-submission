package com.ned.core.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ned.core.data.local.entity.CharactersEntity
import com.ned.core.utils.Converters

@Database(entities = [CharactersEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class CharactersDatabase: RoomDatabase() {
    abstract fun charactersDao(): CharactersDao
}