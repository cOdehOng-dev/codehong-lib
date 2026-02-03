package com.codehong.library.util

import java.time.LocalDate
import java.time.YearMonth
import java.time.ZoneId
import java.time.format.DateTimeFormatter

object DateUtil {

    private val koreaZoneId = ZoneId.of("Asia/Seoul")

    fun getFormatter(pattern: String): DateTimeFormatter =
        DateTimeFormatter.ofPattern(pattern)
    
    fun getCurrentMonth(): Int =
        LocalDate.now(koreaZoneId).monthValue

    fun getPreviousMonthFirstDay(pattern: String): String {
        val today = LocalDate.now(koreaZoneId)
        val previousMonth = today.minusMonths(1)
        return previousMonth.withDayOfMonth(1).format(getFormatter(pattern))
    }

    fun getPreviousMonthLastDay(pattern: String): String {
        val today = LocalDate.now(koreaZoneId)
        val previousMonth = today.minusMonths(1)
        val yearMonth = YearMonth.from(previousMonth)
        val lastDay = yearMonth.atEndOfMonth()
        return lastDay.format(getFormatter(pattern))
    }

    fun getCurrentMonthLastDay(pattern: String): String {
        val today = LocalDate.now(koreaZoneId)
        val yearMonth = YearMonth.from(today)
        val lastDay = yearMonth.atEndOfMonth()
        return lastDay.format(getFormatter(pattern))
    }

    fun getToday(pattern: String): String =
        LocalDate.now(koreaZoneId).format(
            getFormatter(pattern)
        )

    fun getOneMonthLater(pattern: String): String {
        return LocalDate.now(koreaZoneId).plusMonths(1).format(
            getFormatter(pattern)
        )
    }
}