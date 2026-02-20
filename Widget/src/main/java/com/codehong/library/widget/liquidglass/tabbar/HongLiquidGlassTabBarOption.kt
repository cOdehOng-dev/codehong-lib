package com.codehong.library.widget.liquidglass.tabbar

import com.codehong.library.widget.HongWidgetCommonOption
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

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as HongLiquidGlassTabBarOption

        if (isValidComponent != other.isValidComponent) return false
        if (width != other.width) return false
        if (height != other.height) return false
        if (useShapeCircle != other.useShapeCircle) return false
        if (isDarkTheme != other.isDarkTheme) return false
        if (outerRadius != other.outerRadius) return false
        if (verticalPadding != other.verticalPadding) return false
        if (innerSideGap != other.innerSideGap) return false
        if (tabBarHeight != other.tabBarHeight) return false
        if (type != other.type) return false
        if (margin != other.margin) return false
        if (padding != other.padding) return false
        if (click != other.click) return false
        if (radius != other.radius) return false
        if (shadow != other.shadow) return false
        if (border != other.border) return false
        if (backgroundColorHex != other.backgroundColorHex) return false
        if (tabList != other.tabList) return false
        if (onSelectedTab != other.onSelectedTab) return false
        if (tabSelectTypo != other.tabSelectTypo) return false
        if (tabDefTypo != other.tabDefTypo) return false

        return true
    }

    override fun hashCode(): Int {
        var result = isValidComponent.hashCode()
        result = 31 * result + width
        result = 31 * result + height
        result = 31 * result + useShapeCircle.hashCode()
        result = 31 * result + isDarkTheme.hashCode()
        result = 31 * result + outerRadius
        result = 31 * result + verticalPadding
        result = 31 * result + innerSideGap
        result = 31 * result + tabBarHeight
        result = 31 * result + type.hashCode()
        result = 31 * result + margin.hashCode()
        result = 31 * result + padding.hashCode()
        result = 31 * result + (click?.hashCode() ?: 0)
        result = 31 * result + radius.hashCode()
        result = 31 * result + shadow.hashCode()
        result = 31 * result + border.hashCode()
        result = 31 * result + backgroundColorHex.hashCode()
        result = 31 * result + tabList.hashCode()
        result = 31 * result + onSelectedTab.hashCode()
        result = 31 * result + tabSelectTypo.hashCode()
        result = 31 * result + tabDefTypo.hashCode()
        return result
    }

    override fun toString(): String {
        return "HongLiquidGlassTabBarOption(" +
                "type=$type, " +
                "isValidComponent=$isValidComponent, " +
                "width=$width, " +
                "height=$height, " +
                "margin=$margin, " +
                "padding=$padding, " +
                "click=$click, " +
                "radius=$radius, " +
                "shadow=$shadow, " +
                "border=$border, " +
                "useShapeCircle=$useShapeCircle, " +
                "backgroundColorHex='$backgroundColorHex', " +
                "tabList=$tabList, " +
                "isDarkTheme=$isDarkTheme, " +
                "onSelectedTab=$onSelectedTab, " +
                "outerRadius=$outerRadius, " +
                "verticalPadding=$verticalPadding, " +
                "innerSideGap=$innerSideGap, " +
                "tabBarHeight=$tabBarHeight, " +
                "tabSelectTypo=$tabSelectTypo, " +
                "tabDefTypo=$tabDefTypo" +
                ")"
    }


}
