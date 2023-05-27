package com.example.qmartapp.orderPage

import com.example.qmartapp.data.ClientAddress
import java.util.UUID

class Order(
    val clientAddress: ClientAddress,
    val sum: Int,
    val id: String = UUID.randomUUID().toString(),
    val date: String,
    val client: String
)