package com.aditya.core.data.domain.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

data class GameModel(
    val id: Int,
    val name: String,
    val gameImage: String,
    val released: String?,
    val rating: Float,
    var isFavorite: Boolean,
    var isLatest: Boolean
)