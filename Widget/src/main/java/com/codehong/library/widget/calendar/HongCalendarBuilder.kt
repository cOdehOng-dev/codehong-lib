package com.codehong.library.widget.calendar

import com.codehong.library.widget.HongWidgetCommonBuilder
import com.codehong.library.widget.calendar.model.HongCalendarDayOfWeekLangType
import com.codehong.library.widget.calendar.model.InitialSelectedInfo
import com.codehong.library.widget.rule.HongLayoutParam
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.text.def.HongTextOption
import org.threeten.bp.LocalDate

class HongCalendarBuilder : HongWidgetCommonBuilder<HongCalendarOption, HongCalendarBuilder> {

    override val builder: HongCalendarBuilder = this
    override val option: HongCalendarOption = HongCalendarOption()

    override fun width(width: Int?) = apply {
        option.width = HongLayoutParam.MATCH_PARENT.value
    }

    override fun onClick(onClick: ((HongCalendarOption) -> Unit)?) = apply {
        option.click = null
    }

    override fun padding(padding: HongSpacingInfo) = apply {
        option.padding = HongSpacingInfo(
            left = padding.left,
            top = 0f,
            right = padding.right,
            bottom = 0f
        )
    }

    fun spacingHorizontal(spacingHorizontal: Int) = apply {
        padding(
            HongSpacingInfo(
                left = spacingHorizontal.toFloat(),
                top = 0f,
                right = spacingHorizontal.toFloat(),
                bottom = 0f
            )
        )
    }

    fun dayOfWeekTextOption(dayOfWeekTextOption: HongTextOption) = apply {
        this.option.dayOfWeekTextOption = dayOfWeekTextOption
    }

    fun dayOfWeekBottomLineColorHex(dayOfWeekBottomLineColorHex: String) = apply {
        this.option.dayOfWeekBottomLineColorHex = dayOfWeekBottomLineColorHex
    }

    fun yearMonthTextOption(yearMonthTextOption: HongTextOption) = apply {
        this.option.yearMonthTextOption = yearMonthTextOption
    }

    fun yearMonthPattern(pattern: String) = apply {
        this.option.yearMonthPattern = pattern
    }


    fun startDayTextOption(startDayTextOption: HongTextOption) = apply {
        this.option.startDayTextOption = startDayTextOption
    }

    fun endDayTextOption(endDayTextOption: HongTextOption) = apply {
        this.option.endDayTextOption = endDayTextOption
    }

    fun rangeDaysTextOption(rangeDaysTextOption: HongTextOption) = apply {
        this.option.rangeDaysTextOption = rangeDaysTextOption
    }

    fun holidaysTextOption(holidaysTextOption: HongTextOption) = apply {
        this.option.holidaysTextOption = holidaysTextOption
    }

    fun pastDaysTextOption(pastDaysTextOption: HongTextOption) = apply {
        this.option.pastDaysTextOption = pastDaysTextOption
    }

    fun unselectTodayTextOption(unselectTodayTextOption: HongTextOption) = apply {
        this.option.unselectTodayTextOption = unselectTodayTextOption
    }

    fun selectTodayTextOption(selectTodayTextOption: HongTextOption) = apply {
        this.option.selectTodayTextOption = selectTodayTextOption
    }

    fun defaultDayTextOption(defaultDayTextOption: HongTextOption) = apply {
        this.option.defaultDayTextOption = defaultDayTextOption
    }

    fun bottomSpacingDayOfWeek(bottomSpacingDayOfWeek: Int) = apply {
        this.option.bottomSpacingDayOfWeek = bottomSpacingDayOfWeek
    }

    fun bottomSpacingMonth(bottomSpacingMonth: Int) = apply {
        this.option.bottomSpacingMonth = bottomSpacingMonth
    }

    fun bottomSpacingWeek(bottomSpacingWeek: Int) = apply {
        this.option.bottomSpacingWeek = bottomSpacingWeek
    }

    fun holidayList(holidayList: List<LocalDate>?) = apply {
        this.option.holidayList = holidayList
    }

    fun maxYears(maxYears: Int) = apply {
        this.option.maxYears = maxYears
    }

    fun initialSelectedInfo(initialDate: InitialSelectedInfo?) = apply {
        this.option.initialSelectedInfo = initialDate
    }

    fun dayOfWeekLangType(dayOfWeekLangType: HongCalendarDayOfWeekLangType) = apply {
        this.option.dayOfWeekLangType = dayOfWeekLangType
    }

    fun onSelected(onSelected: ((LocalDate?, LocalDate?) -> Unit)?) = apply {
        this.option.onSelected = onSelected
    }

    fun copy(injectOption: HongCalendarOption): HongCalendarBuilder {
        return HongCalendarBuilder()
            .width(injectOption.width)
            .height(injectOption.height)
            .margin(injectOption.margin)
            .padding(injectOption.padding)
            .backgroundColor(injectOption.backgroundColorHex)
            .dayOfWeekTextOption(injectOption.dayOfWeekTextOption)
            .dayOfWeekBottomLineColorHex(injectOption.dayOfWeekBottomLineColorHex)
            .yearMonthTextOption(injectOption.yearMonthTextOption)
            .yearMonthPattern(injectOption.yearMonthPattern)
            .startDayTextOption(injectOption.startDayTextOption)
            .endDayTextOption(injectOption.endDayTextOption)
            .rangeDaysTextOption(injectOption.rangeDaysTextOption)
            .holidaysTextOption(injectOption.holidaysTextOption)
            .pastDaysTextOption(injectOption.pastDaysTextOption)
            .unselectTodayTextOption(injectOption.unselectTodayTextOption)
            .selectTodayTextOption(injectOption.selectTodayTextOption)
            .defaultDayTextOption(injectOption.defaultDayTextOption)
            .bottomSpacingDayOfWeek(injectOption.bottomSpacingDayOfWeek)
            .bottomSpacingMonth(injectOption.bottomSpacingMonth)
            .bottomSpacingWeek(injectOption.bottomSpacingWeek)
            .holidayList(injectOption.holidayList)
            .maxYears(injectOption.maxYears)
            .initialSelectedInfo(injectOption.initialSelectedInfo)
            .dayOfWeekLangType(injectOption.dayOfWeekLangType)
            .onSelected(injectOption.onSelected)
    }


}