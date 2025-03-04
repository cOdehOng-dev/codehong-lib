package com.codehong.library.widget.calendar

import androidx.annotation.ColorRes
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
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.codehong.library.widget.ColorType
import com.codehong.library.widget.HongDivider
import com.codehong.library.widget.MarginTopOrBottom
import com.codehong.library.widget.R
import com.codehong.library.widget.calendar.model.InitialCalendarInfo
import com.codehong.library.widget.model.HongComposeColor
import com.codehong.library.widget.model.text.HongComposeTextStyle
import com.codehong.library.widget.text.HongText
import com.codehong.library.widget.util.getColor
import com.codehong.library.widget.util.getComposeColor
import org.threeten.bp.DayOfWeek
import org.threeten.bp.LocalDate
import org.threeten.bp.YearMonth

@Composable
fun HongComposeCalendar(
    modifier: Modifier = Modifier,
    daysTextStyle: HongComposeTextStyle = HongComposeTextStyle(
        size = 13,
        color = HongComposeColor(
            resId = R.color.honglib_color_666666
        ),
        fontWeight = FontWeight.W400
    ),

    lineColor: HongComposeColor? = null,

    yearMonthTextStyle: HongComposeTextStyle = HongComposeTextStyle(
        size = 19,
        color = HongComposeColor(
            resId = R.color.honglib_color_000000
        ),
        fontWeight = FontWeight.W700
    ),

    yearMonthPattern: String = "yyyy.MM",

    startDateBackgroundColor: HongComposeColor = HongComposeColor(
        resId = R.color.honglib_color_1769ff
    ),
    endDateBackgroundColor: HongComposeColor = HongComposeColor(
        resId = R.color.honglib_color_1769ff
    ),
    selectBackgroundColor: HongComposeColor = HongComposeColor(
        resId = R.color.honglib_color_e9f0fe
    ),

    startDateTextStyle: HongComposeTextStyle = HongComposeTextStyle(
        size = 17,
        color = HongComposeColor(
            resId = R.color.honglib_color_ffffff
        ),
        fontWeight = FontWeight.W700
    ),
    endDateTextStyle: HongComposeTextStyle = HongComposeTextStyle(
        size = 17,
        color = HongComposeColor(
            resId = R.color.honglib_color_ffffff
        ),
        fontWeight = FontWeight.W700
    ),
    rangeDateTextStyle: HongComposeTextStyle = HongComposeTextStyle(
        size = 17,
        color = HongComposeColor(
            resId = R.color.honglib_color_1769ff
        ),
        fontWeight = FontWeight.W700
    ),
    holidayTextStyle: HongComposeTextStyle = HongComposeTextStyle(
        size = 17,
        color = HongComposeColor(
            resId = R.color.honglib_color_ff322e
        ),
        fontWeight = FontWeight.W700
    ),
    pastDateTextStyle: HongComposeTextStyle = HongComposeTextStyle(
        size = 17,
        color = HongComposeColor(
            resId = R.color.honglib_color_cccccc
        ),
        fontWeight = FontWeight.W700
    ),
    unselectTodayTextStyle: HongComposeTextStyle = HongComposeTextStyle(
        size = 8,
        color = HongComposeColor(
            resId = R.color.honglib_color_545457
        ),
        fontWeight = FontWeight.W700
    ),
    selectTodayTextStyle: HongComposeTextStyle = HongComposeTextStyle(
        size = 8,
        color = HongComposeColor(
            resId = R.color.honglib_color_ffffff
        ),
        fontWeight = FontWeight.W700
    ),
    defaultTextStyle: HongComposeTextStyle = HongComposeTextStyle(
        size = 17,
        color = HongComposeColor(
            resId = R.color.honglib_color_000000
        ),
        fontWeight = FontWeight.W700
    ),
    spacingDaysMonths: Int = 20,
    spacingHorizontal: Int = 16,
    spacingMonths: Int = 40,
    spacingBetweenDates: Int = 20,
    holidays: Set<LocalDate>? = null,
    maxYears: Long = 1,
    content: (@Composable () -> Unit)? = null,
    initialDate: InitialCalendarInfo? = null,
    onDateSelected: (LocalDate?, LocalDate?) -> Unit
) {
    val today = LocalDate.now()
    val maxYearLater = today.plusYears(maxYears)
    val months = remember {
        (0..12).map { today.plusMonths(it.toLong()).withDayOfMonth(1) }
            .filter { it <= maxYearLater }
    }

    var startDate by remember { mutableStateOf(initialDate?.getStartLocalDate()) }
    var endDate by remember { mutableStateOf(initialDate?.getEndLocalDate()) }

    Column(modifier.fillMaxSize()) {
        // 요일 헤더
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(46.dp)
                .padding(horizontal = spacingHorizontal.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            listOf("일", "월", "화", "수", "목", "금", "토").forEach { day ->
                Box(
                    modifier = Modifier
                        .weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    HongText(
                        text = day,
                        style = daysTextStyle
                    )
                }
            }
        }

        lineColor?.let {
            HongDivider(
                color = lineColor,
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
                                spacingDaysMonths.dp
                            } else {
                                0.dp
                            },
                            start = spacingHorizontal.dp,
                            end = spacingHorizontal.dp,
                            bottom = spacingMonths.dp
                        )
                ) {
                    // 년, 월 표기
                    HongText(
                        modifier = Modifier.padding(bottom = 8.dp),
                        text = month.format(
                            org.threeten.bp.format.DateTimeFormatter.ofPattern(
                                yearMonthPattern
                            )
                        ),
                        style = yearMonthTextStyle
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
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            week.forEach { date ->
                                Box(
                                    modifier = Modifier.weight(1f),
                                    contentAlignment = Alignment.Center
                                ) {
                                    if (date != null) {
                                        val isPast = date.isBefore(today)
                                        val isToday = date == today
                                        val isStart = date == startDate
                                        val isEnd = date == endDate
                                        val isSelectedRange =
                                            startDate != null && endDate != null && date in startDate!!..endDate!!
                                        val isHoliday = if (holidays != null) {
                                            date in holidays || date.dayOfWeek == DayOfWeek.SUNDAY
                                        } else {
                                            date.dayOfWeek == DayOfWeek.SUNDAY
                                        }

                                        if (isStart) {
                                            if (endDate != null && startDate != endDate) {
                                                SelectBackground(
                                                    selectedType = SelectedType.START
                                                )
                                            }
                                        } else if (isEnd) {
                                            SelectBackground(
                                                selectedType = SelectedType.END
                                            )
                                        }

                                        val dateModifier = when {
                                            isStart -> {
                                                Modifier
                                                    .clip(CircleShape)
                                                    .background(
                                                        color = startDateBackgroundColor.getColor()
                                                    )
                                            }

                                            isEnd -> {
                                                Modifier
                                                    .clip(CircleShape)
                                                    .background(
                                                        color = endDateBackgroundColor.getColor()
                                                    )
                                            }

                                            isSelectedRange -> {
                                                Modifier
                                                    .background(
                                                        color = selectBackgroundColor.getColor()
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
                                                        startDate = date
                                                        endDate = null
                                                    } else if (date < startDate) {
                                                        startDate = date
                                                    } else {
                                                        endDate = date
                                                    }

                                                    onDateSelected(
                                                        startDate,
                                                        endDate
                                                    )
                                                }
                                                .then(dateModifier),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            val dayOfMonthStr = date.dayOfMonth.toString()
                                            when {
                                                isPast -> {
                                                    val pastHolidayTextStyle =
                                                        holidayTextStyle.copy(
                                                            color = HongComposeColor(
                                                                resId = R.color.honglib_color_75ff322e
                                                            )
                                                        )
                                                    DayOfMonthView(
                                                        dayOfMonth = dayOfMonthStr,
                                                        dateTextStyle = if (isHoliday) {
                                                            pastHolidayTextStyle
                                                        } else {
                                                            pastDateTextStyle
                                                        }
                                                    )
                                                }

                                                isStart -> {
                                                    DayOfMonthView(
                                                        dayOfMonth = dayOfMonthStr,
                                                        dateTextStyle = startDateTextStyle,
                                                        todayTextStyle = selectTodayTextStyle,
                                                        isToday = isToday
                                                    )
                                                }

                                                isEnd -> {
                                                    DayOfMonthView(
                                                        dayOfMonth = dayOfMonthStr,
                                                        dateTextStyle = endDateTextStyle
                                                    )
                                                }

                                                isSelectedRange -> {
                                                    DayOfMonthView(
                                                        dayOfMonth = dayOfMonthStr,
                                                        dateTextStyle = rangeDateTextStyle
                                                    )
                                                }

                                                else -> {
                                                    DayOfMonthView(
                                                        dayOfMonth = dayOfMonthStr,
                                                        dateTextStyle = if (isHoliday) {
                                                            holidayTextStyle
                                                        } else {
                                                            defaultTextStyle
                                                        },
                                                        todayTextStyle = unselectTodayTextStyle,
                                                        isToday = isToday
                                                    )
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        MarginTopOrBottom(value = spacingBetweenDates)
                    }
                }
            }
        }

        // 하단 컨텐츠
        content?.invoke()
    }
}

@Composable
private fun SelectBackground(
    selectedType: SelectedType,
    @ColorRes colorResId: Int = R.color.honglib_color_e9f0fe,
    colorType: ColorType? = null
) {
    when (selectedType) {
        SelectedType.START -> {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
            ) {
                Spacer(
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp)
                        .background(
                            colorResource(
                                id = R.color.honglib_transparent
                            )
                        )
                )
                Spacer(
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp)
                        .background(
                            colorType.getComposeColor(colorResId)
                        )
                )
            }
        }

        SelectedType.END -> {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
            ) {
                Spacer(
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp)
                        .background(
                            colorType.getComposeColor(colorResId)
                        )
                )
                Spacer(
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp)
                        .background(
                            colorResource(
                                id = R.color.honglib_transparent
                            )
                        )
                )
            }
        }

        else -> {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .background(
                        colorType.getComposeColor(colorResId)
                    )
            )
        }
    }
}

@Composable
private fun DayOfMonthView(
    dayOfMonth: String,
    dateTextStyle: HongComposeTextStyle,
    todayTextStyle: HongComposeTextStyle = HongComposeTextStyle(
        fontWeight = FontWeight.W700
    ),
    isToday: Boolean = false
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HongText(
            text = dayOfMonth,
            style = dateTextStyle
        )
        if (isToday) {
            HongText(
                text = "Today",
                style = todayTextStyle
            )
        }
    }
}

private enum class SelectedType {
    START, END, BETWEEN
}
