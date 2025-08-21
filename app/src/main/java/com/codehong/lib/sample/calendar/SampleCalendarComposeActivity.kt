package com.codehong.lib.sample.calendar

import android.os.Bundle
import androidx.compose.runtime.Composable
import com.codehong.lib.sample.SampleConst
import com.codehong.lib.sample.base.BaseSampleComposeActivity
import com.codehong.library.widget.calendar.HongCalendarBuilder
import com.codehong.library.widget.calendar.HongCalendarCompose
import com.codehong.library.widget.calendar.model.HongCalendarDayOfWeekLangType
import com.codehong.library.widget.calendar.model.HongCalendarSelectBackgroundColorHex
import com.codehong.library.widget.calendar.model.InitialSelectedInfo
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.typo.HongFont
import com.codehong.library.widget.text.HongTextBuilder
import com.codehong.library.widget.util.HongDateUtil
import com.codehong.library.widget.util.HongToastUtil

class SampleCalendarComposeActivity : BaseSampleComposeActivity() {
    private var isInitialDate = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.isInitialDate = intent.getBooleanExtra(SampleConst.INITIAL_DATE, false)
    }

    @Composable
    override fun InitCompose() {
        HongCalendarCompose(
            option = HongCalendarBuilder()
                .backgroundColor(HongColor.WHITE_100.hex)
                .initialSelectedInfo(
                    if (isInitialDate) {
                        InitialSelectedInfo(
                            startDate = "20250710",
                            endDate = "20250720"
                        )
                    } else {
                        null
                    }
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
                .onSelected { startDate, endDate ->
                    if (startDate == null || endDate == null) {
                        return@onSelected
                    }
                    HongToastUtil.showToast(this, "선택된 날짜: $startDate ~ $endDate")
                }
                .applyOption(),
        )
    }
}
