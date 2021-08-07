package com.aditya.feature.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.aditya.core.data.domain.model.GameModel
import com.aditya.core.data.domain.usecase.GameUsecase

class FavoritViewModel(private val gameUsecase: GameUsecase): ViewModel() {
    val getFavoritList = gameUsecase.getFavoritList().asLiveData()
    fun getDetailGame(id: Int) = gameUsecase.getDetail(id).asLiveData()
    fun updateFavorit(gameModel: GameModel) = gameUsecase.updateFavorit(gameModel)

}