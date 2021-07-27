package com.aditya.core.util

import com.aditya.core.data.domain.model.GameDetailModel
import com.aditya.core.data.domain.model.GameModel
import com.aditya.core.data.source.local.entity.GameEntity
import com.aditya.core.data.source.remote.response.GameResponse
import com.aditya.core.data.source.remote.response.GamesDetailResponse

object DataMapper {

    fun mapResponsesPopularToEntities(input: List<GameResponse>): List<GameEntity> {
        val gameList = ArrayList<GameEntity>()
        input.map {
            val game = GameEntity(
                id = it.id,
                name = it.name,
                gameImage = it.image,
                released = it.released,
                rating = it.rating,
                isFavorite = false,
                isLatest = false
            )
            gameList.add(game)
        }
        return gameList
    }

    fun mapResponsesLatestToEntities(input: List<GameResponse>): List<GameEntity> {
        val gameList = ArrayList<GameEntity>()
        input.map {
            val game = GameEntity(
                id = it.id,
                name = it.name,
                gameImage = it.image,
                released = it.released,
                rating = it.rating,
                isFavorite = false,
                isLatest = true
            )
            gameList.add(game)
        }
        return gameList
    }

    fun mapEntitiesToDomain(input: List<GameEntity>): List<GameModel> =
        input.map {
            GameModel(
                id = it.id,
                name = it.name,
                gameImage = it.gameImage,
                released = it.released,
                rating = it.rating,
                isFavorite = it.isFavorite,
                isLatest = it.isLatest
            )
        }
    fun mapDomainToEntity(input: GameModel) = GameEntity(
        id = input.id,
        name = input.name,
        gameImage = input.gameImage,
        released = input.released,
        rating = input.rating,
        isFavorite = input.isFavorite,
        isLatest = input.isLatest
    )
}