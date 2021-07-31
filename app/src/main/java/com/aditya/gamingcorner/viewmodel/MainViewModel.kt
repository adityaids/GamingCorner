package com.aditya.gamingcorner.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.aditya.core.data.domain.model.GameModel
import com.aditya.core.data.domain.usecase.GameUsecase

class MainViewModel(private val gameUsecase: GameUsecase): ViewModel() {
    val popularGameList = gameUsecase.getPopularGame().asLiveData()
    val latestGameList = gameUsecase.getLatestGame().asLiveData()
    fun getDetailGame(id: Int) = gameUsecase.getDetail(id).asLiveData()
    fun setFavorit(gameModel: GameModel) = gameUsecase.setFavorit(gameModel)
    fun updateFavorit(gameModel: GameModel) = gameUsecase.setFavorit(gameModel)
}