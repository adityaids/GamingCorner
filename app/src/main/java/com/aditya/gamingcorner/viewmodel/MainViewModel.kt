package com.aditya.gamingcorner.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.aditya.core.data.domain.model.GameDetailModel
import com.aditya.core.data.domain.model.GameModel
import com.aditya.core.data.domain.usecase.GameUsecase
import io.reactivex.Flowable

class MainViewModel(private val gameUsecase: GameUsecase): ViewModel() {
    val popularGameList = LiveDataReactiveStreams.fromPublisher(gameUsecase.getPopularGame())
    val latestGameList = LiveDataReactiveStreams.fromPublisher(gameUsecase.getLatestGame())
    fun getDetailGame(id: String): LiveData<GameDetailModel> = LiveDataReactiveStreams.fromPublisher(gameUsecase.getDetail(id))
    fun setFavorit(game: GameModel) = gameUsecase.setFavorit(game)
}