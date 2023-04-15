package com.example.qmartapp.productsPage.adapter

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.parcelize.Parcelize
import java.util.UUID
@Parcelize
data class ProductsDisplayItem(
    val rate: Double?,
    val discount: Int?,
    @DrawableRes
    val image: Int,
    val title: String,
    val subTitle: String,
    val price: Int,
    val merchant: String?,
    val onClick: (ProductsDisplayItem) -> Unit,
    val id: String = UUID.randomUUID().toString(),
    val addAction: (ProductsDisplayItem) -> Unit
):Parcelable