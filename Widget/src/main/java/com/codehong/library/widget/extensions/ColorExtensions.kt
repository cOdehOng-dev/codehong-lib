package com.codehong.library.widget.extensions

import android.util.Log
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.core.graphics.toColorInt
import com.codehong.library.debugtool.log.TimberUtil
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.color.HongColor.Companion.toColor

fun Color?.toHexCode(): String {
    if (this == null) return "#00000000"
    return String.format("#%08X", this.toArgb())
}

fun String?.toColor(): Color {
    try {
        if (this.isNullOrEmpty()) {
            TimberUtil.e("컬러 hex 문자열이 비어있거나 null입니다")
            return HongColor.TRANSPARENT.hex.toColor()
        }

        val hex = this.removePrefix("#")

        return when (hex.length) {
            6 -> { // RGB (e.g. #RRGGBB)
                val r = hex.take(2).toInt(16)
                val g = hex.substring(2, 4).toInt(16)
                val b = hex.substring(4, 6).toInt(16)
                Color(r, g, b)
            }

            8 -> { // ARGB (e.g. #AARRGGBB)
                val a = hex.take(2).toInt(16)
                val r = hex.substring(2, 4).toInt(16)
                val g = hex.substring(4, 6).toInt(16)
                val b = hex.substring(6, 8).toInt(16)
                Color(r, g, b, a)
            }

            else -> HongColor.TRANSPARENT.hex.toColor()
        }
    } catch (e: Exception) {
        return HongColor.TRANSPARENT.hex.toColor()
    }
}

fun String?.toParseColor(): Int {
    if (this.isNullOrEmpty()) {
        TimberUtil.d("컬러 hex 문자열이 비어있거나 null입니다")
        return HongColor.TRANSPARENT.hex.parseColor()
    }

    return this.toColorInt()
}

fun String?.parseColor(): Int {
    return try {
        if (this.isNullOrEmpty()
            || this.equals("null", true)
            || this.equals("none", true)
            || this.equals("blank", true)
            || this.equals("empty", true)
        ) {
            "#00000000"
        } else {
            if (this.startsWith("#")) {
                when (this.length) {
                    7 -> this.replace("#", "#ff")
                    9 -> this
                    else -> "#00000000"
                }
            } else {
                when (this.length) {
                    6 -> "#ff$this"
                    8 -> "#$this"
                    else -> "#00000000"
                }
            }.toColorInt()
        }
    } catch (e: Exception) {
        Log.e("ERROR", e.toString())
        HongColor.TRANSPARENT.toColor()
    } as Int
}