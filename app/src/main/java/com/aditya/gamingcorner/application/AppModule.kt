package com.aditya.gamingcorner.application

import com.aditya.core.data.domain.usecase.GameInteractor
import com.aditya.core.data.domain.usecase.GameUsecase
import com.aditya.gamingcorner.viewmodel.DetailViewModel
import com.aditya.gamingcorner.viewmodel.MainViewModel
import com.aditya.gamingcorner.viewmodel.SearchViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<GameUsecase> { GameInteractor(get()) }
}

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { DetailViewModel(get()) }
    viewModel { SearchViewModel(get()) }
}