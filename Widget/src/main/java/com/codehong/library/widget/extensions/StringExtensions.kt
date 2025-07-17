package com.codehong.library.widget.extensions

import android.util.Log
import androidx.compose.ui.graphics.Color
import com.codehong.library.widget.rule.color.HongColor
fun String?.toColor(): Color {
    try {
        if (this.isNullOrEmpty()) {
            Log.e("TAG","컬러 hex 문자열이 비어있거나 null입니다")
            return HongColor.TRANSPARENT.hex.toColor()
        }

        val hex = this.removePrefix("#")

        return when (hex.length) {
            6 -> { // RGB (e.g. #RRGGBB)
                val r = hex.substring(0, 2).toInt(16)
                val g = hex.substring(2, 4).toInt(16)
                val b = hex.substring(4, 6).toInt(16)
                Color(r, g, b)
            }

            8 -> { // ARGB (e.g. #AARRGGBB)
                val a = hex.substring(0, 2).toInt(16)
                val r = hex.substring(2, 4).toInt(16)
                val g = hex.substring(4, 6).toInt(16)
                val b = hex.substring(6, 8).toInt(16)
                Color(r, g, b, a)
            }

            else -> {
                HongColor.TRANSPARENT.hex.toColor()
            }
        }
    } catch (e: Exception) {
        return HongColor.TRANSPARENT.hex.toColor()
    }
}

fun String?.toParseColor(): Int {
    if (this.isNullOrEmpty()) {
        Log.d("TAG", "컬러 hex 문자열이 비어있거나 null입니다")
        return android.graphics.Color.parseColor(HongColor.TRANSPARENT.hex)
    }

    return android.graphics.Color.parseColor(this)
}

fun String?.toFigureInt(): Int {
    return if (this.isNullOrEmpty()) {
        0
    } else {
        this.toInt()
    }
}

fun String?.toFigureFloat(): Float {
    return if (this.isNullOrEmpty()) {
        0f
    } else {
        this.toFloat()
    }
}

fun String?.toFigureLong(): Long {
    return if (this.isNullOrEmpty()) {
        0L
    } else {
        this.toLong()
    }
}

fun String?.parseComposeColor(): Color {
    return try {
        Color(
            android.graphics.Color.parseColor(
                if (this.isNullOrEmpty()
                    || this.equals("null", true)
                    || this.equals("none", true)
                    || this.equals("blank", true)
                    || this.equals("empty", true)
                ) {
                    "#00000000"
                } else if (this.startsWith("#")) {
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
                }
            )
        )
    } catch (e: Exception) {
        Log.e("ERROR", e.toString())
        Color.Transparent
    }
}

fun String?.parseColor(): Int {
    return try {
        android.graphics.Color.parseColor(
            if (this.isNullOrEmpty()
                || this.equals("null", true)
                || this.equals("none", true)
                || this.equals("blank", true)
                || this.equals("empty", true)
            ) {
                "#00000000"
            } else if (this.startsWith("#")) {
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
            }
        )
    } catch (e: Exception) {
        Log.e("ERROR", e.toString())
        android.graphics.Color.TRANSPARENT
    }
}