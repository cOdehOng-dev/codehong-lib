package com.codehong.lib.sample.draganddrop

import androidx.annotation.DrawableRes

data class FoodItem(
    val id: Int,
    val storeName: String,
    val prdName: String,
    val price: Int,
    val discountPer: String,
    @DrawableRes val image: Int
)
