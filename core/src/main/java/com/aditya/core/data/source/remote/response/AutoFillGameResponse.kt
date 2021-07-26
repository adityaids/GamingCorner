package com.aditya.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class AutoFillGameResponse (
    @field:SerializedName("name")
    val gameTitle: String
        )