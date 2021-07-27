package com.aditya.core.data.source.local.room

import androidx.room.*
import com.aditya.core.data.source.local.entity.GameEntity
import io.reactivex.Completable
import io.reactivex.Flowable
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoritDao {
    @Query("SELECT * FROM favorite")
    fun getAllFavorite(): Flowable<List<GameEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGame(game: GameEntity): Completable

    @Delete
    fun deleteGame(game: GameEntity): Completable
}