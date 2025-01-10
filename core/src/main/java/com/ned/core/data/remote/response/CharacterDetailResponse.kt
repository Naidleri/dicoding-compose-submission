package com.ned.core.data.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class CharacterDetailResponse(
    @field:SerializedName("data")
    val  data: DataItem,

    @field:SerializedName("info")
    val info: Info
): Parcelable
