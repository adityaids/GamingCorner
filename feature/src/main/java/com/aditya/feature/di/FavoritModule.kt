package com.aditya.feature.di

import com.aditya.feature.viewmodel.FavoritViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoritModule = module {
    viewModel { FavoritViewModel(get()) }
}