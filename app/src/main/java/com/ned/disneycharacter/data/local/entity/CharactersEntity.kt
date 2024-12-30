package com.ned.disneycharacter.data.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
@Entity(tableName = "characters")
data class CharactersEntity (
    @field:ColumnInfo(name = "id")
    @PrimaryKey
    val id: Int,

    @field:ColumnInfo(name = "name")
    val name: String,

    @field:ColumnInfo(name = "image")
    val image: String?,

    @field:ColumnInfo(name = "films")
    val films: List<String>?,

    @field:ColumnInfo(name = "tvShows")
    val tvShows: List<String>?,

    @field:ColumnInfo(name = "shortFilms")
    val shortFilms: List<String>?,

    @field:ColumnInfo(name = "videoGames")
    val videoGames: List<String>?,

    @field:ColumnInfo(name = "createdAt")
    val createdAt: String?,

    @field:ColumnInfo(name = "updatedAt")
    val updatedAt: String?,

    @field:ColumnInfo(name = "url")
    val url: String?,

    @field:ColumnInfo(name = "sourceUrl")
    val sourceUrl: String?,

    @field:ColumnInfo(name = "parkAttractions")
    val parkAttraction: List<String>?,

    @field:ColumnInfo(name = "allies")
    val allies: @RawValue List<Any>?,

    @field:ColumnInfo(name = "enemies")
    val enemies: @RawValue List<Any>,

    @field:ColumnInfo(name = "__v")
    val v: Int?,

    @field:ColumnInfo(name = "favorite")
    var favorite: Boolean = false

): Parcelable