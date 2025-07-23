package com.codehong.library.widget.extensions

fun Int?.toFigureString(): String {
    if (this == null) return ""
    return if (this > 0) {
        this.toString()
    } else {
        ""
    }
}

fun Int?.toFigureStringCoverZero(): String {
    if (this == null) return "0"
    return if (this > 0) {
        this.toString()
    } else {
        "0"
    }
}