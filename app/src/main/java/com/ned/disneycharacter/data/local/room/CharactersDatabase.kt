package com.ned.disneycharacter.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ned.disneycharacter.data.local.entity.CharactersEntity
import com.ned.disneycharacter.utils.Converters

@Database(entities = [CharactersEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class CharactersDatabase: RoomDatabase() {
    abstract fun charactersDao(): CharactersDao

    companion object{
        @Volatile
        private var instance: CharactersDatabase? = null
        fun getInstance(context: Context): CharactersDatabase =
            instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    CharactersDatabase::class.java,
                    "characters.db"
                ).build()
            }
    }
}