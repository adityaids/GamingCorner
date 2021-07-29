package com.aditya.core.data.source.local

import com.aditya.core.data.source.local.entity.GameDetailEntity
import com.aditya.core.data.source.local.entity.GameEntity
import com.aditya.core.data.source.local.room.FavoritDao

class LocalDataSource(private val favoritDao: FavoritDao) {
    fun getPopularGame() = favoritDao.getPopularGame()
    fun getLatestGame() = favoritDao.getLatestGame()
    fun getAllFavorit() = favoritDao.getAllFavorite()
    fun updateFavorit(game: GameEntity) = favoritDao.updateGame(game)
    fun getDetailGame(id: Int) = favoritDao.getDetailGame(id)
    suspend fun insertDetailGame(gameDetail: GameDetailEntity) = favoritDao.insertDetailGame(gameDetail)
    suspend fun insertGame(gameList: List<GameEntity>) = favoritDao.insertGame(gameList)
}