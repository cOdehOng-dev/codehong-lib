package com.codehong.library.widget.graph

import com.codehong.library.widget.HongWidgetCommonOption
import com.codehong.library.widget.rule.HongBorderInfo
import com.codehong.library.widget.rule.HongLayoutParam
import com.codehong.library.widget.rule.HongShadowInfo
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.HongWidgetType
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.graph.GraphPoint
import com.codehong.library.widget.rule.radius.HongRadiusInfo
import com.codehong.library.widget.rule.typo.HongTypo

data class HongGraphOption(
    override val type: HongWidgetType = HongWidgetType.GRAPH_LINE
): HongWidgetCommonOption {
    override var isValidComponent: Boolean = true

    override var width: Int = HongLayoutParam.MATCH_PARENT.value
    override var height: Int = HongLayoutParam.WRAP_CONTENT.value
    override var margin: HongSpacingInfo = HongSpacingInfo()
    override var click: ((HongWidgetCommonOption) -> Unit)? = null
    override var radius: HongRadiusInfo = HongRadiusInfo()
    override var border: HongBorderInfo = HongBorderInfo()
    override var shadow = HongShadowInfo()
    override var useShapeCircle: Boolean = false

    override var padding: HongSpacingInfo = HongSpacingInfo(left = 20f, right = 20f, top = 20f)
    override var backgroundColorHex: String = HongColor.TRANSPARENT.hex

    var graphHeight = 198

    var graphPointList: List<GraphPoint> = emptyList()

    var dotLineColorHex = HongColor.GRAY_30.hex
    var dotLineWidth = 1.5f

    var graphColorHex = HongColor.MAIN_ORANGE_100.hex
    var graphLineWidth = 7

    var dividerColorHex = HongColor.GRAY_20.hex
    var dividerWidth = 1

    var labelColorHex = HongColor.BLACK_100.hex
    var labelTypo = HongTypo.CONTENTS_10


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as HongGraphOption

        if (type != other.type) return false
        if (isValidComponent != other.isValidComponent) return false
        if (width != other.width) return false
        if (height != other.height) return false
        if (margin != other.margin) return false
        if (click != other.click) return false
        if (radius != other.radius) return false
        if (border != other.border) return false
        if (shadow != other.shadow) return false
        if (useShapeCircle != other.useShapeCircle) return false
        if (padding != other.padding) return false
        if (backgroundColorHex != other.backgroundColorHex) return false
        if (graphHeight != other.graphHeight) return false
        if (graphPointList != other.graphPointList) return false
        if (dotLineColorHex != other.dotLineColorHex) return false
        if (dotLineWidth != other.dotLineWidth) return false
        if (graphColorHex != other.graphColorHex) return false
        if (graphLineWidth != other.graphLineWidth) return false
        if (dividerColorHex != other.dividerColorHex) return false
        if (dividerWidth != other.dividerWidth) return false
        if (labelColorHex != other.labelColorHex) return false
        if (labelTypo != other.labelTypo) return false

        return true
    }

    override fun hashCode(): Int {
        var result = type.hashCode()
        result = 31 * result + isValidComponent.hashCode()
        result = 31 * result + width
        result = 31 * result + height
        result = 31 * result + margin.hashCode()
        result = 31 * result + (click?.hashCode() ?: 0)
        result = 31 * result + radius.hashCode()
        result = 31 * result + border.hashCode()
        result = 31 * result + shadow.hashCode()
        result = 31 * result + useShapeCircle.hashCode()
        result = 31 * result + padding.hashCode()
        result = 31 * result + backgroundColorHex.hashCode()
        result = 31 * result + graphHeight
        result = 31 * result + graphPointList.hashCode()
        result = 31 * result + dotLineColorHex.hashCode()
        result = 31 * result + dotLineWidth.hashCode()
        result = 31 * result + graphColorHex.hashCode()
        result = 31 * result + graphLineWidth
        result = 31 * result + dividerColorHex.hashCode()
        result = 31 * result + dividerWidth
        result = 31 * result + labelColorHex.hashCode()
        result = 31 * result + labelTypo.hashCode()
        return result
    }

    override fun toString(): String {
        return "HongGraphOption(" +
                "type=$type, " +
                "isValidComponent=$isValidComponent, " +
                "width=$width, " +
                "height=$height, " +
                "margin=$margin, " +
                "click=$click, " +
                "radius=$radius, " +
                "border=$border, " +
                "shadow=$shadow, " +
                "useShapeCircle=$useShapeCircle, " +
                "padding=$padding, " +
                "backgroundColorHex='$backgroundColorHex', " +
                "graphHeight=$graphHeight, " +
                "graphPointList=$graphPointList, " +
                "dotLineColorHex='$dotLineColorHex', " +
                "dotLineWidth=$dotLineWidth, " +
                "graphColorHex='$graphColorHex', " +
                "graphLineWidth=$graphLineWidth, " +
                "dividerColorHex='$dividerColorHex', " +
                "dividerWidth=$dividerWidth, " +
                "labelColorHex='$labelColorHex', " +
                "labelTypo=$labelTypo" +
                ")"
    }


}
