package com.aditya.core.data.domain.usecase

import com.aditya.core.data.domain.model.GameDetailModel
import com.aditya.core.data.domain.model.GameModel
import io.reactivex.Flowable

interface GameUsecase {
    fun getPopularGame(): Flowable<List<GameModel>>
    fun getLatestGame(): Flowable<List<GameModel>>
    fun getDetail(id: String): Flowable<GameDetailModel>
    fun setFavorit(game: GameModel)
    fun getFavoritList(): Flowable<List<GameModel>>
}