package com.ned.core.data.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class CharacterResponse(

	@field:SerializedName("data")
	val  data: List<DataItem>,

	@field:SerializedName("info")
	val info: Info
): Parcelable