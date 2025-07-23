package com.codehong.library.widget.calendar

import com.codehong.library.widget.HongWidgetCommonOption
import com.codehong.library.widget.calendar.model.HongCalendarDayOfWeekLangType
import com.codehong.library.widget.calendar.model.HongCalendarSelectBackgroundColorHex
import com.codehong.library.widget.calendar.model.InitialSelectedInfo
import com.codehong.library.widget.rule.HongBorderInfo
import com.codehong.library.widget.rule.HongLayoutParam
import com.codehong.library.widget.rule.HongShadowInfo
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.HongWidgetType
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.radius.HongRadiusInfo
import com.codehong.library.widget.rule.typo.HongFont
import com.codehong.library.widget.text.HongTextBuilder
import org.threeten.bp.LocalDate

data class HongCalendarOption(
    override val type: HongWidgetType = HongWidgetType.CALENDAR
) : HongWidgetCommonOption {

    companion object {
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

        val DEFAULT_SELECT_BACKGROUND_COLOR = HongCalendarSelectBackgroundColorHex(
            startDayColorHex = HongColor.MAIN_ORANGE_100.hex,
            endDayColorHex = HongColor.MAIN_ORANGE_100.hex,
            rangeDaysColorHex = HongColor.MAIN_ORANGE_15.hex
        )

        val DEFAULT_START_DAY_TEXT_OPTION = HongTextBuilder()
            .size(17)
            .color(HongColor.WHITE_100.hex)
            .fontType(HongFont.PRETENDARD_700)
            .applyOption()

        val DEFAULT_END_DAY_TEXT_OPTION = HongTextBuilder()
            .size(17)
            .color(HongColor.WHITE_100.hex)
            .fontType(HongFont.PRETENDARD_700)
            .applyOption()

        val DEFAULT_RANGE_DAYS_TEXT_OPTION = HongTextBuilder()
            .size(17)
            .color(HongColor.MAIN_ORANGE_100.hex)
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

        val DEFAULT_BOTTOM_SPACING_DAY_OF_WEEK = 20

        val DEFAULT_SPACING_BETWEEN_MONTHS = 40

        val DEFAULT_BOTTOM_SPACING_WEEKS = 20

        val DEFAULT_MAX_YEARS = 1 // 최대 년도 범위

        val DEFAULT_DAY_OF_WEEK_BOTTOM_LINE_COLOR_HEX = "#eeeeee"

    }

    override var isValidComponent: Boolean = false
    override var width: Int = HongLayoutParam.MATCH_PARENT.value
    override var height: Int = HongLayoutParam.MATCH_PARENT.value
    override var margin: HongSpacingInfo = HongSpacingInfo(0f, 0f, 0f, 0f)
    override var padding: HongSpacingInfo = HongSpacingInfo(0f, 0f, 0f, 0f)
    
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

    var selectBackgroundColorHex: HongCalendarSelectBackgroundColorHex = DEFAULT_SELECT_BACKGROUND_COLOR

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

    var initialSelectedInfo: InitialSelectedInfo? = null // 초기 달력 정보

    var dayOfWeekLangType = HongCalendarDayOfWeekLangType.KOR // 요일 언어 타입

    var spacingHorizontal = 16

    var onSelected: ((LocalDate?, LocalDate?) -> Unit)? = null

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as HongCalendarOption

        if (type != other.type) return false
        if (isValidComponent != other.isValidComponent) return false
        if (width != other.width) return false
        if (height != other.height) return false
        if (margin != other.margin) return false
        if (padding != other.padding) return false
        if (backgroundColorHex != other.backgroundColorHex) return false
        if (click != other.click) return false
        if (dayOfWeekBottomLineColorHex != other.dayOfWeekBottomLineColorHex) return false
        if (dayOfWeekTextOption != other.dayOfWeekTextOption) return false
        if (yearMonthTextOption != other.yearMonthTextOption) return false
        if (yearMonthPattern != other.yearMonthPattern) return false
        if (selectBackgroundColorHex != other.selectBackgroundColorHex) return false
        if (startDayTextOption != other.startDayTextOption) return false
        if (endDayTextOption != other.endDayTextOption) return false
        if (rangeDaysTextOption != other.rangeDaysTextOption) return false
        if (holidaysTextOption != other.holidaysTextOption) return false
        if (pastDaysTextOption != other.pastDaysTextOption) return false
        if (unselectTodayTextOption != other.unselectTodayTextOption) return false
        if (selectTodayTextOption != other.selectTodayTextOption) return false
        if (defaultDayTextOption != other.defaultDayTextOption) return false
        if (bottomSpacingDayOfWeek != other.bottomSpacingDayOfWeek) return false
        if (bottomSpacingMonth != other.bottomSpacingMonth) return false
        if (bottomSpacingWeek != other.bottomSpacingWeek) return false
        if (holidayList != other.holidayList) return false
        if (maxYears != other.maxYears) return false
        if (initialSelectedInfo != other.initialSelectedInfo) return false
        if (dayOfWeekLangType != other.dayOfWeekLangType) return false
        if (spacingHorizontal != other.spacingHorizontal) return false
        if (onSelected != other.onSelected) return false

        return true
    }

    override fun hashCode(): Int {
        var result = type.hashCode()
        result = 31 * result + isValidComponent.hashCode()
        result = 31 * result + width
        result = 31 * result + height
        result = 31 * result + margin.hashCode()
        result = 31 * result + padding.hashCode()
        result = 31 * result + backgroundColorHex.hashCode()
        result = 31 * result + (click?.hashCode() ?: 0)
        result = 31 * result + dayOfWeekBottomLineColorHex.hashCode()
        result = 31 * result + dayOfWeekTextOption.hashCode()
        result = 31 * result + yearMonthTextOption.hashCode()
        result = 31 * result + yearMonthPattern.hashCode()
        result = 31 * result + selectBackgroundColorHex.hashCode()
        result = 31 * result + startDayTextOption.hashCode()
        result = 31 * result + endDayTextOption.hashCode()
        result = 31 * result + rangeDaysTextOption.hashCode()
        result = 31 * result + holidaysTextOption.hashCode()
        result = 31 * result + pastDaysTextOption.hashCode()
        result = 31 * result + unselectTodayTextOption.hashCode()
        result = 31 * result + selectTodayTextOption.hashCode()
        result = 31 * result + defaultDayTextOption.hashCode()
        result = 31 * result + bottomSpacingDayOfWeek
        result = 31 * result + bottomSpacingMonth
        result = 31 * result + bottomSpacingWeek
        result = 31 * result + (holidayList?.hashCode() ?: 0)
        result = 31 * result + maxYears
        result = 31 * result + (initialSelectedInfo?.hashCode() ?: 0)
        result = 31 * result + dayOfWeekLangType.hashCode()
        result = 31 * result + spacingHorizontal
        result = 31 * result + (onSelected?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String {
        return "HongCalendarOption(" +
                "type=$type, " +
                "isValidComponent=$isValidComponent, " +
                "width=$width, " +
                "height=$height, " +
                "margin=$margin, " +
                "padding=$padding, " +
                "backgroundColorHex='$backgroundColorHex', " +
                "click=$click, " +
                "dayOfWeekBottomLineColorHex='$dayOfWeekBottomLineColorHex', " +
                "dayOfWeekTextOption=$dayOfWeekTextOption, " +
                "yearMonthTextOption=$yearMonthTextOption, " +
                "yearMonthPattern='$yearMonthPattern', " +
                "selectBackgroundColorHex=$selectBackgroundColorHex, " +
                "startDayTextOption=$startDayTextOption, " +
                "endDayTextOption=$endDayTextOption, " +
                "rangeDaysTextOption=$rangeDaysTextOption, " +
                "holidaysTextOption=$holidaysTextOption, " +
                "pastDaysTextOption=$pastDaysTextOption, " +
                "unselectTodayTextOption=$unselectTodayTextOption, " +
                "selectTodayTextOption=$selectTodayTextOption, " +
                "defaultDayTextOption=$defaultDayTextOption, " +
                "bottomSpacingDayOfWeek=$bottomSpacingDayOfWeek, " +
                "bottomSpacingMonth=$bottomSpacingMonth, " +
                "bottomSpacingWeek=$bottomSpacingWeek, " +
                "holidayList=$holidayList, " +
                "maxYears=$maxYears, " +
                "initialSelectedInfo=$initialSelectedInfo, " +
                "dayOfWeekLangType=$dayOfWeekLangType, " +
                "spacingHorizontal=$spacingHorizontal, " +
                "onSelected=$onSelected" +
                ")"
    }


}