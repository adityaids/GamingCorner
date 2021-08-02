package com.aditya.core.data

import com.aditya.core.data.domain.model.GameDetailModel
import com.aditya.core.data.domain.model.GameModel
import com.aditya.core.data.domain.repository.IGameRepository
import com.aditya.core.data.source.NetworkBoundSource
import com.aditya.core.data.source.Resource
import com.aditya.core.data.source.local.LocalDataSource
import com.aditya.core.data.source.remote.RemoteDataSource
import com.aditya.core.data.source.remote.network.ApiResponse
import com.aditya.core.data.source.remote.response.GameResponse
import com.aditya.core.util.AppExecutor
import com.aditya.core.util.DataMapper
import kotlinx.coroutines.flow.*

class DataRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutor: AppExecutor
) : IGameRepository {

    override fun getPopularGame(): Flow<Resource<List<GameModel>>> =
    object : NetworkBoundSource<List<GameModel>, List<GameResponse>>(){
        override fun loadFromDB(): Flow<List<GameModel>> {
            return localDataSource.getPopularGame().map { DataMapper.mapEntitiesToDomain(it) }
        }

        override fun shouldFetch(data: List<GameModel>?): Boolean =
            data == null || data.isEmpty()

        override suspend fun createCall(): Flow<ApiResponse<List<GameResponse>>> =
            remoteDataSource.getPopular()

        override suspend fun saveCallResult(data: List<GameResponse>) {
            val gameList = DataMapper.mapResponsesToEntities(data)
            localDataSource.insertGame(gameList)
        }

    }.asFlow()

    override fun getLatestGame(): Flow<Resource<List<GameModel>>> =
        object : NetworkBoundSource<List<GameModel>, List<GameResponse>>(){
            override fun loadFromDB(): Flow<List<GameModel>> {
                return localDataSource.getLatestGame().map { DataMapper.mapEntitiesToDomain(it) }
            }

            override fun shouldFetch(data: List<GameModel>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<GameResponse>>> =
                remoteDataSource.getLatest()

            override suspend fun saveCallResult(data: List<GameResponse>) {
                val gameList = DataMapper.mapResponsesLatestToEntities(data)
                localDataSource.insertGame(gameList)
            }

        }.asFlow()

    override fun getDetail(id: Int): Flow<Resource<GameDetailModel>> =
        flow {
            when (val apiResponse = remoteDataSource.getGameDetail(id).first()) {
                is ApiResponse.Success -> {
                    val data = DataMapper.mapDetailResponseToDomain(apiResponse.data)
                    emit(Resource.Success(data))
                }
                is ApiResponse.Empty -> emit(Resource.Success(GameDetailModel()))
                is ApiResponse.Error -> emit(Resource.Error<GameDetailModel>(apiResponse.errorMessage))
            }
        }

    override fun setFavorit(game: GameModel) {
        val gameEntity = DataMapper.mapDomainToEntity(game)
        appExecutor.diskIO().execute{localDataSource.updateFavorit(gameEntity)}
    }

    override fun updateFavorit(game: GameModel) {
        val gameEntity = DataMapper.mapDomainToEntity(game)
        appExecutor.diskIO().execute{localDataSource.updateFavorit(gameEntity)}
    }

    override fun getFavoritList(): Flow<List<GameModel>> {
        return localDataSource.getAllFavorit().map { DataMapper.mapEntitiesToDomain(it) }
    }

    override fun getSearchGameResult(title: String): Flow<Resource<List<GameModel>>> =
        object : NetworkBoundSource<List<GameModel>, List<GameResponse>>(){
            override fun loadFromDB(): Flow<List<GameModel>> {
                return localDataSource.getSearchGameResult(title).map { DataMapper.mapEntitiesToDomain(it) }
            }

            override fun shouldFetch(data: List<GameModel>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<GameResponse>>> =
                remoteDataSource.getSearchGameResult(title)


            override suspend fun saveCallResult(data: List<GameResponse>) {
                val game = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertGame(game)
            }

        }.asFlow()

}