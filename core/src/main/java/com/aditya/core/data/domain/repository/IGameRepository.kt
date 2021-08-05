package com.aditya.core.data.domain.repository

import com.aditya.core.data.domain.model.GameModel
import com.aditya.core.data.source.Resource
import kotlinx.coroutines.flow.Flow

interface IGameRepository {
    fun getPopularGame(): Flow<Resource<List<GameModel>>>
    fun getLatestGame(): Flow<Resource<List<GameModel>>>
    fun getDetail(id: Int): Flow<Resource<GameModel>>
    fun setFavorit(game: GameModel)
    fun updateFavorit(game: GameModel)
    fun getFavoritList(): Flow<List<GameModel>>
    fun getSearchGameResult(title: String): Flow<Resource<List<GameModel>>>
}