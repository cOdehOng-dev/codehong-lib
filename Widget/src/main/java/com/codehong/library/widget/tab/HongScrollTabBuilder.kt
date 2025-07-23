package com.codehong.library.widget.tab

import com.codehong.library.widget.HongWidgetCommonBuilder
import com.codehong.library.widget.rule.radius.HongRadiusInfo
import com.codehong.library.widget.text.HongTextBuilder
import com.codehong.library.widget.text.HongTextOption

class HongScrollTabBuilder : HongWidgetCommonBuilder<HongScrollTabOption, HongScrollTabBuilder> {

    override val builder: HongScrollTabBuilder = this
    override val option: HongScrollTabOption = HongScrollTabOption()


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

    fun onTabClick(click: ((index: Int, item: Any) -> Unit)?) = apply {
        option.tabClick = click
    }


    fun copy(inject: HongScrollTabOption): HongScrollTabBuilder {
        return HongScrollTabBuilder()
            .width(inject.width)
            .height(inject.height)
            .margin(inject.margin)
            .padding(inject.padding)
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
            .onTabClick(inject.tabClick)
    }
}