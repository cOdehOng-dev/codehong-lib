package com.codehong.library.widget.toggleswitch

import com.codehong.library.widget.HongWidgetAdvanceOption
import com.codehong.library.widget.HongWidgetCommonOption
import com.codehong.library.widget.rule.HongBorderInfo
import com.codehong.library.widget.rule.HongShadowInfo
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.HongWidgetType
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.radius.HongRadiusInfo

data class HongSwitchOption(
    override val type: HongWidgetType = HongWidgetType.SWITCH
) : HongWidgetAdvanceOption {

    companion object {
        const val DEFAULT_WIDTH = 55
        const val DEFAULT_HEIGHT = 30
        const val DEFAULT_CURSOR_SIZE = 25
        const val DEFAULT_CURSOR_HORIZONTAL_MARGIN = 3
    }

    override var isValidComponent: Boolean = true
    override var width: Int = DEFAULT_WIDTH
    override var height: Int = DEFAULT_HEIGHT
    override var margin: HongSpacingInfo = HongSpacingInfo()
    override var padding: HongSpacingInfo = HongSpacingInfo()

    override var backgroundColor: HongColor = HongColor.TRANSPARENT
    override var backgroundColorHex: String = HongColor.TRANSPARENT.hex
    override var click: ((HongWidgetCommonOption) -> Unit)? = null

    var switchClick: ((HongWidgetCommonOption, Boolean) -> Unit)? = null

    var onColor: HongColor = HongColor.MAIN_ORANGE_100
    var onColorHex: String = HongColor.MAIN_ORANGE_100.hex

    var offColor: HongColor = HongColor.GRAY_20
    var offColorHex: String = HongColor.GRAY_20.hex

    var cursorSize: Int = DEFAULT_CURSOR_SIZE

    var cursorHorizontalMargin: Int = DEFAULT_CURSOR_HORIZONTAL_MARGIN

    var cursorColor: HongColor? = HongColor.WHITE_100
    var cursorColorHex: String = HongColor.WHITE_100.hex

    var initialState: Boolean = false

    override var radius: HongRadiusInfo = HongRadiusInfo()
    override var border: HongBorderInfo = HongBorderInfo()

    override var useShapeCircle: Boolean = true
    override var shadow = HongShadowInfo()


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as HongSwitchOption

        if (type != other.type) return false
        if (isValidComponent != other.isValidComponent) return false
        if (width != other.width) return false
        if (height != other.height) return false
        if (margin != other.margin) return false
        if (padding != other.padding) return false
        if (backgroundColor != other.backgroundColor) return false
        if (backgroundColorHex != other.backgroundColorHex) return false
        if (click != other.click) return false
        if (switchClick != other.switchClick) return false
        if (onColor != other.onColor) return false
        if (onColorHex != other.onColorHex) return false
        if (offColor != other.offColor) return false
        if (offColorHex != other.offColorHex) return false
        if (cursorSize != other.cursorSize) return false
        if (cursorHorizontalMargin != other.cursorHorizontalMargin) return false
        if (cursorColor != other.cursorColor) return false
        if (cursorColorHex != other.cursorColorHex) return false
        if (initialState != other.initialState) return false
        if (radius != other.radius) return false
        if (border != other.border) return false
        if (useShapeCircle != other.useShapeCircle) return false
        if (shadow != other.shadow) return false

        return true
    }

    override fun hashCode(): Int {
        var result = type.hashCode()
        result = 31 * result + isValidComponent.hashCode()
        result = 31 * result + width
        result = 31 * result + height
        result = 31 * result + margin.hashCode()
        result = 31 * result + padding.hashCode()
        result = 31 * result + backgroundColor.hashCode()
        result = 31 * result + backgroundColorHex.hashCode()
        result = 31 * result + (click?.hashCode() ?: 0)
        result = 31 * result + (switchClick?.hashCode() ?: 0)
        result = 31 * result + onColor.hashCode()
        result = 31 * result + onColorHex.hashCode()
        result = 31 * result + offColor.hashCode()
        result = 31 * result + offColorHex.hashCode()
        result = 31 * result + cursorSize
        result = 31 * result + cursorHorizontalMargin
        result = 31 * result + (cursorColor?.hashCode() ?: 0)
        result = 31 * result + cursorColorHex.hashCode()
        result = 31 * result + initialState.hashCode()
        result = 31 * result + radius.hashCode()
        result = 31 * result + border.hashCode()
        result = 31 * result + useShapeCircle.hashCode()
        result = 31 * result + shadow.hashCode()
        return result
    }

    override fun toString(): String {
        return "HongSwitchOption(" +
                "type=$type, " +
                "isValidComponent=$isValidComponent, " +
                "width=$width, " +
                "height=$height, " +
                "margin=$margin, " +
                "padding=$padding, " +
                "backgroundColor=$backgroundColor, " +
                "backgroundColorHex='$backgroundColorHex', " +
                "click=$click, " +
                "switchClick=$switchClick, " +
                "onColor=$onColor, " +
                "onColorHex='$onColorHex', " +
                "offColor=$offColor, " +
                "offColorHex='$offColorHex', " +
                "cursorSize=$cursorSize, " +
                "cursorHorizontalMargin=$cursorHorizontalMargin, " +
                "cursorColor=$cursorColor, " +
                "cursorColorHex='$cursorColorHex', " +
                "initialState=$initialState, " +
                "radius=$radius, " +
                "border=$border, " +
                "useShapeCircle=$useShapeCircle, " +
                "shadow=$shadow" +
                ")"
    }
}