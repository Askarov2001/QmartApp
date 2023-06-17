package com.example.qmartapp.productsPage.products

import com.example.qmartapp.R


data class Product(
    val id: String,
    val name: String,
    val category: String,
    val description: String,
    val cost: Int,
    val status: String?,
    val images: String?,
    val sellerId: String? = null,
    val merchant: String? = null
) {
    constructor() : this("", "", "", "", 1, null, null)
}