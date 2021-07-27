package com.aditya.core.data.domain.repository

import com.aditya.core.data.domain.model.GameDetailModel
import com.aditya.core.data.domain.model.GameModel
import io.reactivex.Completable
import io.reactivex.Flowable

interface IGameRepository {
    fun getPopularGame(): Flowable<List<GameModel>>
    fun getLatestGame(): Flowable<List<GameModel>>
    fun getDetail(id: String): Flowable<GameDetailModel>
    fun setFavorit(game: GameModel)
    fun getFavoritList(): Flowable<List<GameModel>>
}