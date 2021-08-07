package com.aditya.gamingcorner.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.aditya.core.data.domain.model.GameModel
import com.aditya.core.data.domain.usecase.GameUsecase

class SearchViewModel(private val gameUsecase: GameUsecase): ViewModel() {

    fun getSearchGameResult(title: String) = gameUsecase.getSearchGameResult(title).asLiveData()
    fun getDetailGame(id: Int) = gameUsecase.getDetail(id).asLiveData()
    fun setFavorit(gameModel: GameModel) = gameUsecase.setFavorit(gameModel)
    fun updateFavorit(gameModel: GameModel) = gameUsecase.setFavorit(gameModel)
}