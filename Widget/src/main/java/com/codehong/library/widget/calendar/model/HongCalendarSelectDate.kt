package com.codehong.library.widget.calendar.model

import org.threeten.bp.LocalDate


data class HongCalendarSelectDate(
    val startDate: LocalDate? = null,
    val endDate: LocalDate? = null
)
