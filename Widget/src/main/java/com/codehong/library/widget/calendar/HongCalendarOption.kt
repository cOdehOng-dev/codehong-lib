package com.codehong.library.widget.calendar

import com.codehong.library.widget.HongWidgetCommonOption
import com.codehong.library.widget.calendar.model.HongCalendarDayOfWeekLangType
import com.codehong.library.widget.calendar.model.HongCalendarInitialSelectedInfo
import com.codehong.library.widget.rule.HongBorderInfo
import com.codehong.library.widget.rule.HongLayoutParam
import com.codehong.library.widget.rule.HongShadowInfo
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.HongWidgetType
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.radius.HongRadiusInfo
import com.codehong.library.widget.rule.typo.HongFont
import com.codehong.library.widget.text.def.HongTextBuilder
import org.threeten.bp.LocalDate

data class HongCalendarOption(
    override val type: HongWidgetType = HongWidgetType.CALENDAR
) : HongWidgetCommonOption {

    companion object {
        val DEFAULT_SELECT_START_DAY_BACKGROUND_COLOR = HongColor.MAIN_ORANGE_100
        val DEFAULT_SELECT_END_DAY_BACKGROUND_COLOR = HongColor.MAIN_ORANGE_100
        val DEFAULT_SELECT_RANGE_DAYS_BACKGROUND_COLOR = HongColor.MAIN_ORANGE_15

        val DEFAULT_DAY_OF_WEEK_TEXT_OPTION = HongTextBuilder()
            .size(13)
            .color("#666666")
            .fontType(HongFont.PRETENDARD_400)
            .applyOption()

        val DEFAULT_YEAR_MONTH_TEXT_OPTION = HongTextBuilder()
            .size(19)
            .color(HongColor.BLACK_100.hex)
            .fontType(HongFont.PRETENDARD_700)
            .applyOption()

        const val DEFAULT_YEAR_MONTH_TEXT_PATTERN = "yyyy.MM"

        val DEFAULT_START_DAY_TEXT_OPTION = HongTextBuilder()
            .size(17)
            .color(HongColor.WHITE_100.hex)
            .backgroundColor(DEFAULT_SELECT_START_DAY_BACKGROUND_COLOR)
            .fontType(HongFont.PRETENDARD_700)
            .applyOption()

        val DEFAULT_END_DAY_TEXT_OPTION = HongTextBuilder()
            .size(17)
            .color(HongColor.WHITE_100.hex)
            .backgroundColor(DEFAULT_SELECT_END_DAY_BACKGROUND_COLOR)
            .fontType(HongFont.PRETENDARD_700)
            .applyOption()

        val DEFAULT_RANGE_DAYS_TEXT_OPTION = HongTextBuilder()
            .size(17)
            .color(HongColor.MAIN_ORANGE_100.hex)
            .backgroundColor(DEFAULT_SELECT_RANGE_DAYS_BACKGROUND_COLOR)
            .fontType(HongFont.PRETENDARD_700)
            .applyOption()

        val DEFAULT_HOLIDAY_TEXT_OPTION = HongTextBuilder()
            .size(17)
            .color("#ff322e")
            .fontType(HongFont.PRETENDARD_700)
            .applyOption()

        val DEFAULT_PAST_DAYS_TEXT_OPTION = HongTextBuilder()
            .size(17)
            .color("#cccccc")
            .fontType(HongFont.PRETENDARD_700)
            .applyOption()

        val DEFAULT_UNSELECT_TODAY_TEXT_OPTION = HongTextBuilder()
            .size(8)
            .color("#545457")
            .fontType(HongFont.PRETENDARD_700)
            .applyOption()

        val DEFAULT_SELECT_TODAY_TEXT_OPTION = HongTextBuilder()
            .size(8)
            .color(HongColor.WHITE_100.hex)
            .fontType(HongFont.PRETENDARD_700)
            .applyOption()

        val DEFAULT_DAY_TEXT_OPTION = HongTextBuilder()
            .size(17)
            .color(HongColor.BLACK_100.hex)
            .fontType(HongFont.PRETENDARD_700)
            .applyOption()

        const val DEFAULT_BOTTOM_SPACING_DAY_OF_WEEK = 20

        const  val DEFAULT_SPACING_BETWEEN_MONTHS = 40

        const val DEFAULT_BOTTOM_SPACING_WEEKS = 20

        const val DEFAULT_MAX_YEARS = 1 // 최대 년도 범위

        const val DEFAULT_DAY_OF_WEEK_BOTTOM_LINE_COLOR_HEX = "#eeeeee"


    }

    override var isValidComponent: Boolean = false
    override var width: Int = HongLayoutParam.MATCH_PARENT.value
    override var height: Int = HongLayoutParam.MATCH_PARENT.value
    override var margin: HongSpacingInfo = HongSpacingInfo(0f, 0f, 0f, 0f)
    override var padding: HongSpacingInfo = HongSpacingInfo(16f, 0f, 16f, 0f)
    
    override var backgroundColorHex: String = HongColor.WHITE_100.hex
    override var click: ((HongWidgetCommonOption) -> Unit)? = null
    override var radius: HongRadiusInfo = HongRadiusInfo()
    override var shadow: HongShadowInfo = HongShadowInfo()
    override var border: HongBorderInfo = HongBorderInfo()
    override var useShapeCircle: Boolean = false

    // 요일 하단 라인 색상
    var dayOfWeekBottomLineColorHex = DEFAULT_DAY_OF_WEEK_BOTTOM_LINE_COLOR_HEX
    var dayOfWeekTextOption = DEFAULT_DAY_OF_WEEK_TEXT_OPTION

    var yearMonthTextOption = DEFAULT_YEAR_MONTH_TEXT_OPTION
    var yearMonthPattern: String = DEFAULT_YEAR_MONTH_TEXT_PATTERN

    var startDayTextOption = DEFAULT_START_DAY_TEXT_OPTION
    var endDayTextOption = DEFAULT_END_DAY_TEXT_OPTION
    var rangeDaysTextOption = DEFAULT_RANGE_DAYS_TEXT_OPTION
    var holidaysTextOption = DEFAULT_HOLIDAY_TEXT_OPTION

    var pastDaysTextOption = DEFAULT_PAST_DAYS_TEXT_OPTION

    var unselectTodayTextOption = DEFAULT_UNSELECT_TODAY_TEXT_OPTION
    var selectTodayTextOption = DEFAULT_SELECT_TODAY_TEXT_OPTION
    var defaultDayTextOption = DEFAULT_DAY_TEXT_OPTION

    var bottomSpacingDayOfWeek = DEFAULT_BOTTOM_SPACING_DAY_OF_WEEK // 요일 하단 간격
    var bottomSpacingMonth = DEFAULT_SPACING_BETWEEN_MONTHS // 월 하단 간격
    var bottomSpacingWeek = DEFAULT_BOTTOM_SPACING_WEEKS // 주간 하단 간격

    var holidayList: List<LocalDate>? = null

    var maxYears = DEFAULT_MAX_YEARS // 최대 년도 범위

    var initialSelectedInfo: HongCalendarInitialSelectedInfo? = null // 초기 달력 정보

    var dayOfWeekLangType = HongCalendarDayOfWeekLangType.KOR // 요일 언어 타입

    var onSelected: ((LocalDate?, LocalDate?) -> Unit)? = null

}