package com.codehong.lib.sample.draganddrop.model

import androidx.annotation.DrawableRes

data class BreadItem(
    val id: Int,
    val storeName: String,
    val prdName: String,
    val price: Int,
    val discountPer: String,
    @DrawableRes val image: Int
)
