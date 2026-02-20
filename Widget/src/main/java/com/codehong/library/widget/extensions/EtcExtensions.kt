package com.codehong.library.widget.extensions

import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter

fun LocalDate?.toYyyyMmDd(): String {
    if (this == null) return "20260503"
    val formatter = DateTimeFormatter.ofPattern("yyyyMMdd")
    return this.format(formatter)
}