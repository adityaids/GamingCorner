package com.aditya.core.data.domain.usecase

import com.aditya.core.data.domain.model.GameModel
import com.aditya.core.data.domain.repository.IGameRepository
import com.aditya.core.data.source.Resource
import kotlinx.coroutines.flow.Flow

class GameInteractor(private val gameRepository: IGameRepository): GameUsecase {
    override fun getPopularGame(): Flow<Resource<List<GameModel>>> =
        gameRepository.getPopularGame()

    override fun getLatestGame(): Flow<Resource<List<GameModel>>> =
        gameRepository.getLatestGame()

    override fun getDetail(id: Int): Flow<Resource<GameModel>> =
        gameRepository.getDetail(id)

    override fun setFavorit(game: GameModel) = gameRepository.setFavorit(game)

    override fun updateFavorit(game: GameModel) = gameRepository.updateFavorit(game)

    override fun getFavoritList(): Flow<List<GameModel>> = gameRepository.getFavoritList()

    override fun getSearchGameResult(title: String): Flow<Resource<List<GameModel>>> =
        gameRepository.getSearchGameResult(title)


}