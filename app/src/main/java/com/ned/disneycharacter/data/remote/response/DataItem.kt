package com.ned.disneycharacter.data.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class DataItem(

	@field:SerializedName("parkAttractions")
	val parkAttractions: List<String>?,

	@field:SerializedName("films")
	val films: List<String>?,

	@field:SerializedName("shortFilms")
	val shortFilms: List<String>?,

	@field:SerializedName("tvShows")
	val tvShows: List<String>?,

	@field:SerializedName("enemies")
	val enemies: @RawValue List<Any>,

	@field:SerializedName("url")
	val url: String?,

	@field:SerializedName("videoGames")
	val videoGames: List<String>?,

	@field:SerializedName("sourceUrl")
	val sourceUrl: String?,

	@field:SerializedName("createdAt")
	val createdAt: String?,

	@field:SerializedName("imageUrl")
	val imageUrl: String?,

	@field:SerializedName("__v")
	val v: Int?,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("_id")
	val id: Int,

	@field:SerializedName("allies")
	val allies:@RawValue List<Any>?,

	@field:SerializedName("updatedAt")
	val updatedAt: String?
) : Parcelable