package com.aditya.gamingcorner.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.aditya.core.data.domain.model.GameDetailModel
import com.aditya.core.data.domain.usecase.GameUsecase
import com.aditya.core.data.source.Resource

class MainViewModel(private val gameUsecase: GameUsecase): ViewModel() {
    val popularGameList = gameUsecase.getPopularGame().asLiveData()
    val latestGameList = gameUsecase.getLatestGame().asLiveData()
    fun getDetailGame(id: Int): LiveData<Resource<GameDetailModel>> {
        return gameUsecase.getDetail(id).asLiveData()
    }
}