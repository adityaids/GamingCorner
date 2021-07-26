package com.aditya.core.data

import com.aditya.core.data.domain.repository.IGameRepository
import com.aditya.core.data.source.local.LocalDataSource
import com.aditya.core.data.source.remote.RemoteDataSource

class DataRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
) : IGameRepository {

}