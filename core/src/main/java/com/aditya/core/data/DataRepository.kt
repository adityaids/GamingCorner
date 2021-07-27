package com.aditya.core.data

import com.aditya.core.data.domain.model.GameDetailModel
import com.aditya.core.data.domain.model.GameModel
import com.aditya.core.data.domain.repository.IGameRepository
import com.aditya.core.data.source.local.LocalDataSource
import com.aditya.core.data.source.remote.RemoteDataSource
import io.reactivex.Flowable

class DataRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
) : IGameRepository {
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

    override fun getFavoritList(): Flowable<List<GameModel>> {
        TODO("Not yet implemented")
    }

}