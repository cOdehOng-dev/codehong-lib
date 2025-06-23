package com.codehong.library.widget.util

import org.threeten.bp.Duration
import org.threeten.bp.Instant
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneId

object DateUtil {

    const val MINUTES_IN_A_DAY = 1440L
    private val KOREA_ZONE_ID = ZoneId.of("Asia/Seoul")

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