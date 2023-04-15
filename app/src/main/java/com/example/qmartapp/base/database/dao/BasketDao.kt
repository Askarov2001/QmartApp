package com.example.qmartapp.base.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.qmartapp.base.database.BasketEntity

@Dao
interface BasketDao {
    @Query("SELECT * FROM basket")
    suspend fun getAll(): List<BasketEntity>

    @Query("SELECT * FROM basket WHERE id IN (:basketId)")
    suspend fun getById(basketId: String): BasketEntity?

    @Insert
    suspend fun insert(users: BasketEntity)

    @Query("UPDATE basket SET count =:count WHERE id = :basketId")
    suspend fun updateCount(basketId: String, count: Int)

    @Delete
    suspend fun delete(user: BasketEntity)
}