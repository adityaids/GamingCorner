package com.aditya.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class AutoFillGameListResponse (
    @field:SerializedName("error")
    val error: Boolean,

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("results")
    val autoFillGameList: List<AutoFillGameResponse>
)