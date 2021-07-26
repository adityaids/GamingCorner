package com.aditya.core.data.domain.usecase

import com.aditya.core.data.domain.repository.IGameRepository

class GameInteractor(private val gameRepository: IGameRepository): GameUsecase {
}