package com.example.qmartapp.base.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.qmartapp.base.database.dao.BasketDao
import com.example.qmartapp.base.database.dao.FavouriteDao

@Database(entities = [FavouritesEntity::class, BasketEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun basketDao(): BasketDao

    abstract fun favouriteDao(): FavouriteDao

}