package com.codehong.library.widget.calendar.model

import com.codehong.library.widget.util.HongDateUtil
import org.threeten.bp.LocalDate

data class InitialCalendarInfo(
    val startDate: String,
    val endDate: String,
    val datePattern: String = "yyyyMMdd"
) {
    fun getStartLocalDate(): LocalDate = HongDateUtil.convertDateStringToLocalDate(
        dateTime = startDate,
        datePattern = datePattern
    )

    fun getEndLocalDate(): LocalDate = HongDateUtil.convertDateStringToLocalDate(
        dateTime = endDate,
        datePattern = datePattern
    )
}

