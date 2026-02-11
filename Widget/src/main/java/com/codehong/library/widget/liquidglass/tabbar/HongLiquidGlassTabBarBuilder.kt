package com.codehong.library.widget.liquidglass.tabbar

import com.codehong.library.widget.HongWidgetCommonBuilder
import com.codehong.library.widget.liquidglass.HongLiquidGlassTabItem

class HongLiquidGlassTabBarBuilder : HongWidgetCommonBuilder<HongLiquidGlassTabBarOption, HongLiquidGlassTabBarBuilder> {

    override val builder: HongLiquidGlassTabBarBuilder = this
    override val option: HongLiquidGlassTabBarOption = HongLiquidGlassTabBarOption()


    fun tabList(tabList: List<HongLiquidGlassTabItem>) = apply {
        option.tabList = tabList
    }

    fun isDarkTheme(isDarkTheme: Boolean) = apply {
        option.isDarkTheme = isDarkTheme
    }


    fun outerRadius(outerRadius: Int) = apply {
        if (outerRadius > 40) {
            option.outerRadius = 40
            return this
        }
        option.outerRadius = outerRadius
    }


    fun onSelectedTab(onSelectedTab: (HongLiquidGlassTabItem) -> Unit) = apply {
        option.onSelectedTab = onSelectedTab
    }

}