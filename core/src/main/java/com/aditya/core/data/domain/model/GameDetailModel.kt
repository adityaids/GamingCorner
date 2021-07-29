package com.aditya.core.data.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GameDetailModel(
    val id: Int,
    val name: String,
    val gameImage: String,
    val description: String,
    val released: String,
    val rating: Float,
    var isFavorite: Boolean,
    var isLatest: Boolean
): Parcelable