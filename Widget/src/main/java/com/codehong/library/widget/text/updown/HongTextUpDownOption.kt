package com.codehong.library.widget.text.updown

import com.codehong.library.widget.HongWidgetCommonOption
import com.codehong.library.widget.rule.HongBorderInfo
import com.codehong.library.widget.rule.HongLayoutParam
import com.codehong.library.widget.rule.HongShadowInfo
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.HongWidgetType
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.radius.HongRadiusInfo
import com.codehong.library.widget.rule.typo.HongTypo

data class HongTextUpDownOption(
    override val type: HongWidgetType = HongWidgetType.TEXT_UP_DOWN
) : HongWidgetCommonOption {

    override var isValidComponent: Boolean = true

    override var width: Int = HongLayoutParam.WRAP_CONTENT.value
    override var height: Int = HongLayoutParam.WRAP_CONTENT.value
    override var margin: HongSpacingInfo = HongSpacingInfo()
    override var padding: HongSpacingInfo = HongSpacingInfo()

    override var click: ((HongWidgetCommonOption) -> Unit)? = null
    override var radius: HongRadiusInfo = HongRadiusInfo()
    override var shadow: HongShadowInfo = HongShadowInfo()
    override var border: HongBorderInfo = HongBorderInfo()
    override var useShapeCircle: Boolean = false
    override var backgroundColorHex: String = HongColor.TRANSPARENT.hex

    var amount: Int = 0
    var unit: String? = null

    var useDecimal = false

    var displayTypo: HongTypo = HongTypo.BODY_16
    var displayColorHex: String = HongColor.BLACK_100.hex

    var borderColorHex: String = HongColor.GRAY_30.hex
    var iconColorHex: String = HongColor.GRAY_50.hex

    var buttonSize: Int = 25

    var spaceButtonAndDisplay = 5

    var onResult: (Int) -> Unit = {}
    var gap: Int = 1

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as HongTextUpDownOption

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
        if (amount != other.amount) return false
        if (unit != other.unit) return false
        if (useDecimal != other.useDecimal) return false
        if (displayTypo != other.displayTypo) return false
        if (displayColorHex != other.displayColorHex) return false
        if (borderColorHex != other.borderColorHex) return false
        if (iconColorHex != other.iconColorHex) return false
        if (buttonSize != other.buttonSize) return false
        if (spaceButtonAndDisplay != other.spaceButtonAndDisplay) return false
        if (onResult != other.onResult) return false
        if (gap != other.gap) return false

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
        result = 31 * result + amount
        result = 31 * result + (unit?.hashCode() ?: 0)
        result = 31 * result + useDecimal.hashCode()
        result = 31 * result + displayTypo.hashCode()
        result = 31 * result + displayColorHex.hashCode()
        result = 31 * result + borderColorHex.hashCode()
        result = 31 * result + iconColorHex.hashCode()
        result = 31 * result + buttonSize
        result = 31 * result + spaceButtonAndDisplay
        result = 31 * result + onResult.hashCode()
        result = 31 * result + gap
        return result
    }

    override fun toString(): String {
        return "HongTextUpDownOption(" +
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
                "amount=$amount, " +
                "unit=$unit, " +
                "useDecimal=$useDecimal, " +
                "displayTypo=$displayTypo, " +
                "displayColorHex='$displayColorHex', " +
                "borderColor='$borderColorHex', " +
                "iconColorHex='$iconColorHex', " +
                "buttonSize=$buttonSize, " +
                "spaceButtonAndDisplay=$spaceButtonAndDisplay, " +
                "onResult=$onResult, " +
                "gap=$gap" +
                ")"
    }


}