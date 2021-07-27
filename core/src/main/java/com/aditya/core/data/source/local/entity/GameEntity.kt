package com.aditya.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite")
data class GameEntity (
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    val id: String,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "image")
    val gameImage: String,
    @ColumnInfo(name = "released")
    val released: String,
    @ColumnInfo(name = "rating")
    val rating: Float,
    @ColumnInfo(name = "isFavorite")
    val isFavorite: Boolean
    )