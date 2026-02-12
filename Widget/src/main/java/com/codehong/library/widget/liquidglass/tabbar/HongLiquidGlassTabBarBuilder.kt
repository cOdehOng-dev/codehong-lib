package com.codehong.library.widget.liquidglass.tabbar

import com.codehong.library.widget.HongWidgetCommonBuilder
import com.codehong.library.widget.liquidglass.tabbar.HongLiquidGlassTabItem
import com.codehong.library.widget.rule.typo.HongTypo

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

    fun tabBarHeight(tabBarHeight: Int) = apply {
        option.tabBarHeight = tabBarHeight
    }

    fun tabVerticalPadding(verticalPadding: Int) = apply {
        option.verticalPadding = verticalPadding
    }

    fun innerSideGap(innerSideGap: Int) = apply {
        if (innerSideGap > 16) {
            option.innerSideGap = 16
            return this
        }
        option.innerSideGap = innerSideGap
    }

    fun tabSelectTypo(tabSelectTypo: HongTypo) = apply {
        option.tabSelectTypo = tabSelectTypo
    }

    fun tabDefTypo(tabDefTypo: HongTypo) = apply {
        option.tabDefTypo = tabDefTypo
    }


    fun onSelectedTab(onSelectedTab: (Int, HongLiquidGlassTabItem) -> Unit) = apply {
        option.onSelectedTab = onSelectedTab
    }

}