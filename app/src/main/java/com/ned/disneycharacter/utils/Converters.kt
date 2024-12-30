package com.ned.disneycharacter.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    @TypeConverter
    fun fromString(value: String?): List<String>? {
        return value?.let {
            Gson().fromJson(it, object : TypeToken<List<String>>() {}.type)
        }
    }

    @TypeConverter
    fun fromList(list: List<String>?): String? {
        return list?.let { Gson().toJson(it) }
    }

    @TypeConverter
    fun fromAnyList(value: String?): List<Any>? {
        return value?.let {
            Gson().fromJson(it, object : TypeToken<List<Any>>() {}.type)
        }
    }

    @TypeConverter
    fun toAnyList(list: List<Any>?): String? {
        return list?.let { Gson().toJson(it) }
    }
}