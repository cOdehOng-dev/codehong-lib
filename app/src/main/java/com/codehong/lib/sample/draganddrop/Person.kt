package com.codehong.lib.sample.draganddrop

import androidx.annotation.DrawableRes

data class Person(
    val id: Int,
    val name: String,
    @DrawableRes val profile: Int
)
