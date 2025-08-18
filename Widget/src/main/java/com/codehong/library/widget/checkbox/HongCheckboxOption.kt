package com.codehong.library.widget.checkbox

import com.codehong.library.widget.HongWidgetCommonOption

import com.codehong.library.widget.rule.HongBorderInfo
import com.codehong.library.widget.rule.HongLayoutParam
import com.codehong.library.widget.rule.HongShadowInfo
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.HongState
import com.codehong.library.widget.rule.HongWidgetType
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.radius.HongRadiusInfo

data class HongCheckboxOption(
    override val type: HongWidgetType = HongWidgetType.CHECKBOX,
) : HongWidgetCommonOption {

    override var isValidComponent: Boolean = true
    override var width: Int = HongLayoutParam.WRAP_CONTENT.value
    override var height: Int = HongLayoutParam.WRAP_CONTENT.value
    override var margin: HongSpacingInfo = HongSpacingInfo()
    override var padding: HongSpacingInfo = HongSpacingInfo()
    override var click: ((HongWidgetCommonOption) -> Unit)? = null

    override var backgroundColorHex: String = HongColor.TRANSPARENT.hex
    override var shadow: HongShadowInfo = HongShadowInfo()
    override var useShapeCircle: Boolean = false
    override var border: HongBorderInfo = HongBorderInfo(
        width = 2,
        color = HongColor.GRAY_40.hex
    )
    override var radius: HongRadiusInfo = HongRadiusInfo(
        topLeft = 4,
        topRight = 4,
        bottomLeft = 4,
        bottomRight = 4
    )

    var size: Int = 24

    var checkedColorHex: String =  HongColor.MAIN_ORANGE_100.hex
    var checkmarkColorHex: String = HongColor.WHITE_100.hex

    var checkState = false

    var enableState: HongState = HongState.ENABLED


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as HongCheckboxOption

        if (type != other.type) return false
        if (isValidComponent != other.isValidComponent) return false
        if (width != other.width) return false
        if (height != other.height) return false
        if (margin != other.margin) return false
        if (padding != other.padding) return false
        if (click != other.click) return false
        if (backgroundColorHex != other.backgroundColorHex) return false
        if (shadow != other.shadow) return false
        if (useShapeCircle != other.useShapeCircle) return false
        if (border != other.border) return false
        if (radius != other.radius) return false
        if (size != other.size) return false
        if (checkedColorHex != other.checkedColorHex) return false
        if (checkmarkColorHex != other.checkmarkColorHex) return false
        if (checkState != other.checkState) return false
        if (enableState != other.enableState) return false

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
        result = 31 * result + backgroundColorHex.hashCode()
        result = 31 * result + shadow.hashCode()
        result = 31 * result + useShapeCircle.hashCode()
        result = 31 * result + border.hashCode()
        result = 31 * result + radius.hashCode()
        result = 31 * result + size
        result = 31 * result + checkedColorHex.hashCode()
        result = 31 * result + checkmarkColorHex.hashCode()
        result = 31 * result + checkState.hashCode()
        result = 31 * result + enableState.hashCode()
        return result
    }

    override fun toString(): String {
        return "HongCheckboxOption(" +
                "type=$type, " +
                "isValidComponent=$isValidComponent, " +
                "width=$width, " +
                "height=$height, " +
                "margin=$margin, " +
                "padding=$padding, " +
                "click=$click, " +
                "backgroundColorHex='$backgroundColorHex', " +
                "shadow=$shadow, " +
                "useShapeCircle=$useShapeCircle, " +
                "border=$border, " +
                "radius=$radius, " +
                "size=$size, " +
                "checkedColorHex='$checkedColorHex', " +
                "checkmarkColorHex='$checkmarkColorHex', " +
                "checkState=$checkState, " +
                "enableState=$enableState" +
                ")"
    }
}