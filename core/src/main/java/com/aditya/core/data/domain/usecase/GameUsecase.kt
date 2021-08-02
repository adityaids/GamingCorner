package com.aditya.core.data.domain.usecase

import com.aditya.core.data.domain.model.GameDetailModel
import com.aditya.core.data.domain.model.GameModel
import com.aditya.core.data.source.Resource
import kotlinx.coroutines.flow.Flow

interface GameUsecase {
    fun getPopularGame(): Flow<Resource<List<GameModel>>>
    fun getLatestGame(): Flow<Resource<List<GameModel>>>
    fun getDetail(id: Int): Flow<Resource<GameDetailModel>>
    fun setFavorit(game: GameModel)
    fun updateFavorit(game: GameModel)
    fun getFavoritList(): Flow<List<GameModel>>
    fun getSearchGameResult(title: String): Flow<Resource<List<GameModel>>>
}