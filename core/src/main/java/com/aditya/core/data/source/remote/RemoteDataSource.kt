package com.aditya.core.data.source.remote

import android.annotation.SuppressLint
import android.util.Log
import com.aditya.core.data.source.remote.network.ApiResponse
import com.aditya.core.data.source.remote.response.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {
    companion object{
        const val API_KEY: String = "4fa30b393b76408194e80b0dde0e3860"
    }

    @SuppressLint("CheckResult")
    suspend fun getPopular(): Flow<ApiResponse<List<GameResponse>>> {
        return flow {
            try {
                val response = apiService.popularGames(API_KEY)
                val dataArray = response.gameList
                if (dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(response.gameList))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
    @SuppressLint("CheckResult")
    suspend fun getLatest(): Flow<ApiResponse<List<GameResponse>>>{
        return flow {
            try {
                val response = apiService.latestGame(API_KEY)
                val dataArray = response.gameList
                if (dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(response.gameList))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.Default)
    }

    @SuppressLint("CheckResult")
    suspend fun getSearchResult(title: String): Flow<ApiResponse<List<AutoFillGameResponse>>>{
        return flow {
            try {
                val response = apiService.searchGame(API_KEY, title)
                val dataArray = response.autoFillGameList
                if (dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(response.autoFillGameList))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
    @SuppressLint("CheckResult")
    suspend fun getGameDetail(id: Int): Flow<ApiResponse<GamesDetailResponse>> {
        return flow {
            try {
                val response = apiService.getGameDetail(API_KEY, id)
                emit(ApiResponse.Success(response))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}