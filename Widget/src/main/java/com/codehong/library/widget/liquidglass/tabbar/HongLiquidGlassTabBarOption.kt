package com.codehong.library.widget.liquidglass.tabbar

import com.codehong.library.widget.HongWidgetCommonOption
import com.codehong.library.widget.liquidglass.HongLiquidGlassTabItem
import com.codehong.library.widget.rule.HongBorderInfo
import com.codehong.library.widget.rule.HongLayoutParam
import com.codehong.library.widget.rule.HongShadowInfo
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.HongWidgetType
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.radius.HongRadiusInfo
import com.codehong.library.widget.rule.typo.HongTypo

data class HongLiquidGlassTabBarOption(
    override val type: HongWidgetType = HongWidgetType.LIQUID_GLASS_TAB_BAR,
) : HongWidgetCommonOption {

    override var isValidComponent: Boolean = true

    override var width: Int = HongLayoutParam.MATCH_PARENT.value
    override var height: Int = 52
    override var margin: HongSpacingInfo = HongSpacingInfo()
    override var padding: HongSpacingInfo = HongSpacingInfo()
    override var click: ((HongWidgetCommonOption) -> Unit)? = null
    override var radius: HongRadiusInfo = HongRadiusInfo()
    override var shadow: HongShadowInfo = HongShadowInfo()
    override var border: HongBorderInfo = HongBorderInfo()
    override var useShapeCircle: Boolean = false

    override var backgroundColorHex: String = HongColor.TRANSPARENT.hex

    var tabList: List<HongLiquidGlassTabItem> = emptyList()

    var isDarkTheme: Boolean = false

    var onSelectedTab: (Int, HongLiquidGlassTabItem) -> Unit = {_, _ ->}


    var outerRadius: Int = 40

    var verticalPadding: Int = 12

    var innerSideGap: Int = 16

    var tabBarHeight: Int = 80


    var tabSelectTypo: HongTypo = HongTypo.CONTENTS_12_B
    var tabDefTypo: HongTypo = HongTypo.CONTENTS_12



}
