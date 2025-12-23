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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.codehong.library.widget.HongDivider
import com.codehong.library.widget.calendar.model.HongCalendarDayOfWeekLangType
import com.codehong.library.widget.calendar.model.HongCalendarSelectBackgroundColorHex
import com.codehong.library.widget.calendar.model.HongCalendarSelectedType
import com.codehong.library.widget.calendar.model.InitialSelectedInfo
import com.codehong.library.widget.extensions.toColor
import com.codehong.library.widget.rule.HongLayoutParam
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.typo.HongFont
import com.codehong.library.widget.text.label.HongTextBuilder
import com.codehong.library.widget.text.label.HongTextCompose
import com.codehong.library.widget.text.label.HongTextOption
import com.codehong.library.widget.util.HongDateUtil
import com.codehong.library.widget.util.HongWidgetContainer
import org.threeten.bp.DayOfWeek
import org.threeten.bp.LocalDate
import org.threeten.bp.YearMonth
import org.threeten.bp.format.DateTimeFormatter


@Composable
fun HongCalendarCompose(
    option: HongCalendarOption
) {
    val today = LocalDate.now()
    val maxYearLater = today.plusYears(option.maxYears.toLong())
    val months = rememberSaveable {
        (0..12).map { today.plusMonths(it.toLong()).withDayOfMonth(1) }
            .filter { it <= maxYearLater }
    }

    var startDate by rememberSaveable { mutableStateOf(option.initialSelectedInfo?.getStartLocalDate()) }
    var endDate by rememberSaveable { mutableStateOf(option.initialSelectedInfo?.getEndLocalDate()) }

    HongWidgetContainer(option) {
        Column {
            // 요일 헤더
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(46.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                option.dayOfWeekLangType.dayOfWeekList.forEach { day ->
                    Box(
                        modifier = Modifier
                            .weight(1f),
                        contentAlignment = Alignment.Center
                    ) {
                        HongTextCompose(
                            option = HongTextBuilder()
                                .copy(option.dayOfWeekTextOption)
                                .text(day)
                                .applyOption(),
                        )
                    }
                }
            }

            // 요일 헤더 라인
            if (option.dayOfWeekBottomLineColorHex != HongColor.TRANSPARENT.hex) {
                HongDivider(
                    colorHex = option.dayOfWeekBottomLineColorHex,
                    height = 1
                )
            }

            // 달력 본문
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                itemsIndexed(months) { i, month ->
                    Column(
                        modifier = Modifier
                            .padding(
                                top = if (i == 0) {
                                    option.bottomSpacingDayOfWeek.dp
                                } else {
                                    0.dp
                                },
                                bottom = option.bottomSpacingMonth.dp
                            )
                    ) {
                        // 년, 월 표기
                        // ex) 2025.07
                        HongTextCompose(
                            option = HongTextBuilder()
                                .copy(option.yearMonthTextOption)
                                .width(HongLayoutParam.MATCH_PARENT.value)
                                .padding(
                                    HongSpacingInfo(
                                        left = 11f,
                                        right = 11f,
                                        bottom = 8f
                                    )
                                )
                                .text(
                                    month.format(
                                        DateTimeFormatter.ofPattern(
                                            option.yearMonthPattern
                                        )
                                    )
                                )
                                .applyOption()
                        )

                        val daysInMonth = YearMonth.from(month).lengthOfMonth()
                        val firstDayOfWeek = month.withDayOfMonth(1).dayOfWeek.value % 7 // 요일 시작 위치
                        val days = List(firstDayOfWeek) { null } + (1..daysInMonth).map {
                            month.withDayOfMonth(it)
                        }
                        val totalCells = ((days.size + 6) / 7) * 7 // 7의 배수로 행 맞추기
                        val paddedDays = days + List(totalCells - days.size) { null }

                        paddedDays.chunked(7).forEach { week ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(
                                        bottom = option.bottomSpacingWeek.dp,
                                    )
                            ) {
                                week.forEach { day ->
                                    Box(
                                        modifier = Modifier.weight(1f),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        day?.let {
                                            val isPast = it.isBefore(today)
                                            val isToday = it == today
                                            val isStart = it == startDate
                                            val isEnd = it == endDate
                                            val isSelectedRange =
                                                startDate != null && endDate != null && it in startDate!!..endDate!!
                                            val holidayList = option.holidayList
                                            val isHoliday = if (holidayList != null) {
                                                it in holidayList || it.dayOfWeek == DayOfWeek.SUNDAY
                                            } else {
                                                it.dayOfWeek == DayOfWeek.SUNDAY
                                            }

                                            if (isStart) {
                                                if (endDate != null && startDate != endDate) {
                                                    SelectStartOrEndBackground(
                                                        selectedType = HongCalendarSelectedType.START,
                                                        selectedBackgroundColorHex = option.selectBackgroundColorHex.rangeDaysColorHex
                                                    )
                                                }
                                            } else if (isEnd) {
                                                SelectStartOrEndBackground(
                                                    selectedType = HongCalendarSelectedType.END,
                                                    selectedBackgroundColorHex = option.selectBackgroundColorHex.rangeDaysColorHex
                                                )
                                            }

                                            val dateModifier = when {
                                                isStart -> {
                                                    Modifier
                                                        .clip(CircleShape)
                                                        .background(
                                                            color = option.selectBackgroundColorHex.startDayColorHex.toColor()
                                                        )
                                                }

                                                isEnd -> {
                                                    Modifier
                                                        .clip(CircleShape)
                                                        .background(
                                                            color = option.selectBackgroundColorHex.endDayColorHex.toColor()
                                                        )
                                                }

                                                isSelectedRange -> {
                                                    Modifier
                                                        .background(
                                                            color = option.selectBackgroundColorHex.rangeDaysColorHex.toColor()
                                                        )
                                                }

                                                else -> Modifier
                                            }

                                            Box(
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .height(48.dp)
                                                    .clickable(enabled = !isPast) {
                                                        if (startDate == null || endDate != null) {
                                                            startDate = it
                                                            endDate = null
                                                        } else if (it < startDate) {
                                                            startDate = it
                                                        } else {
                                                            endDate = it
                                                        }

                                                        option.onSelected?.invoke(
                                                            startDate,
                                                            endDate
                                                        )
                                                    }
                                                    .then(dateModifier),
                                                contentAlignment = Alignment.Center
                                            ) {
                                                val dayOfMonthStr = it.dayOfMonth.toString()
                                                when {
                                                    isPast -> {
                                                        DayOfMonthView(
                                                            dayOfMonth = dayOfMonthStr,
                                                            dayTextOption = if (isHoliday) {
                                                                HongTextBuilder()
                                                                    .copy(option.holidaysTextOption)
                                                                    .color("#75ff322e")
                                                                    .applyOption()
                                                            } else {
                                                                option.pastDaysTextOption
                                                            }
                                                        )
                                                    }

                                                    isStart -> {
                                                        DayOfMonthView(
                                                            dayOfMonth = dayOfMonthStr,
                                                            dayTextOption = option.startDayTextOption,
                                                            todayTextOption = option.selectTodayTextOption,
                                                            isToday = isToday
                                                        )
                                                    }

                                                    isEnd -> {
                                                        DayOfMonthView(
                                                            dayOfMonth = dayOfMonthStr,
                                                            dayTextOption = option.endDayTextOption
                                                        )
                                                    }

                                                    isSelectedRange -> {
                                                        DayOfMonthView(
                                                            dayOfMonth = dayOfMonthStr,
                                                            dayTextOption = option.rangeDaysTextOption
                                                        )
                                                    }

                                                    else -> {
                                                        DayOfMonthView(
                                                            dayOfMonth = dayOfMonthStr,
                                                            dayTextOption = if (isHoliday) {
                                                                option.holidaysTextOption
                                                            } else {
                                                                option.defaultDayTextOption
                                                            },
                                                            todayTextOption = option.unselectTodayTextOption,
                                                            isToday = isToday
                                                        )
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun SelectStartOrEndBackground(
    selectedType: HongCalendarSelectedType,
    selectedBackgroundColorHex: String
) {
    when (selectedType) {
        HongCalendarSelectedType.START -> {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
            ) {
                Spacer(
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp)
                        .background(HongColor.TRANSPARENT.hex.toColor())
                )
                Spacer(
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp)
                        .background(selectedBackgroundColorHex.toColor())
                )
            }
        }

        HongCalendarSelectedType.END -> {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
            ) {
                Spacer(
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp)
                        .background(selectedBackgroundColorHex.toColor())
                )
                Spacer(
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp)
                        .background(HongColor.TRANSPARENT.hex.toColor())
                )
            }
        }

        else -> {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .background(selectedBackgroundColorHex.toColor())
            )
        }
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
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
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

@Preview(showBackground = true)
@Composable
fun PreviewHongCalendarCompose() {
    val option = HongCalendarBuilder()
        .backgroundColor(HongColor.WHITE_100.hex)
        .initialSelectedInfo(
            InitialSelectedInfo(
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
        .selectBackgroundColorHex(
            HongCalendarSelectBackgroundColorHex(
                startDayColorHex = HongColor.MAIN_ORANGE_100.hex,
                endDayColorHex = HongColor.MAIN_ORANGE_100.hex,
                rangeDaysColorHex = HongColor.MAIN_ORANGE_15.hex
            )
        )
        .startDayTextOption(
            HongTextBuilder()
                .size(17)
                .color(HongColor.WHITE_100.hex)
                .fontType(HongFont.PRETENDARD_700)
                .applyOption()
        )
        .endDayTextOption(
            HongTextBuilder()
                .size(17)
                .color(HongColor.WHITE_100.hex)
                .fontType(HongFont.PRETENDARD_700)
                .applyOption()
        )
        .rangeDaysTextOption(
            HongTextBuilder()
                .size(17)
                .color(HongColor.MAIN_ORANGE_100.hex)
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
        .onSelected { _, _ -> }
        .applyOption()

    HongCalendarCompose(option)
}
