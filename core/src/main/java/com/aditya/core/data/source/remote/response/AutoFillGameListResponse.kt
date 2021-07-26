package com.aditya.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class AutoFillGameListResponse (
    @field:SerializedName("results")
    val autoFillGameList: List<AutoFillGameResponse>
)