package com.aditya.core.data.source.remote.response

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("games?key={key}&search={search}")
    suspend fun searchGame(
        @Path("key") key: String,
        @Path("search") search: String,
        @Query(value = "autocomplete") autoComplete: Boolean = true
    ): AutoFillGameListResponse

    @GET("games?key={key}&ordering=released")
    suspend fun latestGame(
        @Path("key") key: String
    ): ListGameResponse

    @GET("games?key={key}&metacritics=75")
    suspend fun popularGames(
        @Path("key") key: String
    ): ListGameResponse
}