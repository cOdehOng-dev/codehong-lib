package com.codehong.library.widget

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

object DateUtil {
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
}
