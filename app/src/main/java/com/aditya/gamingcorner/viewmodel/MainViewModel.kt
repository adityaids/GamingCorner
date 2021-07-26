package com.aditya.gamingcorner.viewmodel

import androidx.lifecycle.ViewModel
import com.aditya.core.data.domain.usecase.GameUsecase

class MainViewModel(private val gameUsecase: GameUsecase): ViewModel() {
}