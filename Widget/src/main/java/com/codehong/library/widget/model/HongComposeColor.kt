package com.codehong.library.widget.model

import androidx.annotation.ColorRes
import com.codehong.library.widget.ColorType
import com.codehong.library.widget.R

data class HongComposeColor(
    @ColorRes val colorRes: Int = R.color.honglib_color_transparent,
    val colorType: ColorType? = null
)
