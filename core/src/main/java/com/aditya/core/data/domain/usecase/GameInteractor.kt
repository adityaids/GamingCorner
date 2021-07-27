package com.aditya.core.data.domain.usecase

import com.aditya.core.data.domain.model.GameDetailModel
import com.aditya.core.data.domain.model.GameModel
import com.aditya.core.data.domain.repository.IGameRepository
import io.reactivex.Flowable

class GameInteractor(private val gameRepository: IGameRepository): GameUsecase {
    override fun getPopularGame(): Flowable<List<GameModel>> {
        TODO("Not yet implemented")
    }

    override fun getLatestGame(): Flowable<List<GameModel>> {
        TODO("Not yet implemented")
    }

    override fun getDetail(id: String): Flowable<GameDetailModel> {
        TODO("Not yet implemented")
    }

    override fun setFavorit(game: GameModel) {
        TODO("Not yet implemented")
    }

    override fun deleteFavorit(game: GameModel) {
        TODO("Not yet implemented")
    }

    override fun getFavoritList(): Flowable<List<GameModel>> {
        TODO("Not yet implemented")
    }
}