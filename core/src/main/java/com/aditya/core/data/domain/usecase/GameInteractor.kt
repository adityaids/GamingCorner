package com.aditya.core.data.domain.usecase

import com.aditya.core.data.domain.model.GameDetailModel
import com.aditya.core.data.domain.model.GameModel
import com.aditya.core.data.domain.repository.IGameRepository
import com.aditya.core.data.source.Resource
import io.reactivex.Flowable

class GameInteractor(private val gameRepository: IGameRepository): GameUsecase {
    override fun getPopularGame(): Flowable<Resource<List<GameModel>>> =
        gameRepository.getPopularGame()

    override fun getLatestGame(): Flowable<Resource<List<GameModel>>> =
        gameRepository.getLatestGame()

    override fun getDetail(id: Int): Flowable<Resource<GameDetailModel>> =
        gameRepository.getDetail(id)

    override fun setFavorit(game: GameModel) = gameRepository.setFavorit(game)

    override fun updateFavorit(game: GameModel) = gameRepository.updateFavorit(game)

    override fun getFavoritList(): Flowable<List<GameModel>> = gameRepository.getFavoritList()

}