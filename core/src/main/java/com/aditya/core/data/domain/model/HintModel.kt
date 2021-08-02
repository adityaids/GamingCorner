package com.aditya.core.data.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class HintModel(
    val gameTitle: String
    ): Parcelable