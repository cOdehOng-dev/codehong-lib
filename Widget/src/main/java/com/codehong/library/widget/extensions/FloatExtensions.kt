package com.codehong.library.widget.extensions

fun Float?.toFigureString(): String {
    if (this == null) return ""
    return if (this > 0f) {
        this.toString().removeSuffix(".0")
    } else {
        ""
    }
}