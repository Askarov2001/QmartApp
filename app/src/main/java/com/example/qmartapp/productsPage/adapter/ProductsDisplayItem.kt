package com.example.qmartapp.productsPage.adapter

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.parcelize.Parcelize
import java.util.UUID

@Parcelize
data class ProductsDisplayItem(
    val rate: Double?,
    val discount: Int?,
    val image: String?,
    val title: String,
    val subTitle: String,
    val price: Int,
    val merchant: String?,
    val onClick: (ProductsDisplayItem) -> Unit,
    val id: String = UUID.randomUUID().toString(),
    val addAction: (ProductsDisplayItem) -> Unit,
    val sellerId: String? = null
) : Parcelable