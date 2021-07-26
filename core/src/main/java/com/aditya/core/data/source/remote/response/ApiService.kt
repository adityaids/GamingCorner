package com.aditya.core.data.source.remote.response

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("games?key={key}&search={search}")
    suspend fun searchGame(
        @Query("key") key: String,
        @Query("search") search: String
    ): AutoFillGameListResponse

    @GET("games?key={key}&ordering=released")
    suspend fun latestGame(
        @Query("key") key: String
    ): ListGameResponse

    @GET("games?key={key}&metacritics=75")
    suspend fun popularGames(
        @Query("key") key: String
    ): ListGameResponse

    @GET("games?key={key}/{id}")
    suspend fun getGameDetail(
        @Query("key") key: String,
        @Path("id") gamesId: String
    ): GamesDetailResponse
}