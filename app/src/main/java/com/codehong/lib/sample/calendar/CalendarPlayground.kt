package com.codehong.lib.sample.calendar

import android.util.Log
import com.codehong.lib.sample.playground.BasePlayground
import com.codehong.lib.sample.playground.PlaygroundActivity
import com.codehong.library.widget.calendar.HongCalendarBuilder
import com.codehong.library.widget.calendar.HongCalendarOption
import com.codehong.library.widget.calendar.model.HongCalendarDayOfWeekLangType
import com.codehong.library.widget.calendar.model.HongCalendarSelectBackgroundColorHex
import com.codehong.library.widget.rule.HongWidgetType
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.typo.HongFont
import com.codehong.library.widget.text.def.HongTextBuilder
import com.codehong.library.widget.util.HongDateUtil

class CalendarPlayground(
    playgroundActivity: PlaygroundActivity
) : BasePlayground<HongCalendarOption> {

    companion object {
        val DEFAULT_PREVIEW_OPTION = HongCalendarBuilder()
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
            .onSelected { startDate, endDate ->
                if (startDate == null || endDate == null) {
                    return@onSelected
                }
                Log.d("TAG", "선택된 날짜: $startDate ~ $endDate")
            }
            .applyOption()
    }

    override val activity: PlaygroundActivity = playgroundActivity
    override var previewOption: HongCalendarOption = DEFAULT_PREVIEW_OPTION
    override val widgetType: HongWidgetType = HongWidgetType.CALENDAR

    fun preview() {
        executePreview()

        commonPreviewOption(
            height = previewOption.height,
            margin = previewOption.margin,
            useWidth = false,
            usePadding = false,
            selectHeight = {
                previewOption = HongCalendarBuilder()
                    .copy(previewOption)
                    .height(it)
                    .applyOption()
                executePreview()
            },
            selectMargin = {
                previewOption = HongCalendarBuilder()
                    .copy(previewOption)
                    .margin(it)
                    .applyOption()
                executePreview()
            },
        )
    }
}