package com.codehong.library.widget.tab.flow

import com.codehong.library.widget.HongWidgetCommonOption
import com.codehong.library.widget.rule.HongBorderInfo
import com.codehong.library.widget.rule.HongLayoutParam
import com.codehong.library.widget.rule.HongShadowInfo
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.HongWidgetType
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.radius.HongRadiusInfo
import com.codehong.library.widget.rule.typo.HongTypo

data class HongTabFlowOption(
    override val type: HongWidgetType = HongWidgetType.TAB_FLOW
) : HongWidgetCommonOption {

    override var isValidComponent: Boolean = true

    override var width: Int = HongLayoutParam.MATCH_PARENT.value
    override var height: Int = HongLayoutParam.WRAP_CONTENT.value
    override var margin: HongSpacingInfo = HongSpacingInfo()
    override var padding: HongSpacingInfo = HongSpacingInfo()
    override var backgroundColorHex: String = HongColor.TRANSPARENT.hex
    override var click: ((HongWidgetCommonOption) -> Unit)? = null
    override var radius: HongRadiusInfo = HongRadiusInfo()
    override var border: HongBorderInfo = HongBorderInfo()
    override var useShapeCircle: Boolean = false
    override var shadow = HongShadowInfo()

    var tabList: List<String> = emptyList()
    var initialSelectedIndex: Int = 0
    var maxRowCount: Int = 3


    var betweenTabSpacing: Int = 10
    var rowSpacing: Int = 10

    var tabRadius = HongRadiusInfo(
        topLeft = 18,
        topRight = 18,
        bottomLeft = 18,
        bottomRight = 18
    )

    var selectBackgroundColorHex = HongColor.WHITE_100.hex
    var unselectTabBackgroundColorHex = HongColor.WHITE_100.hex

    var selectedBorder = HongBorderInfo(
        width = 2,
        color = HongColor.BLACK_80.hex
    )
    var unselectedBorder = HongBorderInfo(
        width = 1,
        color = HongColor.GRAY_30.hex
    )

    var selectTextColorHex = HongColor.BLACK_100.hex
    var unselectTextColorHex = HongColor.BLACK_100.hex

    var selectTextTypo = HongTypo.BODY_16_B
    var unselectTextTypo = HongTypo.BODY_16

    var onSelect: ((Int) -> Unit)? = null

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as HongTabFlowOption

        if (type != other.type) return false
        if (isValidComponent != other.isValidComponent) return false
        if (width != other.width) return false
        if (height != other.height) return false
        if (margin != other.margin) return false
        if (padding != other.padding) return false
        if (backgroundColorHex != other.backgroundColorHex) return false
        if (click != other.click) return false
        if (radius != other.radius) return false
        if (border != other.border) return false
        if (useShapeCircle != other.useShapeCircle) return false
        if (shadow != other.shadow) return false
        if (tabList != other.tabList) return false
        if (initialSelectedIndex != other.initialSelectedIndex) return false
        if (maxRowCount != other.maxRowCount) return false
        if (betweenTabSpacing != other.betweenTabSpacing) return false
        if (rowSpacing != other.rowSpacing) return false
        if (tabRadius != other.tabRadius) return false
        if (selectBackgroundColorHex != other.selectBackgroundColorHex) return false
        if (unselectTabBackgroundColorHex != other.unselectTabBackgroundColorHex) return false
        if (selectedBorder != other.selectedBorder) return false
        if (unselectedBorder != other.unselectedBorder) return false
        if (selectTextColorHex != other.selectTextColorHex) return false
        if (unselectTextColorHex != other.unselectTextColorHex) return false
        if (selectTextTypo != other.selectTextTypo) return false
        if (unselectTextTypo != other.unselectTextTypo) return false
        if (onSelect != other.onSelect) return false

        return true
    }

    override fun hashCode(): Int {
        var result = type.hashCode()
        result = 31 * result + isValidComponent.hashCode()
        result = 31 * result + width
        result = 31 * result + height
        result = 31 * result + margin.hashCode()
        result = 31 * result + padding.hashCode()
        result = 31 * result + backgroundColorHex.hashCode()
        result = 31 * result + (click?.hashCode() ?: 0)
        result = 31 * result + radius.hashCode()
        result = 31 * result + border.hashCode()
        result = 31 * result + useShapeCircle.hashCode()
        result = 31 * result + shadow.hashCode()
        result = 31 * result + tabList.hashCode()
        result = 31 * result + initialSelectedIndex
        result = 31 * result + maxRowCount
        result = 31 * result + betweenTabSpacing
        result = 31 * result + rowSpacing
        result = 31 * result + tabRadius.hashCode()
        result = 31 * result + selectBackgroundColorHex.hashCode()
        result = 31 * result + unselectTabBackgroundColorHex.hashCode()
        result = 31 * result + selectedBorder.hashCode()
        result = 31 * result + unselectedBorder.hashCode()
        result = 31 * result + selectTextColorHex.hashCode()
        result = 31 * result + unselectTextColorHex.hashCode()
        result = 31 * result + selectTextTypo.hashCode()
        result = 31 * result + unselectTextTypo.hashCode()
        result = 31 * result + (onSelect?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String {
        return "HongTabFlowOption(" +
                "type=$type, " +
                "isValidComponent=$isValidComponent, " +
                "width=$width, " +
                "height=$height, " +
                "margin=$margin, " +
                "padding=$padding, " +
                "backgroundColorHex='$backgroundColorHex', " +
                "click=$click, " +
                "radius=$radius, " +
                "border=$border, " +
                "useShapeCircle=$useShapeCircle, " +
                "shadow=$shadow, " +
                "tabList=$tabList, " +
                "initialSelectedIndex=$initialSelectedIndex, " +
                "maxRowCount=$maxRowCount, " +
                "betweenTabSpacing=$betweenTabSpacing, " +
                "rowSpacing=$rowSpacing, " +
                "tabRadius=$tabRadius, " +
                "selectBackgroundColorHex='$selectBackgroundColorHex', " +
                "unselectTabBackgroundColorHex='$unselectTabBackgroundColorHex', " +
                "selectedBorder=$selectedBorder, " +
                "unselectedBorder=$unselectedBorder, " +
                "selectTextColorHex='$selectTextColorHex', " +
                "unselectTextColorHex='$unselectTextColorHex', " +
                "selectTextTypo=$selectTextTypo, " +
                "unselectTextTypo=$unselectTextTypo, " +
                "onSelect=$onSelect" +
                ")"
    }


}