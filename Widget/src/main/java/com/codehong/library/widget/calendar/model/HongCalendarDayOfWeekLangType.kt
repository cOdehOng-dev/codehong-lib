package com.codehong.library.widget.calendar.model

enum class HongCalendarDayOfWeekLangType(
    val dayOfWeekList: List<String>
) {
    KOR(listOf("일", "월", "화", "수", "목", "금", "토")),
    ENG(listOf("Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"))
    ;
}