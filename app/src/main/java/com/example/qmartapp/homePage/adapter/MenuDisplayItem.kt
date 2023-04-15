package com.example.qmartapp.homePage.adapter

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.parcelize.Parcelize

@Parcelize
data class MenuDisplayItem(
    val title: String,
    @DrawableRes
    val image: Int,
    val id: String,
    val onClick: (MenuDisplayItem) -> Unit
) : Parcelable