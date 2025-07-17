package com.codehong.library.widget.tab

import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.radius.HongRadiusInfo
import com.codehong.library.widget.text.HongTextBuilder
import com.codehong.library.widget.text.HongTextOption

class HongScrollTabBuilder {

    var option = HongScrollTabOption()

    fun width(width: Int?) = apply {
        width?.let { option.width = it }
    }

    fun height(height: Int?) = apply {
        height?.let { option.height = it }
    }

    fun margin(margin: HongSpacingInfo) = apply {
        option.margin = margin
    }

    fun padding(padding: HongSpacingInfo) = apply {
        option.padding = padding
    }

    fun onClick(onClick: ((HongScrollTabOption) -> Unit)?) = apply {
        option.click = {
            if (it is HongScrollTabOption) {
                onClick?.invoke(it)
            }
        }
    }


    fun tabList(tabList: List<Any>) = apply {
        option.tabList = tabList
    }

    fun tabTitleList(tabTitleList: List<String>) = apply {
        option.tabTitleList = tabTitleList
    }

    fun selectTabTextOption(option: HongTextOption?) = apply {
        this.option.selectTabTextOption = option
            ?: HongTextBuilder()
                .typography(HongScrollTabOption.DEFAULT_SELECT_TEXT_TYPO)
                .color(HongScrollTabOption.DEFAULT_SELECT_TEXT_COLOR)
                .applyOption()
    }

    fun unselectTabTextOption(option: HongTextOption?) = apply {
        this.option.unselectTabTextOption = option
            ?: HongTextBuilder()
                .typography(HongScrollTabOption.DEFAULT_UNSELECT_TEXT_TYPO)
                .color(HongScrollTabOption.DEFAULT_UNSELECT_TEXT_COLOR)
                .applyOption()
    }

    fun borderWidth(borderWidth: Int) = apply {
        option.borderWidth = borderWidth
    }

    fun selectBackgroundColor(color: String) = apply {
        option.selectBackgroundColor = color
    }

    fun selectBorderColor(color: String) = apply {
        option.selectBorderColor = color
    }

    fun unselectBackgroundColor(color: String) = apply {
        option.unselectBackgroundColor = color
    }

    fun unselectBorderColor(color: String) = apply {
        option.unselectBorderColor = color
    }

    fun tabBetweenPadding(padding: Int) = apply {
        option.tabBetweenPadding = padding
    }

    fun tabTextHorizontalPadding(padding: Int) = apply {
        option.tabTextHorizontalPadding = padding
    }

    fun tabTextVerticalPadding(padding: Int) = apply {
        option.tabTextVerticalPadding = padding
    }

    fun radius(radius: HongRadiusInfo) = apply {
        option.radius = radius
    }

    fun initialSelectIndex(index: Int) = apply {
        option.initialSelectIndex = index
    }

    fun applyOption(): HongScrollTabOption {
        return option
    }

    fun copy(inject: HongScrollTabOption): HongScrollTabBuilder {
        return HongScrollTabBuilder()
            .width(inject.width)
            .height(inject.height)
            .margin(inject.margin)
            .padding(inject.padding)
            .onClick(inject.click)
            .tabList(inject.tabList)
            .tabTitleList(inject.tabTitleList)
            .selectTabTextOption(inject.selectTabTextOption)
            .unselectTabTextOption(inject.unselectTabTextOption)
            .borderWidth(inject.borderWidth)
            .selectBackgroundColor(inject.selectBackgroundColor)
            .selectBorderColor(inject.selectBorderColor)
            .unselectBackgroundColor(inject.unselectBackgroundColor)
            .unselectBorderColor(inject.unselectBorderColor)
            .tabBetweenPadding(inject.tabBetweenPadding)
            .tabTextHorizontalPadding(inject.tabTextHorizontalPadding)
            .tabTextVerticalPadding(inject.tabTextVerticalPadding)
            .radius(inject.radius)
            .initialSelectIndex(inject.initialSelectIndex)
    }
}