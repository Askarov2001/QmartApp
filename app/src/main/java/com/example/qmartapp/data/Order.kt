package com.example.qmartapp.data

import java.util.UUID


data class Order(
    val products: List<OrderProduct>? = null,
    val address: String? = null,
    val clientName: String? = null,
    val phone: String? = null,
    val productsCost: Int? = null,
    val deliveryCost: Int? = null,
    val totalCost: Int? = null,
    val id: String = UUID.randomUUID().toString()
)

data class OrderProduct(
    val name: String,
    val image: String?,
    val count: Int,
    val price: Int
)
