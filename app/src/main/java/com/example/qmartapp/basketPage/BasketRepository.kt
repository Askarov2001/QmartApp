package com.example.qmartapp.basketPage

import com.example.qmartapp.base.database.BasketEntity
import com.example.qmartapp.base.database.dao.BasketDao

class BasketRepository(private val dao: BasketDao) {
    suspend fun getAll(): List<BasketEntity> = dao.getAll()

    suspend fun increaseCount(id: String) {
        val item = dao.getById(id)
        item?.let {
            dao.updateCount(id, item.count + 1)
        }
    }

    suspend fun decreaseCount(id: String) {
        val item = dao.getById(id)
        item?.let {
            if ((item.count - 1) < 1) {
                dao.delete(item)
            } else {
                dao.updateCount(id, item.count - 1)
            }
        }
    }

    suspend fun add(item: BasketEntity): Boolean {
        if (dao.getById(item.id) != null) {
            return false
        } else {
            dao.insert(item)
            return true
        }
    }

    suspend fun deleteItem(id: String) {
        if (dao.getById(id) != null) {
            dao.delete(dao.getById(id)!!)
        }
    }
}