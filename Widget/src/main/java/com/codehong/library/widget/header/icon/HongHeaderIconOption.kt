package com.codehong.library.widget.header.icon

import com.codehong.library.widget.HongWidgetCommonOption
import com.codehong.library.widget.rule.HongBorderInfo
import com.codehong.library.widget.rule.HongLayoutParam
import com.codehong.library.widget.rule.HongShadowInfo
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.HongWidgetType
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.radius.HongRadiusInfo
import com.codehong.library.widget.rule.typo.HongTypo

data class HongHeaderIconOption(
    override val type: HongWidgetType = HongWidgetType.HEADER_ICON,
) : HongWidgetCommonOption {

    override var isValidComponent: Boolean = true

    override var width: Int = HongLayoutParam.MATCH_PARENT.value
    override var height: Int = 50
    override var margin: HongSpacingInfo = HongSpacingInfo()
    override var padding: HongSpacingInfo = HongSpacingInfo()
    override var click: ((HongWidgetCommonOption) -> Unit)? = null
    override var radius: HongRadiusInfo = HongRadiusInfo()
    override var shadow: HongShadowInfo = HongShadowInfo()
    override var border: HongBorderInfo = HongBorderInfo()
    override var useShapeCircle: Boolean = false

    override var backgroundColorHex: String = HongColor.WHITE_100.hex

    var title: String? = null
    var titleTypo: HongTypo = HongTypo.BODY_18
    var titleColorHex: String = HongColor.BLACK_100.hex

    var backIconRes: Int? = null
    var backIconColorHex: String = HongColor.BLACK_100.hex
    var onBackClick: () -> Unit = {}

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as HongHeaderIconOption

        if (type != other.type) return false
        if (isValidComponent != other.isValidComponent) return false
        if (width != other.width) return false
        if (height != other.height) return false
        if (margin != other.margin) return false
        if (padding != other.padding) return false
        if (click != other.click) return false
        if (radius != other.radius) return false
        if (shadow != other.shadow) return false
        if (border != other.border) return false
        if (useShapeCircle != other.useShapeCircle) return false
        if (backgroundColorHex != other.backgroundColorHex) return false
        if (title != other.title) return false
        if (titleTypo != other.titleTypo) return false
        if (titleColorHex != other.titleColorHex) return false
        if (backIconRes != other.backIconRes) return false
        if (backIconColorHex != other.backIconColorHex) return false
        if (onBackClick != other.onBackClick) return false

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
        result = 31 * result + shadow.hashCode()
        result = 31 * result + border.hashCode()
        result = 31 * result + useShapeCircle.hashCode()
        result = 31 * result + backgroundColorHex.hashCode()
        result = 31 * result + (title?.hashCode() ?: 0)
        result = 31 * result + titleTypo.hashCode()
        result = 31 * result + titleColorHex.hashCode()
        result = 31 * result + (backIconRes?.hashCode() ?: 0)
        result = 31 * result + backIconColorHex.hashCode()
        result = 31 * result + onBackClick.hashCode()
        return result
    }

    override fun toString(): String {
        return "HongHeaderIconOption(" +
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
                "title=$title, " +
                "titleTypo=$titleTypo, " +
                "titleColorHex='$titleColorHex', " +
                "backIconRes=$backIconRes, " +
                "backIconColorHex='$backIconColorHex', " +
                "onBackClick=$onBackClick" +
                ")"
    }
}
