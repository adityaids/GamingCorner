package com.aditya.core.data.source.local.room

import androidx.room.*
import com.aditya.core.data.source.local.entity.GameDetailEntity
import com.aditya.core.data.source.local.entity.GameEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoritDao {
    @Query("SELECT * FROM Game where latest = 1")
    fun getLatestGame(): Flow<List<GameEntity>>

    @Query("SELECT * FROM Game where rating > 4.0")
    fun getPopularGame(): Flow<List<GameEntity>>

    @Query("SELECT * FROM Game where isFavorite = 1")
    fun getAllFavorite(): Flow<List<GameEntity>>

    @Query("SELECT * FROM game_detail where id = :id")
    fun getDetailGame(id: Int): Flow<GameDetailEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGame(game: List<GameEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDetailGame(game: GameDetailEntity)

    @Update
    fun updateGame(game: GameEntity)
}