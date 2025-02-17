package com.codehong.library.widget.model.text

import androidx.annotation.ColorRes
import androidx.compose.ui.text.font.FontWeight
import com.codehong.library.widget.ColorType
import com.codehong.library.widget.R

data class HongComposeTextStyle(
    @ColorRes val textColorResId: Int = R.color.honglib_color_29292d,
    val fontWeight: FontWeight,
    val textSize: Int,
    val textColorType: ColorType? = null
)
