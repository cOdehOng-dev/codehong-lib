package com.codehong.library.widget.util

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.TimeZone
import org.threeten.bp.Duration
import org.threeten.bp.Instant
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneId

object HongDateUtil {
    const val MINUTES_IN_A_DAY = 1440L
    private val KOREA_ZONE_ID = ZoneId.of("Asia/Seoul")

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

    fun parseDateTimeString(
        dateTimeStr: String?,
        pattern: String
    ): Long {
        if (dateTimeStr.isNullOrEmpty()) return 0L
        val sdf = SimpleDateFormat(pattern, Locale.KOREA)
        sdf.timeZone = TimeZone.getTimeZone("Asia/Seoul") // 서울 시간 기준
        return sdf.parse(dateTimeStr)?.time ?: 0L
    }

    fun checkNoShowTime(
        lastShowMillis: Long,
        durationNoShowMinutes: Long
    ): Pair<Boolean, String> {
        try {
            val now = LocalDateTime.now(KOREA_ZONE_ID)

            val lastHiddenTime = LocalDateTime.ofInstant(
                Instant.ofEpochMilli(lastShowMillis),
                KOREA_ZONE_ID
            )

            val targetTime = if (durationNoShowMinutes >= MINUTES_IN_A_DAY) {
                // 하루 이상이면 다음 날 자정 기준
                val daysToAdd = durationNoShowMinutes / MINUTES_IN_A_DAY
                lastHiddenTime.toLocalDate().plusDays(daysToAdd).atStartOfDay()
            } else {
                // 일반적인 분 단위 제한
                lastHiddenTime.plusMinutes(durationNoShowMinutes)
            }

            val shouldShow = now.isAfter(targetTime)

            val remainTimeLog = if (!shouldShow) {
                val remaining = Duration.between(now, targetTime)
                val safeRemaining = if (remaining.isNegative) Duration.ZERO else remaining

                val days = safeRemaining.toDays()
                val hours = safeRemaining.toHours() % 24
                val minutes = safeRemaining.toMinutes() % 60
                val seconds = safeRemaining.seconds % 60

                "노출까지 남은 시간 = ${days}일 ${hours}시간 ${minutes}분 ${seconds}초"
            } else {
                "노출 필요"
            }

            return Pair(shouldShow, remainTimeLog)
        } catch (e: Exception) {
            e.printStackTrace()
            return Pair(false, "")
        }
    }
}
