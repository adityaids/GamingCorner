package com.aditya.core.util

import com.aditya.core.data.domain.model.GameDetailModel
import com.aditya.core.data.domain.model.GameModel
import com.aditya.core.data.source.local.entity.GameEntity
import com.aditya.core.data.source.remote.response.GameResponse
import com.aditya.core.data.source.remote.response.GamesDetailResponse

object DataMapper {

    fun mapResponsesToDomain(input: List<GameResponse>): List<GameModel> {
        val gameList = ArrayList<GameModel>()
        input.map {
            val game = GameModel(
                id = it.id,
                name = it.name,
                gameImage = it.image,
                released = it.released,
                rating = it.rating,
                isFavorite = false
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
                isFavorite = it.isFavorite
            )
        }
    fun mapDomainToEntity(input: GameModel) = GameEntity(
        id = input.id,
        name = input.name,
        gameImage = input.gameImage,
        released = input.released,
        rating = input.rating,
        isFavorite = input.isFavorite
    )
    fun mapResponseDetailToDomain(input: GamesDetailResponse) = GameDetailModel(
        id = input.id,
        name = input.name,
        gameImage = input.image,
        description = input.description,
        released = input.released,
        rating = input.rating,
        isFavorite = false
    )
}