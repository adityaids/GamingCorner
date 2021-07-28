package com.aditya.core.data.source.remote.response

import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("games?key=&search=")
    fun searchGame(
        @Query("key") key: String,
        @Query("search") search: String
    ): Flowable<AutoFillGameListResponse>

    @GET("games?key=&ordering=released")
    fun latestGame(
        @Query("key") key: String
    ): Flowable<ListGameResponse>

    @GET("games?key=&metacritics=75")
    fun popularGames(
        @Query("key") key: String
    ): Flowable<ListGameResponse>

    @GET("games?key=/{id}")
    fun getGameDetail(
        @Query("key") key: String,
        @Path("id") gamesId: String
    ): Flowable<GamesDetailResponse>
}