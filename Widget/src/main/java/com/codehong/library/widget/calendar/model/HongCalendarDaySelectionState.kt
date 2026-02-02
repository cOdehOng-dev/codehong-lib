package com.codehong.library.widget.calendar.model

sealed class HongCalendarDaySelectionState {
    data object Past : HongCalendarDaySelectionState()
    data object Start : HongCalendarDaySelectionState()
    data object End : HongCalendarDaySelectionState()
    data object InRange : HongCalendarDaySelectionState()
    data object Default : HongCalendarDaySelectionState()
}