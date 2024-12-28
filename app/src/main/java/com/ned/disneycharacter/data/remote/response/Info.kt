package com.ned.disneycharacter.data.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Info(

	@field:SerializedName("previousPage")
	val previousPage: String?,

	@field:SerializedName("nextPage")
	val nextPage: String?,

	@field:SerializedName("totalPages")
	val totalPages: Int?,

	@field:SerializedName("count")
	val count: Int
): Parcelable