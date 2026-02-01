package com.codehong.library.widget.tab.segment

import com.codehong.library.widget.HongWidgetCommonOption
import com.codehong.library.widget.rule.HongBorderInfo
import com.codehong.library.widget.rule.HongLayoutParam
import com.codehong.library.widget.rule.HongShadowInfo
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.HongWidgetType
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.radius.HongRadiusInfo
import com.codehong.library.widget.rule.typo.HongTypo

data class HongTabSegmentOption(
    override val type: HongWidgetType = HongWidgetType.TAB_SEGMENT
) : HongWidgetCommonOption {

    override var isValidComponent: Boolean = true
    override var width: Int = HongLayoutParam.MATCH_PARENT.value
    override var height: Int = HongLayoutParam.WRAP_CONTENT.value
    override var margin: HongSpacingInfo = HongSpacingInfo(
        left = 16f,
        top = 16f,
        right = 16f,
        bottom = 16f
    )
    override var padding: HongSpacingInfo = HongSpacingInfo(
        left = 4f,
        top = 4f,
        right = 4f,
        bottom = 4f
    )
    override var radius: HongRadiusInfo = HongRadiusInfo(
        topLeft = 24,
        topRight = 24,
        bottomLeft = 24,
        bottomRight = 24
    )
    override var backgroundColorHex: String = HongColor.TRANSPARENT.hex
    override var click: ((HongWidgetCommonOption) -> Unit)? = null
    override var border: HongBorderInfo = HongBorderInfo()
    override var useShapeCircle: Boolean = false
    override var shadow = HongShadowInfo()


    var tabTextList: List<String> = emptyList()

    var initialSelectIndex: Int = 0
    var indicatorColorHex: String = HongColor.WHITE_100.hex

    var selectTabTextColorHex: String = HongColor.BLACK_100.hex
    var unselectTabColorHex: String = HongColor.GRAY_50.hex

    var selectTypo: HongTypo = HongTypo.BODY_15_B
    var unselectTypo: HongTypo = HongTypo.BODY_15

    var tabWidth: Int = 100
    var tabHeight: Int = 40

    var tabClick: ((Int) -> Unit)? = null


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as HongTabSegmentOption

        if (type != other.type) return false
        if (isValidComponent != other.isValidComponent) return false
        if (width != other.width) return false
        if (height != other.height) return false
        if (margin != other.margin) return false
        if (padding != other.padding) return false
        if (radius != other.radius) return false
        if (backgroundColorHex != other.backgroundColorHex) return false
        if (click != other.click) return false
        if (border != other.border) return false
        if (useShapeCircle != other.useShapeCircle) return false
        if (shadow != other.shadow) return false
        if (tabTextList != other.tabTextList) return false
        if (initialSelectIndex != other.initialSelectIndex) return false
        if (indicatorColorHex != other.indicatorColorHex) return false
        if (selectTabTextColorHex != other.selectTabTextColorHex) return false
        if (unselectTabColorHex != other.unselectTabColorHex) return false
        if (selectTypo != other.selectTypo) return false
        if (unselectTypo != other.unselectTypo) return false
        if (tabWidth != other.tabWidth) return false
        if (tabHeight != other.tabHeight) return false
        if (tabClick != other.tabClick) return false

        return true
    }

    override fun hashCode(): Int {
        var result = type.hashCode()
        result = 31 * result + isValidComponent.hashCode()
        result = 31 * result + width
        result = 31 * result + height
        result = 31 * result + margin.hashCode()
        result = 31 * result + padding.hashCode()
        result = 31 * result + radius.hashCode()
        result = 31 * result + backgroundColorHex.hashCode()
        result = 31 * result + (click?.hashCode() ?: 0)
        result = 31 * result + border.hashCode()
        result = 31 * result + useShapeCircle.hashCode()
        result = 31 * result + shadow.hashCode()
        result = 31 * result + tabTextList.hashCode()
        result = 31 * result + initialSelectIndex
        result = 31 * result + indicatorColorHex.hashCode()
        result = 31 * result + selectTabTextColorHex.hashCode()
        result = 31 * result + unselectTabColorHex.hashCode()
        result = 31 * result + selectTypo.hashCode()
        result = 31 * result + unselectTypo.hashCode()
        result = 31 * result + tabWidth
        result = 31 * result + tabHeight
        result = 31 * result + (tabClick?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String {
        return "HongTabSegmentOption(" +
                "type=$type, " +
                "isValidComponent=$isValidComponent, " +
                "width=$width, " +
                "height=$height, " +
                "margin=$margin, " +
                "padding=$padding, " +
                "radius=$radius, " +
                "backgroundColorHex='$backgroundColorHex', " +
                "click=$click, " +
                "border=$border, " +
                "useShapeCircle=$useShapeCircle, " +
                "shadow=$shadow, " +
                "tabTextList=$tabTextList, " +
                "initialSelectIndex=$initialSelectIndex, " +
                "indicatorColorHex='$indicatorColorHex', " +
                "selectTabTextColorHex='$selectTabTextColorHex', " +
                "unselectTabColorHex='$unselectTabColorHex', " +
                "selectTypo=$selectTypo, " +
                "unselectTypo=$unselectTypo, " +
                "tabWidth=$tabWidth, " +
                "tabHeight=$tabHeight, " +
                "tabClick=$tabClick" +
                ")"
    }


}