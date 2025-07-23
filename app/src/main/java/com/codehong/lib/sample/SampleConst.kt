package com.codehong.lib.sample

import org.threeten.bp.LocalDate

object SampleConst {
    const val WIDGET_TYPE = "widget_type"

    const val INITIAL_DATE = "initial_date"

    val koreanHolidayList = listOf(
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

    val testImageUrlList = listOf(
        "https://images.unsplash.com/photo-1738168273959-952fdc961991?q=80&w=1964&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
        "https://images.unsplash.com/photo-1735094495719-80c0e43efccc?q=80&w=2060&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
        "https://images.unsplash.com/photo-1739268515254-631fda640a1c?q=80&w=2052&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
    )
}