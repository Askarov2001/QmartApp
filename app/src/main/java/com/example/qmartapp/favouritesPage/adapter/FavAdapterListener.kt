package com.example.qmartapp.basketPage.adapter

import com.example.qmartapp.base.database.FavouritesEntity

interface FavAdapterListener {
    fun onDelete(id: String)
    fun onAdd(item: FavouritesEntity)
}