package com.codehong.library.widget.tab.scroll

import com.codehong.library.widget.HongWidgetCommonBuilder
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.radius.HongRadiusInfo
import com.codehong.library.widget.rule.typo.HongTypo

class HongTabScrollBuilder : HongWidgetCommonBuilder<HongTabScrollOption, HongTabScrollBuilder> {

    override val builder: HongTabScrollBuilder = this
    override val option: HongTabScrollOption = HongTabScrollOption()

    fun tabList(tabList: List<Any>) = apply {
        option.tabList = tabList
    }

    fun tabTextList(tabTextList: List<String>) = apply {
        option.tabTextList = tabTextList
    }

    /**************************************************************
     * 활성 탭
     **************************************************************/
    fun selectTabTextTypo(typo: HongTypo) = apply {
        option.selectTabTextTypo = typo
    }

    fun selectTabTextColor(color: HongColor) = apply {
        option.selectTabTextColorHex = color.hex
    }
    fun selectTabTextColor(color: String) = apply {
        option.selectTabTextColorHex = color
    }

    fun selectBackgroundColor(color: HongColor) = apply {
        option.selectBackgroundColorHex = color.hex
    }
    fun selectBackgroundColor(color: String) = apply {
        option.selectBackgroundColorHex = color
    }

    fun selectBorderColor(color: HongColor) = apply {
        option.selectBorderColorHex = color.hex
    }
    fun selectBorderColor(color: String) = apply {
        option.selectBorderColorHex = color
    }

    fun selectBorderWidth(borderWidth: Int) = apply {
        option.selectBorderWidth = borderWidth
    }

    /**************************************************************
     * 비활성 탭
     **************************************************************/
    fun unselectTabTextTypo(typo: HongTypo) = apply {
        option.unselectTabTextTypo = typo
    }

    fun unselectTabTextColor(color: HongColor) = apply {
        option.unselectTabTextColorHex = color.hex
    }
    fun unselectTabTextColor(color: String) = apply {
        option.unselectTabTextColorHex = color
    }

    fun unselectBackgroundColor(color: HongColor) = apply {
        option.unselectBackgroundColorHex = color.hex
    }
    fun unselectBackgroundColor(color: String) = apply {
        option.unselectBackgroundColorHex = color
    }

    fun unselectBorderColor(color: HongColor) = apply {
        option.unselectBorderColorHex = color.hex
    }
    fun unselectBorderColor(color: String) = apply {
        option.unselectBorderColorHex = color
    }

    fun unselectBorderWidth(borderWidth: Int) = apply {
        option.unselectBorderWidth = borderWidth
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


    fun copy(inject: HongTabScrollOption): HongTabScrollBuilder {
        return HongTabScrollBuilder()
            .width(inject.width)
            .height(inject.height)
            .tabList(inject.tabList)
            .tabTextList(inject.tabTextList)

            .selectTabTextTypo(inject.selectTabTextTypo)
            .selectTabTextColor(inject.selectTabTextColorHex)
            .selectBackgroundColor(inject.selectBackgroundColorHex)
            .selectBorderColor(inject.selectBorderColorHex)
            .selectBorderWidth(inject.selectBorderWidth)

            .unselectTabTextTypo(inject.unselectTabTextTypo)
            .unselectTabTextColor(inject.unselectTabTextColorHex)
            .unselectBackgroundColor(inject.unselectBackgroundColorHex)
            .unselectBorderColor(inject.unselectBorderColorHex)
            .unselectBorderWidth(inject.unselectBorderWidth)

            .tabBetweenPadding(inject.tabBetweenPadding)
            .tabTextHorizontalPadding(inject.tabTextHorizontalPadding)
            .tabTextVerticalPadding(inject.tabTextVerticalPadding)
            .radius(inject.radius)
            .initialSelectIndex(inject.initialSelectIndex)
            .onTabClick(inject.tabClick)

    }
}