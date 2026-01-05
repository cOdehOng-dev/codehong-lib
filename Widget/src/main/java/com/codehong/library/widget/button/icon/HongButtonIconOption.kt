package com.codehong.library.widget.button.icon

import com.codehong.library.widget.HongWidgetCommonOption
import com.codehong.library.widget.rule.HongBorderInfo
import com.codehong.library.widget.rule.HongClickState
import com.codehong.library.widget.rule.HongLayoutParam
import com.codehong.library.widget.rule.HongShadowInfo
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.HongWidgetType
import com.codehong.library.widget.rule.button.HongButtonIconType
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.radius.HongRadiusInfo

data class HongButtonIconOption(
    override val type: HongWidgetType = HongWidgetType.BUTTON_ICON
) : HongWidgetCommonOption {

    companion object {
        val DEFAULT_BORDER = HongBorderInfo(
            width = 1,
            color = HongColor.GRAY_40.hex
        )
        val DEFAULT_BUTTON_TYPE = HongButtonIconType.SIZE_28
    }

    override var isValidComponent: Boolean = true
    override var width: Int = HongLayoutParam.WRAP_CONTENT.value
    override var height: Int = HongLayoutParam.WRAP_CONTENT.value
    override var margin: HongSpacingInfo = HongSpacingInfo()
    override var padding: HongSpacingInfo = HongSpacingInfo()
    override var shadow = HongShadowInfo()


    override var backgroundColorHex: String = HongColor.WHITE_100.hex
    override var border: HongBorderInfo = DEFAULT_BORDER
    override var radius: HongRadiusInfo = HongRadiusInfo()
    override var useShapeCircle: Boolean = false
    override var click: ((HongWidgetCommonOption) -> Unit)? = null

    var buttonType: HongButtonIconType = DEFAULT_BUTTON_TYPE
    var iconColorHex: String = HongColor.BLACK_100.hex
    var state: HongClickState = HongClickState.ENABLE
    var iconResId: Int = 0

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as HongButtonIconOption

        if (type != other.type) return false
        if (isValidComponent != other.isValidComponent) return false
        if (width != other.width) return false
        if (height != other.height) return false
        if (margin != other.margin) return false
        if (padding != other.padding) return false
        if (shadow != other.shadow) return false
        if (backgroundColorHex != other.backgroundColorHex) return false
        if (border != other.border) return false
        if (radius != other.radius) return false
        if (useShapeCircle != other.useShapeCircle) return false
        if (click != other.click) return false
        if (buttonType != other.buttonType) return false
        if (iconColorHex != other.iconColorHex) return false
        if (state != other.state) return false
        if (iconResId != other.iconResId) return false

        return true
    }

    override fun hashCode(): Int {
        var result = type.hashCode()
        result = 31 * result + isValidComponent.hashCode()
        result = 31 * result + width
        result = 31 * result + height
        result = 31 * result + margin.hashCode()
        result = 31 * result + padding.hashCode()
        result = 31 * result + shadow.hashCode()
        result = 31 * result + backgroundColorHex.hashCode()
        result = 31 * result + border.hashCode()
        result = 31 * result + radius.hashCode()
        result = 31 * result + useShapeCircle.hashCode()
        result = 31 * result + (click?.hashCode() ?: 0)
        result = 31 * result + buttonType.hashCode()
        result = 31 * result + iconColorHex.hashCode()
        result = 31 * result + state.hashCode()
        result = 31 * result + iconResId
        return result
    }

    override fun toString(): String {
        return "HongIconButtonOption(" +
                "type=$type, " +
                "isValidComponent=$isValidComponent, " +
                "width=$width, " +
                "height=$height, " +
                "margin=$margin, " +
                "padding=$padding, " +
                "shadow=$shadow, " +
                "backgroundColorHex='$backgroundColorHex', " +
                "border=$border, " +
                "radius=$radius, " +
                "useShapeCircle=$useShapeCircle, " +
                "click=$click, " +
                "buttonType=$buttonType, " +
                "iconColorHex='$iconColorHex', " +
                "state=$state, " +
                "iconResId=$iconResId" +
                ")"
    }
}