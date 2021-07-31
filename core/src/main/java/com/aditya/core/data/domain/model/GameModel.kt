package com.aditya.core.data.domain.model

data class GameModel(
    val id: Int,
    val name: String,
    val gameImage: String,
    val released: String?,
    val rating: Float,
    var isFavorite: Boolean,
    var isLatest: Boolean
)