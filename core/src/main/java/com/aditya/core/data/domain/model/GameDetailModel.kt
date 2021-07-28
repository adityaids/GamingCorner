package com.aditya.core.data.domain.model

import android.os.Parcelable


data class GameDetailModel(
    val id: String,
    val name: String,
    val gameImage: String,
    val description: String,
    val released: String,
    val rating: Float,
    var isFavorite: Boolean,
    var isLatest: Boolean
)