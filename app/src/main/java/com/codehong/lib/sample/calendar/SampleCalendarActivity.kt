package com.codehong.lib.sample.calendar

import android.os.Bundle
import com.codehong.lib.sample.SampleConst
import com.codehong.lib.sample.base.BaseActivity
import com.codehong.lib.sample.databinding.ActivitySampleCalendarBinding
import com.codehong.library.widget.calendar.HongCalendarBuilder
import com.codehong.library.widget.calendar.HongCalendarOption
import com.codehong.library.widget.calendar.HongCalendarView
import com.codehong.library.widget.calendar.model.HongCalendarDayOfWeekLangType
import com.codehong.library.widget.calendar.model.InitialSelectedInfo
import com.codehong.library.widget.rule.HongWidgetType
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.typo.HongFont
import com.codehong.library.widget.text.def.HongTextBuilder
import com.codehong.library.widget.util.HongDateUtil
import com.codehong.library.widget.util.HongToastUtil

class SampleCalendarActivity : BaseActivity() {

    private lateinit var binding: ActivitySampleCalendarBinding

    private var isInitialDate = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySampleCalendarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        this.isInitialDate = intent.getBooleanExtra(SampleConst.INITIAL_DATE, false)

        binding.vHeader.init(
            title = "${HongWidgetType.CALENDAR?.value} 샘플",
            back = {
                finish()
            }
        )

        val option = HongCalendarBuilder()
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
            .backgroundColor(HongColor.WHITE_100.hex)
            .bottomSpacingWeek(20)
            .holidayList(HongDateUtil.KOREAN_HOLIDAY_LIST)
            .dayOfWeekBottomLineColorHex("#eeeeee")
            .onSelected { startDate, endDate ->
                if (startDate == null || endDate == null) {
                    return@onSelected
                }
                HongToastUtil.showToast(this, "선택된 날짜: $startDate ~ $endDate")
            }
            .applyOption()


        binding.flContainer.addView(
            HongCalendarView(this).set(option)
        )

    }

}