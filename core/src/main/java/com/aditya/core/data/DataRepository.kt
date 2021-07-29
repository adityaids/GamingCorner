package com.aditya.core.data

import android.util.Log
import com.aditya.core.data.domain.model.GameDetailModel
import com.aditya.core.data.domain.model.GameModel
import com.aditya.core.data.domain.repository.IGameRepository
import com.aditya.core.data.source.NetworkBoundSource
import com.aditya.core.data.source.Resource
import com.aditya.core.data.source.local.LocalDataSource
import com.aditya.core.data.source.remote.RemoteDataSource
import com.aditya.core.data.source.remote.network.ApiResponse
import com.aditya.core.data.source.remote.response.GameResponse
import com.aditya.core.data.source.remote.response.GamesDetailResponse
import com.aditya.core.util.DataMapper
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DataRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
) : IGameRepository {

    override fun getPopularGame(): Flowable<Resource<List<GameModel>>> =
    object : NetworkBoundSource<List<GameModel>, List<GameResponse>>(){
        override fun loadFromDB(): Flowable<List<GameModel>> {
            return localDataSource.getPopularGame().map { DataMapper.mapEntitiesToDomain(it) }
        }

        override fun shouldFetch(data: List<GameModel>?): Boolean =
            data == null || data.isEmpty()

        override fun createCall(): Flowable<ApiResponse<List<GameResponse>>> =
            remoteDataSource.getPopular()

        override fun saveCallResult(data: List<GameResponse>) {
            val gameList = DataMapper.mapResponsesPopularToEntities(data)
            localDataSource.insertGame(gameList)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
        }

    }.asFlowable()

    override fun getLatestGame(): Flowable<Resource<List<GameModel>>> =
        object : NetworkBoundSource<List<GameModel>, List<GameResponse>>(){
            override fun loadFromDB(): Flowable<List<GameModel>> {
                return localDataSource.getLatestGame().map { DataMapper.mapEntitiesToDomain(it) }
            }

            override fun shouldFetch(data: List<GameModel>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): Flowable<ApiResponse<List<GameResponse>>> =
                remoteDataSource.getLatest()

            override fun saveCallResult(data: List<GameResponse>) {
                val gameList = DataMapper.mapResponsesLatestToEntities(data)
                localDataSource.insertGame(gameList)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe()
            }

        }.asFlowable()

    override fun getDetail(id: Int): Flowable<Resource<GameDetailModel>> =
        object : NetworkBoundSource<GameDetailModel, GamesDetailResponse>(){
            override fun loadFromDB(): Flowable<GameDetailModel> {
                Log.d("loadDb", localDataSource.getDetailGame(id).toString())
                return localDataSource.getDetailGame(id).map { DataMapper.mapDetailEntityToDomain(it) }
            }

            override fun shouldFetch(data: GameDetailModel?): Boolean = true

            override fun createCall(): Flowable<ApiResponse<GamesDetailResponse>> =
                remoteDataSource.getGameDetail(id)

            override fun saveCallResult(data: GamesDetailResponse) {
                val gameDetail = DataMapper.mapDetailResponseToDomain(data)
                Log.d("saveCall", gameDetail.name)
                localDataSource.insertDetailGame(gameDetail)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe()
            }
        }.asFlowable()

    override fun setFavorit(game: GameModel) {
        localDataSource.updateFavorit(DataMapper.mapDomainToEntity(game))
    }

    override fun updateFavorit(game: GameModel) {
        localDataSource.updateFavorit(DataMapper.mapDomainToEntity(game))
    }

    override fun getFavoritList(): Flowable<List<GameModel>> {
        return localDataSource.getAllFavorit().map { DataMapper.mapEntitiesToDomain(it) }
    }

}