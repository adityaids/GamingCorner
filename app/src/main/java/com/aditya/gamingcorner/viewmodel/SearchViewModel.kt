package com.aditya.gamingcorner.viewmodel

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.aditya.core.data.domain.model.GameModel
import com.aditya.core.data.domain.usecase.GameUsecase

class SearchViewModel(private val gameUsecase: GameUsecase): ViewModel() {
    fun setFavorit(game: GameModel) = gameUsecase.setFavorit(game)
    fun getDetailGame(id: String) = LiveDataReactiveStreams.fromPublisher(gameUsecase.getDetail(id))
}