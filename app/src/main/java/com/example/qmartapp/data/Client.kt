package com.example.qmartapp.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ClientAddress(
    val city: String,
    val streetHouse: String,
    val apartment: String?,
    val entrance: String?,
    val floor: String?
) : Parcelable

data class BankCard(
    val name: String,
    val number: Int,
    val expireDate: String,
    val cvc: Int
)