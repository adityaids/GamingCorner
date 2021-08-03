package com.aditya.core.data.source.local

import com.aditya.core.data.source.local.entity.GameEntity
import com.aditya.core.data.source.local.room.GameDao

class LocalDataSource(private val gameDao: GameDao) {
    fun getPopularGame() = gameDao.getPopularGame()
    fun getLatestGame() = gameDao.getLatestGame()
    fun getAllFavorit() = gameDao.getAllFavorite()
    fun updateFavorit(game: GameEntity) = gameDao.updateGame(game)
    fun getSearchGameResult(name: String) = gameDao.getSearchGameResult(name)
    suspend fun insertGame(gameList: List<GameEntity>) = gameDao.insertGame(gameList)
}