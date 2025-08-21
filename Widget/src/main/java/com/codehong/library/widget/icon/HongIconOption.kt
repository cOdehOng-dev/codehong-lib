package com.codehong.library.widget.icon

import com.codehong.library.widget.HongWidgetCommonOption
import com.codehong.library.widget.rule.HongBorderInfo
import com.codehong.library.widget.rule.HongLayoutParam
import com.codehong.library.widget.rule.HongScaleType
import com.codehong.library.widget.rule.HongShadowInfo
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.HongWidgetType
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.icon.HongIconType
import com.codehong.library.widget.rule.radius.HongRadiusInfo

data class HongIconOption(
    override val type: HongWidgetType = HongWidgetType.ICON
): HongWidgetCommonOption {
    override var isValidComponent: Boolean = true

    override var width: Int = HongLayoutParam.WRAP_CONTENT.value
    override var height: Int = HongLayoutParam.WRAP_CONTENT.value
    override var margin = HongSpacingInfo()
    override var padding = HongSpacingInfo()
    override var radius = HongRadiusInfo()
    override var border = HongBorderInfo()
    override var shadow = HongShadowInfo()
    override var useShapeCircle: Boolean = false
    override var click: ((HongWidgetCommonOption) -> Unit)? = null

    override var backgroundColorHex: String = HongColor.TRANSPARENT.hex


    var iconType: HongIconType = HongIconType.H24
    var iconResId: Int?  = null
    var iconColorHex: String = HongColor.BLACK_100.hex
    var iconScaleType = HongScaleType.CENTER_INSIDE


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as HongIconOption

        if (type != other.type) return false
        if (isValidComponent != other.isValidComponent) return false
        if (width != other.width) return false
        if (height != other.height) return false
        if (margin != other.margin) return false
        if (padding != other.padding) return false
        if (radius != other.radius) return false
        if (border != other.border) return false
        if (shadow != other.shadow) return false
        if (useShapeCircle != other.useShapeCircle) return false
        if (click != other.click) return false
        if (backgroundColorHex != other.backgroundColorHex) return false
        if (iconType != other.iconType) return false
        if (iconResId != other.iconResId) return false
        if (iconColorHex != other.iconColorHex) return false
        if (iconScaleType != other.iconScaleType) return false

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
        result = 31 * result + border.hashCode()
        result = 31 * result + shadow.hashCode()
        result = 31 * result + useShapeCircle.hashCode()
        result = 31 * result + (click?.hashCode() ?: 0)
        result = 31 * result + backgroundColorHex.hashCode()
        result = 31 * result + iconType.hashCode()
        result = 31 * result + (iconResId ?: 0)
        result = 31 * result + iconColorHex.hashCode()
        result = 31 * result + iconScaleType.hashCode()
        return result
    }

    override fun toString(): String {
        return "HongIconOption(" +
                "type=$type, " +
                "isValidComponent=$isValidComponent, " +
                "width=$width, " +
                "height=$height, " +
                "margin=$margin, " +
                "padding=$padding, " +
                "radius=$radius, " +
                "border=$border, " +
                "shadow=$shadow, " +
                "useShapeCircle=$useShapeCircle, " +
                "click=$click, " +
                "backgroundColorHex='$backgroundColorHex', " +
                "iconType=$iconType, " +
                "iconResId=$iconResId, " +
                "iconColorHex='$iconColorHex', " +
                "iconScaleType=$iconScaleType" +
                ")"
    }


}
