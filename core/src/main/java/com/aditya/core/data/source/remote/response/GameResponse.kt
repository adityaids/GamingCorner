package com.aditya.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

class GameResponse (
    @field:SerializedName("id")
    val id: Int,
    @field:SerializedName("name")
    val name: String,
    @field:SerializedName("background_image")
    val image: String?,
    @field:SerializedName("released")
    val released: String?,
    @field:SerializedName("rating")
    val rating: Float
        )