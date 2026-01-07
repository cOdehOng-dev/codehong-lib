package com.codehong.library.widget.calendar.adapter

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.LinearLayout.LayoutParams
import androidx.recyclerview.widget.RecyclerView
import com.codehong.library.widget.calendar.HongCalendarOption
import com.codehong.library.widget.calendar.model.HongCalendarSelectedType
import com.codehong.library.widget.databinding.HonglibItemCalendarBinding
import com.codehong.library.widget.extensions.dpToPx
import com.codehong.library.widget.extensions.hongMargin
import com.codehong.library.widget.extensions.toParseColor
import com.codehong.library.widget.rule.HongLayoutParam
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.typo.HongFont
import com.codehong.library.widget.text.def.HongTextBuilder
import com.codehong.library.widget.text.def.HongTextOption
import com.codehong.library.widget.text.def.HongTextView
import org.threeten.bp.DayOfWeek
import org.threeten.bp.LocalDate
import org.threeten.bp.YearMonth
import org.threeten.bp.format.DateTimeFormatter

class HongCalendarViewHolder(
    private val binding: HonglibItemCalendarBinding,
    private val option: HongCalendarOption,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        month: LocalDate?,
        today: LocalDate,
        startDate: LocalDate?,
        endDate: LocalDate?,
        onDayClick: (LocalDate) -> Unit
    ) {
        binding.llCalendarDays.removeAllViews()
        if (month == null) return

        binding.llCalendarContainer
            .hongMargin(
                HongSpacingInfo(
                    bottom = option.bottomSpacingMonth.toFloat()
                )
            )
        binding.tvYearMonth.set(
            HongTextBuilder()
                .copy(option.yearMonthTextOption)
                .width(HongLayoutParam.MATCH_PARENT.value)
                .padding(
                    HongSpacingInfo(
                        left = 11f,
                        right = 11f,
                        bottom = 8f
                    )
                )
                .text(month.format(DateTimeFormatter.ofPattern(option.yearMonthPattern)))
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
            val row = LinearLayout(binding.root.context).apply {
                orientation = LinearLayout.HORIZONTAL
                layoutParams = LayoutParams(
                    LayoutParams.MATCH_PARENT,
                    LayoutParams.WRAP_CONTENT
                )
                weightSum = 7f
                hongMargin(
                    margin = HongSpacingInfo(
                        bottom = option.bottomSpacingWeek.toFloat(),
                    )
                )
            }
            week.forEachIndexed { i, day ->
                if (day != null) {
                    val isPast = day.isBefore(today)
                    val isToday = day == today
                    val isStart = day == startDate
                    val isEnd = day == endDate
                    val isSelectedRange =
                        startDate != null && endDate != null && day in startDate..endDate
                    val holidayList = option.holidayList
                    val isHoliday = if (holidayList != null) {
                        day in holidayList || day.dayOfWeek == DayOfWeek.SUNDAY
                    } else {
                        day.dayOfWeek == DayOfWeek.SUNDAY
                    }

                    row.addView(
                        FrameLayout(binding.root.context).apply {
                            layoutParams = LayoutParams(0, context.dpToPx(48)).apply {
                                weight = 1f
                            }
                            val selectBackgroundView = if (isStart) {
                                if (endDate != null && startDate != endDate) {
                                    selectStartOrBackground(
                                        selectedType = HongCalendarSelectedType.START,
                                        selectedBackgroundColorHex = option.rangeDaysTextOption.backgroundColorHex
                                    )
                                } else {
                                    null
                                }
                            } else if (isEnd) {
                                selectStartOrBackground(
                                    selectedType = HongCalendarSelectedType.END,
                                    selectedBackgroundColorHex = option.rangeDaysTextOption.backgroundColorHex
                                )
                            } else {
                                null
                            }

                            selectBackgroundView?.let {
                                addView(it)
                            }

                            addView(
                                FrameLayout(binding.root.context).apply {
                                    background = when {
                                        isStart -> circleDrawable(option.startDayTextOption.backgroundColorHex)
                                        isEnd -> circleDrawable(option.endDayTextOption.backgroundColorHex)
                                        isSelectedRange -> rectangleDrawable(option.rangeDaysTextOption.backgroundColorHex)
                                        else -> null
                                    }
                                    val dayOfMonthStr = day.dayOfMonth.toString()

                                    val view = when {
                                        isPast -> {
                                            dayOfMonthView(
                                                context,
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
                                            dayOfMonthView(
                                                context,
                                                dayOfMonth = dayOfMonthStr,
                                                dayTextOption = option.startDayTextOption,
                                                todayTextOption = option.selectTodayTextOption,
                                                isToday = isToday
                                            )
                                        }

                                        isEnd -> {
                                            dayOfMonthView(
                                                context,
                                                dayOfMonth = dayOfMonthStr,
                                                dayTextOption = option.endDayTextOption
                                            )
                                        }

                                        isSelectedRange -> {
                                            dayOfMonthView(
                                                context,
                                                dayOfMonth = dayOfMonthStr,
                                                dayTextOption = option.rangeDaysTextOption
                                            )
                                        }

                                        else -> {
                                            dayOfMonthView(
                                                context,
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
                                    val params = FrameLayout.LayoutParams(
                                        LayoutParams.WRAP_CONTENT,
                                        LayoutParams.WRAP_CONTENT
                                    ).apply {
                                        gravity = Gravity.CENTER
                                    }

                                    setOnClickListener {
                                        if (!isPast) {
                                            onDayClick(day)
                                        }
                                    }

                                    addView(view, params)
                                }
                            )

                        }
                    )
                } else {
                    row.addView(
                        FrameLayout(binding.root.context).apply {
                            layoutParams = LayoutParams(0, context.dpToPx(48)).apply {
                                weight = 1f
                            }
                            isEnabled = false
                        }
                    )
                }
            }
            binding.llCalendarDays.addView(row)
        }
    }

    private fun dayOfMonthView(
        context: Context,
        dayOfMonth: String,
        dayTextOption: HongTextOption,
        todayTextOption: HongTextOption = HongTextBuilder()
            .fontType(HongFont.PRETENDARD_700)
            .applyOption(),
        isToday: Boolean = false
    ): View {
        return LinearLayout(binding.root.context).apply {
            orientation = LinearLayout.VERTICAL
            layoutParams = LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT
            )
            gravity = Gravity.CENTER_HORIZONTAL
        }.apply {
            addView(
                HongTextView(context).set(
                    HongTextBuilder()
                        .copy(dayTextOption)
                        .text(dayOfMonth)
                        .applyOption()
                )
            )
            if (isToday) {
                addView(
                    HongTextView(context).set(
                        HongTextBuilder()
                            .copy(todayTextOption)
                            .text("Today")
                            .applyOption()
                    )
                )
            }
        }
    }

    private fun selectStartOrBackground(
        selectedType: HongCalendarSelectedType,
        selectedBackgroundColorHex: String
    ): View? {
        return when (selectedType) {
            HongCalendarSelectedType.START -> {
                return LinearLayout(binding.root.context).apply {
                    orientation = LinearLayout.HORIZONTAL
                    layoutParams = LayoutParams(
                        LayoutParams.MATCH_PARENT,
                        context.dpToPx(48)
                    )
                    gravity = Gravity.CENTER_VERTICAL

                    addView(
                        View(binding.root.context).apply {
                            layoutParams = LayoutParams(0, context.dpToPx(48)).apply {
                                weight = 1f
                            }
                            setBackgroundColor(Color.TRANSPARENT)
                        }
                    )

                    addView(
                        View(binding.root.context).apply {
                            layoutParams = LayoutParams(0, context.dpToPx(48)).apply {
                                weight = 1f
                            }
                            setBackgroundColor(selectedBackgroundColorHex.toParseColor())
                        }
                    )
                }
            }

            HongCalendarSelectedType.END -> {
                return LinearLayout(binding.root.context).apply {
                    orientation = LinearLayout.HORIZONTAL
                    layoutParams = LayoutParams(
                        LayoutParams.MATCH_PARENT,
                        context.dpToPx(48)
                    )
                    gravity = Gravity.CENTER_VERTICAL

                    addView(
                        View(binding.root.context).apply {
                            layoutParams = LayoutParams(0, context.dpToPx(48)).apply {
                                weight = 1f
                            }
                            setBackgroundColor(selectedBackgroundColorHex.toParseColor())
                        }
                    )

                    addView(
                        View(binding.root.context).apply {
                            layoutParams = LayoutParams(0, context.dpToPx(48)).apply {
                                weight = 1f
                            }
                            setBackgroundColor(Color.TRANSPARENT)
                        }
                    )
                }
            }

            else -> null
        }
    }

    private fun circleDrawable(colorHex: String): GradientDrawable {
        return GradientDrawable().apply {
            shape = GradientDrawable.OVAL
            setColor(Color.parseColor(colorHex))
        }
    }

    private fun rectangleDrawable(colorHex: String): GradientDrawable {
        return GradientDrawable().apply {
            shape = GradientDrawable.RECTANGLE
            setColor(Color.parseColor(colorHex))
        }
    }
}