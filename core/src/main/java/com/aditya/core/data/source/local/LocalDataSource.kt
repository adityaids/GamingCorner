package com.aditya.core.data.source.local

import com.aditya.core.data.source.local.entity.GameEntity
import com.aditya.core.data.source.local.room.FavoritDao

class LocalDataSource(private val favoritDao: FavoritDao) {
    fun getAllFavorit() = favoritDao.getAllFavorite()
    fun setFavorit(game: GameEntity) = favoritDao.insertGame(game)
}