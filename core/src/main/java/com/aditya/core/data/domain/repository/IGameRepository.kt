package com.aditya.core.data.domain.repository

import com.aditya.core.data.domain.model.GameDetailModel
import com.aditya.core.data.domain.model.GameModel
import com.aditya.core.data.source.Resource
import kotlinx.coroutines.flow.Flow

interface IGameRepository {
    fun getPopularGame(): Flow<Resource<List<GameModel>>>
    fun getLatestGame(): Flow<Resource<List<GameModel>>>
    fun getDetail(id: Int): Flow<Resource<GameDetailModel>>
    suspend fun setFavorit(game: GameModel)
    fun updateFavorit(game: GameModel)
    fun getFavoritList(): Flow<List<GameModel>>
}