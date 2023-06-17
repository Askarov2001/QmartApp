package com.example.qmartapp.base.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "basket")
data class BasketEntity(
    @PrimaryKey val id: String,
    val name: String,
    val image: String?,
    val count: Int,
    val price: Int,
    val sellerId: String?
)