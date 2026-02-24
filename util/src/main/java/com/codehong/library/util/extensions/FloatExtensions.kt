package com.codehong.library.util.extensions

fun Float?.toFigureString(): String {
    if (this == null) return ""
    return if (this > 0f) {
        this.toString().removeSuffix(".0")
    } else {
        ""
    }
}