package com.aditya.gamingcorner.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.aditya.core.data.domain.model.GameDetailModel
import com.aditya.core.data.domain.usecase.GameUsecase
import io.reactivex.Flowable

class FavoritViewModel(private val gameUsecase: GameUsecase): ViewModel() {
    val favoritGameList = LiveDataReactiveStreams.fromPublisher(gameUsecase.getFavoritList())
    fun getDetailGame(id: String): LiveData<GameDetailModel> = LiveDataReactiveStreams.fromPublisher(gameUsecase.getDetail(id))

}