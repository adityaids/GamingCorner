package com.aditya.gamingcorner.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.aditya.core.data.domain.model.GameModel
import com.aditya.core.data.domain.usecase.GameUsecase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*

class SearchViewModel(private val gameUsecase: GameUsecase): ViewModel() {

    fun getSearchGameResult(title: String) = gameUsecase.getSearchGameResult(title).asLiveData()
    fun getDetailGame(id: Int) = gameUsecase.getDetail(id).asLiveData()
    fun setFavorit(gameModel: GameModel) = gameUsecase.setFavorit(gameModel)
    fun updateFavorit(gameModel: GameModel) = gameUsecase.setFavorit(gameModel)
}