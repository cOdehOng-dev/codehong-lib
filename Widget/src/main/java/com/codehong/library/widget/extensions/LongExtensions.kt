package com.codehong.library.widget.extensions

fun Long?.toFigureString(): String {
    if (this == null) return ""

    return if (this > 0L) {
        this.toString()
    } else {
        ""
    }
}