package com.example.qmartapp.favouritesPage

import com.example.qmartapp.base.database.FavouritesEntity
import com.example.qmartapp.base.database.dao.FavouriteDao

class FavouritesRepository(private val dao: FavouriteDao) {
    suspend fun getAll(): List<FavouritesEntity> = dao.getAll()


    suspend fun add(item: FavouritesEntity): Boolean {
        return if (dao.getById(item.id) != null) {
            false
        } else {
            dao.insert(item)
            true
        }
    }

    suspend fun deleteItem(id: String) {
        if (dao.getById(id) != null) {
            dao.delete(dao.getById(id)!!)
        }
    }

    suspend fun isFav(id: String): Boolean = dao.getById(id) != null
}