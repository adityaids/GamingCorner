package com.aditya.core.data.domain.usecase

import com.aditya.core.data.domain.model.GameDetailModel
import com.aditya.core.data.domain.model.GameModel
import com.aditya.core.data.source.Resource
import io.reactivex.Flowable

interface GameUsecase {
    fun getPopularGame(): Flowable<Resource<List<GameModel>>>
    fun getLatestGame(): Flowable<Resource<List<GameModel>>>
    fun getDetail(id: String): Flowable<Resource<GameDetailModel>>
    fun setFavorit(game: GameModel)
    fun updateFavorit(game: GameModel)
    fun getFavoritList(): Flowable<List<GameModel>>
}