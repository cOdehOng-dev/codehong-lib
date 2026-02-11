package com.codehong.library.widget.extensions

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb

fun Color?.toHexCode(): String {
    if (this == null) return "#00000000"
    return String.format("#%08X", this.toArgb())
}