package com.aditya.core.data.source.remote.response

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("games?key=&search=")
    suspend fun searchGame(
        @Query("key") key: String,
        @Query("search") search: String
    ): ListGameResponse

    @GET("games?key=&ordering=updated")
    suspend fun latestGame(
        @Query("key") key: String
    ): ListGameResponse

    @GET("games?key=&metacritics=75")
    suspend fun popularGames(
        @Query("key") key: String
    ): ListGameResponse

    @GET("games/{id}")
    suspend fun getGameDetail(
        @Path("id") gamesId: Int,
        @Query("key") key: String
    ): GamesDetailResponse

    @GET("games?key=&search=")
    suspend fun getAutoFillHint(
        @Query("key") key: String,
        @Query("search") search: String
    ): HintListResponse
}