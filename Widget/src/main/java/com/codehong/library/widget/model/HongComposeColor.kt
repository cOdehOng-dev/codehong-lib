package com.codehong.library.widget.model

import androidx.annotation.ColorRes
import com.codehong.library.widget.R
import com.codehong.library.widget.rule.color.HongColor

data class HongComposeColor(
    @ColorRes val resId: Int = R.color.honglib_color_transparent,
    val type: HongColor? = null,
    val hexCode: String? = null,
    val alpha: Int? = null
)
