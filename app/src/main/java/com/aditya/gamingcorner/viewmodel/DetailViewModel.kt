package com.aditya.gamingcorner.viewmodel

import androidx.lifecycle.ViewModel
import com.aditya.core.data.domain.model.GameModel
import com.aditya.core.data.domain.usecase.GameUsecase

class DetailViewModel(private val gameUsecase: GameUsecase): ViewModel() {
    fun setFavorit(game: GameModel) = gameUsecase.setFavorit(game)
}