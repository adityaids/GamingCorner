package com.aditya.core.data.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GameDetailModel(
    var id: Int = 0,
    var name: String? = "",
    var gameImage: String? = "",
    var description: String? = "",
    var released: String? = "",
    var rating: Float = 0.0f,
    var isFavorite: Boolean = false,
    var isLatest: Boolean = false
): Parcelable