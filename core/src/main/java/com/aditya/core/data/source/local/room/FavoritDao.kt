package com.aditya.core.data.source.local.room

import androidx.room.*
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

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGame(game: List<GameEntity>): Completable

    @Update
    fun updateGame(game: GameEntity): Completable
}