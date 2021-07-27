package com.aditya.core.data.source.remote

import android.annotation.SuppressLint
import android.util.Log
import com.aditya.core.data.source.remote.network.ApiResponse
import com.aditya.core.data.source.remote.response.*
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

class RemoteDataSource(private val apiService: ApiService) {
    private val API_KEY: String = "4fa30b393b76408194e80b0dde0e3860"

    @SuppressLint("CheckResult")
    fun getPopular(): Flowable<ApiResponse<List<GameResponse>>>{
        val resultData = PublishSubject.create<ApiResponse<List<GameResponse>>>()
        val client = apiService.popularGames(API_KEY)

        client
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe ({ response ->
                val dataArray = response.gameList
                resultData.onNext(if (dataArray.isNotEmpty()) ApiResponse.Success(dataArray) else ApiResponse.Empty)
            }, { error ->
                resultData.onNext(ApiResponse.Error(error.message.toString()))
                Log.e("RemoteDataSource", error.toString())
            })

        return resultData.toFlowable(BackpressureStrategy.BUFFER)
    }
    @SuppressLint("CheckResult")
    fun getLatest(): Flowable<ApiResponse<List<GameResponse>>>{
        val resultData = PublishSubject.create<ApiResponse<List<GameResponse>>>()
        val client = apiService.latestGame(API_KEY)

        client
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe ({ response ->
                val dataArray = response.gameList
                resultData.onNext(if (dataArray.isNotEmpty()) ApiResponse.Success(dataArray) else ApiResponse.Empty)
            }, { error ->
                resultData.onNext(ApiResponse.Error(error.message.toString()))
                Log.e("RemoteDataSource", error.toString())
            })

        return resultData.toFlowable(BackpressureStrategy.BUFFER)
    }

    @SuppressLint("CheckResult")
    fun getSearchResult(title: String): Flowable<ApiResponse<List<AutoFillGameResponse>>>{
        val resultData = PublishSubject.create<ApiResponse<List<AutoFillGameResponse>>>()
        val client = apiService.searchGame(API_KEY, title)

        client
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe ({ response ->
                val dataArray = response.autoFillGameList
                resultData.onNext(if (dataArray.isNotEmpty()) ApiResponse.Success(dataArray) else ApiResponse.Empty)
            }, { error ->
                resultData.onNext(ApiResponse.Error(error.message.toString()))
                Log.e("RemoteDataSource", error.toString())
            })

        return resultData.toFlowable(BackpressureStrategy.BUFFER)
    }
    @SuppressLint("CheckResult")
    fun getGameDetail(id: String): Flowable<ApiResponse<GamesDetailResponse>>{
        val resultData = PublishSubject.create<ApiResponse<GamesDetailResponse>>()
        val client = apiService.getGameDetail(API_KEY, id)

        client
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .take(1)
            .subscribe ({ response ->
                resultData.onNext(if (response != null) ApiResponse.Success(response) else ApiResponse.Empty)
            }, { error ->
                resultData.onNext(ApiResponse.Error(error.message.toString()))
                Log.e("RemoteDataSource", error.toString())
            })

        return resultData.toFlowable(BackpressureStrategy.BUFFER)
    }
}