package com.example.qmartapp.base.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.qmartapp.base.database.FavouritesEntity

@Dao
interface FavouriteDao {
    @Query("SELECT * FROM favourites")
    suspend fun getAll(): List<FavouritesEntity>

    @Query("SELECT * FROM favourites WHERE id IN (:favId)")
    suspend fun getById(favId: String): FavouritesEntity?

    @Insert
    suspend fun insert(users: FavouritesEntity)

    @Delete
    suspend fun delete(user: FavouritesEntity)
}