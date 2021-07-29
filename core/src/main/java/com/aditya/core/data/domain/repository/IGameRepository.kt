package com.aditya.core.data.domain.repository

import com.aditya.core.data.domain.model.GameDetailModel
import com.aditya.core.data.domain.model.GameModel
import com.aditya.core.data.source.Resource
import io.reactivex.Completable
import io.reactivex.Flowable

interface IGameRepository {
    fun getPopularGame(): Flowable<Resource<List<GameModel>>>
    fun getLatestGame(): Flowable<Resource<List<GameModel>>>
    fun getDetail(id: Int): Flowable<Resource<GameDetailModel>>
    fun setFavorit(game: GameModel)
    fun updateFavorit(game: GameModel)
    fun getFavoritList(): Flowable<List<GameModel>>
}