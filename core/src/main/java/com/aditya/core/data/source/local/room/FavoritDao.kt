package com.aditya.core.data.source.local.room

import androidx.room.*
import com.aditya.core.data.domain.model.GameDetailModel
import com.aditya.core.data.source.local.entity.GameDetailEntity
import com.aditya.core.data.source.local.entity.GameEntity
import io.reactivex.Completable
import io.reactivex.Flowable
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoritDao {
    @Query("SELECT * FROM Game where latest = 1")
    fun getLatestGame(): Flowable<List<GameEntity>>

    @Query("SELECT * FROM Game where rating > 4.0")
    fun getPopularGame(): Flowable<List<GameEntity>>

    @Query("SELECT * FROM Game where isFavorite = 1")
    fun getAllFavorite(): Flowable<List<GameEntity>>

    @Query("SELECT * FROM game_detail where id = :id")
    fun getDetailGame(id: String): Flowable<GameDetailEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGame(game: List<GameEntity>): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDetailGame(game: GameDetailEntity): Completable

    @Update
    fun updateGame(game: GameEntity): Completable
}