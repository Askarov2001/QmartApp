package com.example.qmartapp.base.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favourites")
data class FavouritesEntity(
    @PrimaryKey val id: String,
    val image: String?,
    val name: String,
    val price: Int,
    val count: Int,
    val sellerId: String?
)