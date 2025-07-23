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

    val KOREAN_HOLIDAY_LIST = listOf(
        // 2025년 공휴일
        LocalDate.of(2025, 1, 1), // 신정
        LocalDate.of(2025, 1, 27), // 임시공휴일
        LocalDate.of(2025, 1, 28), // 설날 연휴
        LocalDate.of(2025, 1, 29), // 설날
        LocalDate.of(2025, 1, 30), // 설날 연휴
        LocalDate.of(2025, 3, 1), // 삼일절
        LocalDate.of(2025, 3, 3), // 삼일절 대체공휴일
        LocalDate.of(2025, 5, 5), // 어린이날
        LocalDate.of(2025, 5, 6), // 석가탄신일
        LocalDate.of(2025, 6, 6), // 현충일
        LocalDate.of(2025, 8, 15), // 광복절
        LocalDate.of(2025, 10, 3), // 개천절
        LocalDate.of(2025, 10, 5), // 추석 연휴
        LocalDate.of(2025, 10, 6), // 추석
        LocalDate.of(2025, 10, 7), // 추석 연휴
        LocalDate.of(2025, 10, 8), // 추석 대체공휴일
        LocalDate.of(2025, 10, 9), // 한글날
        LocalDate.of(2025, 12, 25), // 성탄절

        // 2026년 공휴일
        LocalDate.of(2026, 1, 1), // 신정
        LocalDate.of(2026, 2, 16), // 설날 연휴
        LocalDate.of(2026, 2, 17), // 설날
        LocalDate.of(2026, 2, 18), // 설날 연휴
        LocalDate.of(2026, 3, 1), // 삼일절
        LocalDate.of(2026, 3, 2), // 삼일절 대체공휴일
        LocalDate.of(2026, 5, 5), // 어린이날
        LocalDate.of(2026, 5, 24), // 석가탄신일
        LocalDate.of(2026, 5, 25), // 석가탄신일 대체공휴일
        LocalDate.of(2026, 6, 6), // 현충일
        LocalDate.of(2026, 8, 15), // 광복절
        LocalDate.of(2026, 8, 17), // 광복절 대체공휴일
        LocalDate.of(2026, 9, 24), // 추석 연휴
        LocalDate.of(2026, 9, 25), // 추석
        LocalDate.of(2026, 9, 26), // 추석 연휴
        LocalDate.of(2026, 10, 3), // 개천절
        LocalDate.of(2026, 10, 5), // 개천절 대체공휴일
        LocalDate.of(2026, 10, 9), // 한글날
        LocalDate.of(2026, 12, 25) // 성탄절
    )

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
