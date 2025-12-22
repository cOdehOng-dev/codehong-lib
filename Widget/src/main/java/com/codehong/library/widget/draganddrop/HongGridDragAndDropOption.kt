package com.codehong.library.widget.draganddrop

import com.codehong.library.widget.HongWidgetCommonOption
import com.codehong.library.widget.rule.HongBorderInfo
import com.codehong.library.widget.rule.HongLayoutParam
import com.codehong.library.widget.rule.HongShadowInfo
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.HongWidgetType
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.radius.HongRadiusInfo

data class HongGridDragAndDropOption(
    override val type: HongWidgetType = HongWidgetType.GRID_DRAG_AND_DROP
) : HongWidgetCommonOption {

    override var isValidComponent: Boolean = true

    override var width: Int = HongLayoutParam.MATCH_PARENT.value
    override var height: Int = HongLayoutParam.MATCH_PARENT.value
    override var margin: HongSpacingInfo = HongSpacingInfo(0f, 0f, 0f, 0f)
    override var padding: HongSpacingInfo = HongSpacingInfo(0f, 0f, 0f, 0f)
    override var click: ((HongWidgetCommonOption) -> Unit)? = null

    override var radius: HongRadiusInfo = HongRadiusInfo()

    override var backgroundColorHex: String = HongColor.WHITE_100.hex

    override var border: HongBorderInfo = HongBorderInfo()
    override var shadow = HongShadowInfo()

    override var useShapeCircle: Boolean = false

    var onItemClick: () -> Unit = {}
    var onBackClick: () -> Unit = {}

    var itemList: List<Any> = emptyList()

    var inboundColorHex: String = HongColor.MAIN_ORANGE_25.hex

    var gridColumns: Int = 3
    var gridHorizontalSpacing: Int = 8
    var gridVerticalSpacing: Int = 8
    var gridContentPadding: HongSpacingInfo = HongSpacingInfo(0f, 0f, 0f, 0f)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as HongGridDragAndDropOption

        if (type != other.type) return false
        if (isValidComponent != other.isValidComponent) return false
        if (width != other.width) return false
        if (height != other.height) return false
        if (margin != other.margin) return false
        if (padding != other.padding) return false
        if (click != other.click) return false
        if (radius != other.radius) return false
        if (backgroundColorHex != other.backgroundColorHex) return false
        if (border != other.border) return false
        if (shadow != other.shadow) return false
        if (useShapeCircle != other.useShapeCircle) return false
        if (onItemClick != other.onItemClick) return false
        if (onBackClick != other.onBackClick) return false
        if (itemList != other.itemList) return false
        if (inboundColorHex != other.inboundColorHex) return false
        if (gridColumns != other.gridColumns) return false
        if (gridHorizontalSpacing != other.gridHorizontalSpacing) return false
        if (gridVerticalSpacing != other.gridVerticalSpacing) return false
        if (gridContentPadding != other.gridContentPadding) return false

        return true
    }

    override fun hashCode(): Int {
        var result = type.hashCode()
        result = 31 * result + isValidComponent.hashCode()
        result = 31 * result + width
        result = 31 * result + height
        result = 31 * result + margin.hashCode()
        result = 31 * result + padding.hashCode()
        result = 31 * result + (click?.hashCode() ?: 0)
        result = 31 * result + radius.hashCode()
        result = 31 * result + backgroundColorHex.hashCode()
        result = 31 * result + border.hashCode()
        result = 31 * result + shadow.hashCode()
        result = 31 * result + useShapeCircle.hashCode()
        result = 31 * result + onItemClick.hashCode()
        result = 31 * result + onBackClick.hashCode()
        result = 31 * result + itemList.hashCode()
        result = 31 * result + inboundColorHex.hashCode()
        result = 31 * result + gridColumns
        result = 31 * result + gridHorizontalSpacing
        result = 31 * result + gridVerticalSpacing
        result = 31 * result + gridContentPadding.hashCode()
        return result
    }

    override fun toString(): String {
        return "HongGridDragAndDropOption(" +
                "type=$type, " +
                "isValidComponent=$isValidComponent, " +
                "width=$width, " +
                "height=$height, " +
                "margin=$margin, " +
                "padding=$padding, " +
                "click=$click, " +
                "radius=$radius, " +
                "backgroundColorHex='$backgroundColorHex', " +
                "border=$border, " +
                "shadow=$shadow, " +
                "useShapeCircle=$useShapeCircle, " +
                "onItemClick=$onItemClick, " +
                "onBackClick=$onBackClick, " +
                "itemList=$itemList, " +
                "inboundColorHex='$inboundColorHex', " +
                "gridColumns=$gridColumns, " +
                "gridHorizontalSpacing=$gridHorizontalSpacing, " +
                "gridVerticalSpacing=$gridVerticalSpacing, " +
                "gridContentPadding=$gridContentPadding" +
                ")"
    }
}