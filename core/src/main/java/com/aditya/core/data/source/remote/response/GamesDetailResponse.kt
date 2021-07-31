package com.aditya.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class GamesDetailResponse (
    @field:SerializedName("id")
    val id: Int,
    @field:SerializedName("name")
    val name: String,
    @field:SerializedName("background_image")
    val image: String,
    @field:SerializedName("released")
    val released: String,
    @field:SerializedName("description_raw")
    val description: String,
    @field:SerializedName("rating")
    val rating: Float,
    var isFavorited: Boolean = false,
    var isLatest: Boolean = false
        )