package com.codehong.library.widget.util

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.TimeZone
import org.threeten.bp.LocalDate

object HongDateUtil {
    fun formatTodayDateTime(
        input: String?,
        inputPattern: String,
        todayPattern: String,
        otherDayPattern: String
    ): String {
        if (input.isNullOrEmpty()) return ""
        val inputFormat = SimpleDateFormat(inputPattern, Locale.getDefault())
        val outputTodayFormat = SimpleDateFormat(todayPattern, Locale.getDefault())
        val outputOtherDayFormat = SimpleDateFormat(otherDayPattern, Locale.getDefault())

        try {
            // 입력된 시간 파싱
            val date = inputFormat.parse(input) ?: return input

            // 현재 시간 가져오기
            val calendar = Calendar.getInstance()
            val todayStart = calendar.apply {
                set(Calendar.HOUR_OF_DAY, 0)
                set(Calendar.MINUTE, 0)
                set(Calendar.SECOND, 0)
                set(Calendar.MILLISECOND, 0)
            }.time

            val tomorrowStart = calendar.apply {
                add(Calendar.DAY_OF_YEAR, 1)
            }.time

            // 오늘인지 아닌지 확인
            return when {
                date >= todayStart && date < tomorrowStart -> outputTodayFormat.format(date)
                else -> outputOtherDayFormat.format(date)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return input
    }

    /**
     * 30분 단위로 버린 현재 시간 가져오기
     */
    fun getDateTimeStandard30Min(
        datePattern: String
    ): String {
        val calendar = Calendar.getInstance()

        // 현재 분 계산 (30분 단위로 버림)
        val minutes = calendar.get(Calendar.MINUTE)
        val roundedMinutes = if (minutes < 30) 0 else 30

        // 반올림된 시간 설정
        calendar.set(Calendar.MINUTE, roundedMinutes)
        calendar.set(Calendar.SECOND, 0) // 초는 항상 0으로 설정

        val dateFormat = SimpleDateFormat(datePattern, Locale.KOREAN)
        return dateFormat.format(calendar.time)
    }

    fun convertDateStringToLocalDate(
        dateTime: String,
        datePattern: String
    ): LocalDate {
        try {
            val inputFormat = SimpleDateFormat(datePattern, Locale.KOREA).apply {
                timeZone = TimeZone.getTimeZone("Asia/Seoul")
            }
            val dateParse = inputFormat.parse(dateTime)

            val cal = Calendar.getInstance()
            cal.time = dateParse

            val year = cal.get(Calendar.YEAR)
            val month = cal.get(Calendar.MONTH) + 1
            val date = cal.get(Calendar.DATE)
            return LocalDate.of(year, month, date)
        } catch (e: Exception) {
            return LocalDate.of(2025, 1, 1)
        }
    }
}
