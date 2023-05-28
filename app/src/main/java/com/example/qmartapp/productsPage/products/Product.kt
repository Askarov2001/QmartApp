package com.example.qmartapp.productsPage.products

import com.example.qmartapp.R


data class Product(
    val id: String,
    val name: String,
    val category: String,
    val description: String,
    val cost: Int,
    val status: String?,
    val images: String?
) {
    constructor() : this("", "", "", "", 1, null, null)
}

enum class Categories(val nameRes: Int) {
    PRODUCTS(R.string.products),
    APPLIANCES(R.string.appliances),
    ALL_FOR_HOME(R.string.all_for_home),
    FURNITURE(R.string.furniture),
    ELECTRONIC(R.string.electronic),
    ACCESSORIES(R.string.accessories),
    SPORT(R.string.sport),
    MAKE_UP(R.string.make_up),
    ZOO(R.string.zoo_products)
}