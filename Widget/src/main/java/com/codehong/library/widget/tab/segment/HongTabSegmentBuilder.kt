package com.codehong.library.widget.tab.segment

import com.codehong.library.widget.HongWidgetCommonBuilder
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.radius.HongRadiusInfo
import com.codehong.library.widget.rule.typo.HongTypo

class HongTabSegmentBuilder : HongWidgetCommonBuilder<HongTabSegmentOption, HongTabSegmentBuilder> {

    override val builder: HongTabSegmentBuilder = this
    override val option: HongTabSegmentOption = HongTabSegmentOption()

    fun radius(radiusInfo: HongRadiusInfo) = apply {
        option.radius = radiusInfo
    }

    fun tabTextList(tabTextList: List<String>) = apply {
        option.tabTextList = tabTextList
    }

    fun initialSelectIndex(index: Int) = apply {
        option.initialSelectIndex = index
    }

    fun indicatorColor(color: HongColor) = apply {
        option.indicatorColorHex = color.hex
    }
    fun indicatorColor(color: String) = apply {
        option.indicatorColorHex = color
    }

    fun selectTextColor(color: HongColor) = apply {
        option.selectTextColorHex = color.hex
    }
    fun selectTextColor(color: String) = apply {
        option.selectTextColorHex = color
    }

    fun unselectTabTextColor(color: HongColor) = apply {
        option.unselectTabColorHex = color.hex
    }
    fun unselectTabTextColor(color: String) = apply {
        option.unselectTabColorHex = color
    }

    fun tabWidth(width: Int) = apply {
        option.tabWidth = width
    }
    fun tabHeight(height: Int) = apply {
        option.tabHeight = height
    }

    fun selectTypo(typo: HongTypo) = apply {
        option.selectTypo = typo
    }
    fun unselectTypo(typo: HongTypo) = apply {
        option.unselectTypo = typo
    }

    fun onTabClick(click: ((Int) -> Unit)?) = apply {
        option.tabClick = click
    }

    fun copy(inject: HongTabSegmentOption?): HongTabSegmentBuilder {
        if (inject == null) return HongTabSegmentBuilder()

        return HongTabSegmentBuilder()
            .width(inject.width)
            .height(inject.height)
            .margin(inject.margin)
            .padding(inject.padding)
            .backgroundColor(inject.backgroundColorHex)
            .tabTextList(inject.tabTextList)
            .initialSelectIndex(inject.initialSelectIndex)
            .indicatorColor(inject.indicatorColorHex)
            .selectTextColor(inject.selectTextColorHex)
            .unselectTabTextColor(inject.unselectTabColorHex)
            .tabWidth(inject.tabWidth)
            .tabHeight(inject.tabHeight)
            .onTabClick(inject.tabClick)
            .selectTypo(inject.selectTypo)
            .unselectTypo(inject.unselectTypo)

    }

}