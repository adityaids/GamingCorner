package com.aditya.core.di

import androidx.room.Room
import com.aditya.core.data.DataRepository
import com.aditya.core.data.domain.repository.IGameRepository
import com.aditya.core.data.source.local.LocalDataSource
import com.aditya.core.data.source.local.room.GameDatabase
import com.aditya.core.data.source.remote.RemoteDataSource
import com.aditya.core.data.source.remote.response.ApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<GameDatabase>().favoritDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            GameDatabase::class.java, "Tourism.db"
        ).fallbackToDestructiveMigration().build()
    }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.rawg.io/api/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    single<IGameRepository> { DataRepository(get(), get()) }
}