package com.example.qmartapp.data

import java.util.UUID


data class Order(
    val products: List<OrderProduct>? = null,
    val address: String? = null,
    val date: String? = null,
    val time: String? = null,
    val clientName: String? = null,
    val comment: String? = null,
    val bankCard: BankCard? = null,
    val productsCost: Int? = null,
    val deliveryCost: Int? = null,
    val tipCost: Int = 0,
    val totalCost: Int? = null,
    val id: String = UUID.randomUUID().toString()
)

data class OrderProduct(
    val name: String,
    val image: Int,
    val count: Int,
    val price: Int
)
