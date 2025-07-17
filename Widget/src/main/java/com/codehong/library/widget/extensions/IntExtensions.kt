package com.codehong.library.widget.extensions

fun Int?.toFigureString(): String {
    if (this == null) return ""
    return if (this > 0) {
        this.toString()
    } else {
        ""
    }
}