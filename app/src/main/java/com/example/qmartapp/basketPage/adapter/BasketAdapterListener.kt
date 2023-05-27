package com.example.qmartapp.basketPage.adapter

interface BasketAdapterListener {
    fun onDelete(id: String)
    fun onCounterClick(id: String, count: Int)
}