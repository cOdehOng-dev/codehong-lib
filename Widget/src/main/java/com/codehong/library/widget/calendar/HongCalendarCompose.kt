package com.codehong.library.widget.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.codehong.library.widget.HongDivider
import com.codehong.library.widget.calendar.model.HongCalendarDayOfWeekLangType
import com.codehong.library.widget.calendar.model.HongCalendarDaySelectionState
import com.codehong.library.widget.calendar.model.HongCalendarInitialSelectedInfo
import com.codehong.library.widget.calendar.model.HongCalendarSelectDate
import com.codehong.library.widget.calendar.model.HongCalendarSelectedType
import com.codehong.library.widget.extensions.toColor
import com.codehong.library.widget.rule.HongLayoutParam
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.typo.HongFont
import com.codehong.library.widget.text.def.HongTextBuilder
import com.codehong.library.widget.text.def.HongTextCompose
import com.codehong.library.widget.text.def.HongTextOption
import com.codehong.library.widget.util.HongDateUtil
import com.codehong.library.widget.util.HongWidgetContainer
import org.threeten.bp.DayOfWeek
import org.threeten.bp.LocalDate
import org.threeten.bp.YearMonth
import org.threeten.bp.format.DateTimeFormatter

private val LocalOption = compositionLocalOf { HongCalendarOption() }
private val LocalSelectDate = compositionLocalOf { HongCalendarSelectDate() }

@Composable
fun HongCalendarCompose(
    option: HongCalendarOption
) {
    val today = remember { LocalDate.now() }
    val months = remember {
        (0..option.maxYears * 12).map { today.plusMonths(it.toLong()).withDayOfMonth(1) }
    }

    var startDate by rememberSaveable { mutableStateOf(option.initialSelectedInfo?.getStartLocalDate()) }
    var endDate by rememberSaveable { mutableStateOf(option.initialSelectedInfo?.getEndLocalDate()) }

    CompositionLocalProvider(
        LocalOption provides option,
        LocalSelectDate provides HongCalendarSelectDate(startDate, endDate)
    ) {
        HongWidgetContainer(option) {
            Column {
                DayOfWeekHeaderContent()

                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    itemsIndexed(months) { i, month ->
                        MonthContent(
                            month = month,
                            today = today,
                            isFirstMonth = i == 0,
                            onDateSelected = { newStart, newEnd ->
                                startDate = newStart
                                endDate = newEnd
                                option.onSelected?.invoke(startDate, endDate)
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun DayOfWeekHeaderContent() {
    val option = LocalOption.current

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(46.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        option.dayOfWeekLangType.dayOfWeekList.forEach { day ->
            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.Center
            ) {
                HongTextCompose(
                    option = HongTextBuilder()
                        .copy(option.dayOfWeekTextOption)
                        .text(day)
                        .applyOption()
                )
            }
        }
    }
    HongDivider(
        colorHex = option.dayOfWeekBottomLineColorHex,
        height = 1
    )
}

@Composable
private fun MonthContent(
    month: LocalDate,
    today: LocalDate,
    isFirstMonth: Boolean,
    onDateSelected: (LocalDate?, LocalDate?) -> Unit
) {
    val option = LocalOption.current

    Column(
        modifier = Modifier.padding(
            top = if (isFirstMonth) option.bottomSpacingDayOfWeek.dp else 0.dp,
            bottom = option.bottomSpacingMonth.dp
        )
    ) {
        YearMonthContent(month.format(DateTimeFormatter.ofPattern(option.yearMonthPattern)))
        DateContent(month = month, today = today, onDateSelected = onDateSelected)
    }
}

@Composable
private fun YearMonthContent(month: String) {
    val option = LocalOption.current
    HongTextCompose(
        option = HongTextBuilder()
            .copy(option.yearMonthTextOption)
            .width(HongLayoutParam.MATCH_PARENT.value)
            .padding(HongSpacingInfo(left = 11f, right = 11f, bottom = 8f))
            .text(month)
            .applyOption()
    )
}

@Composable
private fun DateContent(
    month: LocalDate,
    today: LocalDate,
    onDateSelected: (LocalDate?, LocalDate?) -> Unit
) {
    val paddedDays = generateMonthDays(month)

    paddedDays.chunked(7).forEach { week ->
        WeekRow(week = week, today = today, onDateSelected = onDateSelected)
    }
}

private fun generateMonthDays(month: LocalDate): List<LocalDate?> {
    val daysInMonth = YearMonth.from(month).lengthOfMonth()
    val firstDayOfWeek = month.withDayOfMonth(1).dayOfWeek.value % 7
    val days = List(firstDayOfWeek) { null } + (1..daysInMonth).map { month.withDayOfMonth(it) }
    val totalCells = ((days.size + 6) / 7) * 7
    return days + List(totalCells - days.size) { null }
}

@Composable
private fun WeekRow(
    week: List<LocalDate?>,
    today: LocalDate,
    onDateSelected: (LocalDate?, LocalDate?) -> Unit
) {
    val option = LocalOption.current

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = option.bottomSpacingWeek.dp)
    ) {
        week.forEach { day ->
            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.Center
            ) {
                day?.let {
                    DayCell(day = it, today = today, onDateSelected = onDateSelected)
                }
            }
        }
    }
}

@Composable
private fun DayCell(
    day: LocalDate,
    today: LocalDate,
    onDateSelected: (LocalDate?, LocalDate?) -> Unit
) {
    val option = LocalOption.current
    val selectDate = LocalSelectDate.current
    val startDate = selectDate.startDate
    val endDate = selectDate.endDate

    val isPast = day.isBefore(today)
    val isToday = day == today
    val isStart = day == startDate
    val isEnd = day == endDate
    val isHoliday = checkIsHoliday(day, option.holidayList)

    val selectionState = determineSelectionState(
        day = day,
        isPast = isPast,
        isStart = isStart,
        isEnd = isEnd,
        startDate = startDate,
        endDate = endDate
    )

    if (isStart && endDate != null && startDate != endDate) {
        SelectStartOrEndBackground(
            selectedType = HongCalendarSelectedType.START,
            selectedBackgroundColorHex = option.rangeDaysTextOption.backgroundColorHex
        )
    } else if (isEnd) {
        SelectStartOrEndBackground(
            selectedType = HongCalendarSelectedType.END,
            selectedBackgroundColorHex = option.rangeDaysTextOption.backgroundColorHex
        )
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .clickable(enabled = !isPast) {
                handleDateClick(day, startDate, endDate, onDateSelected)
            }
            .then(getDateModifier(selectionState, option)),
        contentAlignment = Alignment.Center
    ) {
        DayContent(
            day = day,
            selectionState = selectionState,
            isToday = isToday,
            isHoliday = isHoliday,
            option = option
        )
    }
}

private fun checkIsHoliday(day: LocalDate, holidayList: List<LocalDate>?): Boolean {
    return day.dayOfWeek == DayOfWeek.SUNDAY || holidayList?.contains(day) == true
}

private fun determineSelectionState(
    day: LocalDate,
    isPast: Boolean,
    isStart: Boolean,
    isEnd: Boolean,
    startDate: LocalDate?,
    endDate: LocalDate?
): HongCalendarDaySelectionState {
    return when {
        isPast -> HongCalendarDaySelectionState.Past
        isStart -> HongCalendarDaySelectionState.Start
        isEnd -> HongCalendarDaySelectionState.End
        startDate != null && endDate != null && day in startDate..endDate -> HongCalendarDaySelectionState.InRange
        else -> HongCalendarDaySelectionState.Default
    }
}

private fun getDateModifier(
    selectionState: HongCalendarDaySelectionState,
    option: HongCalendarOption
): Modifier {
    return when (selectionState) {
        is HongCalendarDaySelectionState.Start -> Modifier
            .clip(CircleShape)
            .background(option.startDayTextOption.backgroundColorHex.toColor())
        is HongCalendarDaySelectionState.End -> Modifier
            .clip(CircleShape)
            .background(option.endDayTextOption.backgroundColorHex.toColor())
        is HongCalendarDaySelectionState.InRange -> Modifier
            .background(option.rangeDaysTextOption.backgroundColorHex.toColor())
        else -> Modifier
    }
}

private fun handleDateClick(
    day: LocalDate,
    startDate: LocalDate?,
    endDate: LocalDate?,
    onDateSelected: (LocalDate?, LocalDate?) -> Unit
) {
    when {
        startDate == null || endDate != null -> onDateSelected(day, null)
        day < startDate -> onDateSelected(day, endDate)
        else -> onDateSelected(startDate, day)
    }
}

@Composable
private fun DayContent(
    day: LocalDate,
    selectionState: HongCalendarDaySelectionState,
    isToday: Boolean,
    isHoliday: Boolean,
    option: HongCalendarOption
) {
    val dayOfMonthStr = day.dayOfMonth.toString()

    when (selectionState) {
        is HongCalendarDaySelectionState.Past -> {
            DayOfMonthView(
                dayOfMonth = dayOfMonthStr,
                dayTextOption = if (isHoliday) {
                    HongTextBuilder()
                        .copy(option.holidaysTextOption)
                        .color(option.holidaysTextOption.colorHex.toPastColorHex())
                        .applyOption()
                } else {
                    option.pastDaysTextOption
                }
            )
        }
        is HongCalendarDaySelectionState.Start -> {
            DayOfMonthView(
                dayOfMonth = dayOfMonthStr,
                dayTextOption = option.startDayTextOption,
                todayTextOption = option.selectTodayTextOption,
                isToday = isToday
            )
        }
        is HongCalendarDaySelectionState.End -> {
            DayOfMonthView(
                dayOfMonth = dayOfMonthStr,
                dayTextOption = option.endDayTextOption
            )
        }
        is HongCalendarDaySelectionState.InRange -> {
            DayOfMonthView(
                dayOfMonth = dayOfMonthStr,
                dayTextOption = option.rangeDaysTextOption
            )
        }
        is HongCalendarDaySelectionState.Default -> {
            DayOfMonthView(
                dayOfMonth = dayOfMonthStr,
                dayTextOption = if (isHoliday) option.holidaysTextOption else option.defaultDayTextOption,
                todayTextOption = option.unselectTodayTextOption,
                isToday = isToday
            )
        }
    }
}

/** 지난 날짜 공휴일 색상: 공휴일 색상에 알파 0x75(~46%)를 적용해 흐리게 표시 */
private fun String?.toPastColorHex(): String {
    val hex = this?.trimStart('#') ?: return "#75ff322e"
    return when (hex.length) {
        6 -> "#75$hex"
        8 -> "#75${hex.drop(2)}"
        else -> "#75ff322e"
    }
}

@Composable
private fun SelectStartOrEndBackground(
    selectedType: HongCalendarSelectedType,
    selectedBackgroundColorHex: String
) {
    val (leftColorHex, rightColorHex) = when (selectedType) {
        HongCalendarSelectedType.START -> HongColor.TRANSPARENT.hex to selectedBackgroundColorHex
        HongCalendarSelectedType.END -> selectedBackgroundColorHex to HongColor.TRANSPARENT.hex
        else -> return
    }

    Row(modifier = Modifier.fillMaxWidth().height(48.dp)) {
        Spacer(
            modifier = Modifier
                .weight(1f)
                .height(48.dp)
                .background(leftColorHex.toColor())
        )
        Spacer(
            modifier = Modifier
                .weight(1f)
                .height(48.dp)
                .background(rightColorHex.toColor())
        )
    }
}

@Composable
private fun DayOfMonthView(
    dayOfMonth: String,
    dayTextOption: HongTextOption,
    todayTextOption: HongTextOption = HongTextBuilder()
        .fontType(HongFont.PRETENDARD_700)
        .applyOption(),
    isToday: Boolean = false
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        HongTextCompose(
            option = HongTextBuilder()
                .copy(dayTextOption)
                .text(dayOfMonth)
                .applyOption()
        )
        if (isToday) {
            HongTextCompose(
                option = HongTextBuilder()
                    .copy(todayTextOption)
                    .text("Today")
                    .applyOption()
            )
        }
    }
}

// region Preview

@Preview(showBackground = true, name = "Default Calendar")
@Composable
private fun PreviewHongCalendarDefault() {
    HongCalendarCompose(
        option = HongCalendarBuilder()
            .backgroundColor(HongColor.WHITE_100.hex)
            .applyOption()
    )
}

@Preview(showBackground = true, name = "Calendar with Selected Range")
@Composable
private fun PreviewHongCalendarWithSelection() {
    HongCalendarCompose(
        option = HongCalendarBuilder()
            .backgroundColor(HongColor.WHITE_100.hex)
            .initialSelectedInfo(
                HongCalendarInitialSelectedInfo(
                    startDate = "20250710",
                    endDate = "20250720"
                )
            )
            .applyOption()
    )
}

@Preview(showBackground = true, name = "Calendar with Korean Holidays")
@Composable
private fun PreviewHongCalendarWithHolidays() {
    HongCalendarCompose(
        option = HongCalendarBuilder()
            .backgroundColor(HongColor.WHITE_100.hex)
            .dayOfWeekLangType(HongCalendarDayOfWeekLangType.KOR)
            .holidayList(HongDateUtil.KOREAN_HOLIDAY_LIST)
            .applyOption()
    )
}

@Preview(showBackground = true, name = "Calendar Full Customized")
@Composable
private fun PreviewHongCalendarFullCustomized() {
    HongCalendarCompose(
        option = HongCalendarBuilder()
            .backgroundColor(HongColor.WHITE_100.hex)
            .initialSelectedInfo(
                HongCalendarInitialSelectedInfo(
                    startDate = "20250710",
                    endDate = "20250720"
                )
            )
            .dayOfWeekTextOption(
                HongTextBuilder()
                    .size(13)
                    .color("#666666")
                    .fontType(HongFont.PRETENDARD_400)
                    .applyOption()
            )
            .dayOfWeekLangType(HongCalendarDayOfWeekLangType.KOR)
            .yearMonthTextOption(
                HongTextBuilder()
                    .size(19)
                    .color(HongColor.BLACK_100.hex)
                    .fontType(HongFont.PRETENDARD_700)
                    .applyOption()
            )
            .yearMonthPattern("yyyy.MM")
            .startDayTextOption(
                HongTextBuilder()
                    .size(17)
                    .color(HongColor.WHITE_100.hex)
                    .backgroundColor(HongCalendarOption.DEFAULT_SELECT_START_DAY_BACKGROUND_COLOR)
                    .fontType(HongFont.PRETENDARD_700)
                    .applyOption()
            )
            .endDayTextOption(
                HongTextBuilder()
                    .size(17)
                    .color(HongColor.WHITE_100.hex)
                    .backgroundColor(HongCalendarOption.DEFAULT_SELECT_END_DAY_BACKGROUND_COLOR)
                    .fontType(HongFont.PRETENDARD_700)
                    .applyOption()
            )
            .rangeDaysTextOption(
                HongTextBuilder()
                    .size(17)
                    .color(HongColor.MAIN_ORANGE_100.hex)
                    .backgroundColor(HongCalendarOption.DEFAULT_SELECT_RANGE_DAYS_BACKGROUND_COLOR)
                    .fontType(HongFont.PRETENDARD_700)
                    .applyOption()
            )
            .holidaysTextOption(
                HongTextBuilder()
                    .size(17)
                    .color("#ff322e")
                    .fontType(HongFont.PRETENDARD_700)
                    .applyOption()
            )
            .pastDaysTextOption(
                HongTextBuilder()
                    .size(17)
                    .color("#cccccc")
                    .fontType(HongFont.PRETENDARD_700)
                    .applyOption()
            )
            .selectTodayTextOption(
                HongTextBuilder()
                    .size(8)
                    .color(HongColor.WHITE_100.hex)
                    .fontType(HongFont.PRETENDARD_700)
                    .applyOption()
            )
            .unselectTodayTextOption(
                HongTextBuilder()
                    .size(8)
                    .color("#545457")
                    .fontType(HongFont.PRETENDARD_700)
                    .applyOption()
            )
            .defaultDayTextOption(
                HongTextBuilder()
                    .size(17)
                    .color(HongColor.BLACK_100.hex)
                    .fontType(HongFont.PRETENDARD_700)
                    .applyOption()
            )
            .spacingHorizontal(16)
            .bottomSpacingWeek(20)
            .holidayList(HongDateUtil.KOREAN_HOLIDAY_LIST)
            .dayOfWeekBottomLineColorHex("#eeeeee")
            .applyOption()
    )
}

// endregion
