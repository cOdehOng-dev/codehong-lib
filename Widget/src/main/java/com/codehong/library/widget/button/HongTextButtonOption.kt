package com.codehong.library.widget.button

import com.codehong.library.widget.HongWidgetAdvanceOption
import com.codehong.library.widget.HongWidgetCommonOption
import com.codehong.library.widget.rule.HongBorderInfo
import com.codehong.library.widget.rule.HongLayoutParam
import com.codehong.library.widget.rule.HongShadowInfo
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.HongWidgetType
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.radius.HongRadiusInfo
import com.codehong.library.widget.rule.typo.HongTypo
import com.codehong.library.widget.text.HongTextBuilder

data class HongTextButtonOption(
    override val type: HongWidgetType = HongWidgetType.TEXT_BUTTON,
) : HongWidgetAdvanceOption {

    companion object {
        const val DEFAULT_ALL_RADIUS = 0
        const val DEFAULT_TOP_RADIUS = 0
        const val DEFAULT_BOTTOM_RADIUS = 0
        const val DEFAULT_TOP_START_RADIUS = 0
        const val DEFAULT_TOP_END_RADIUS = 0
        const val DEFAULT_BOTTOM_START_RADIUS = 0
        const val DEFAULT_BOTTOM_END_RADIUS = 0

        val DEFAULT_TEXT_TYPO = HongTypo.BODY_16_B
        val DEFAULT_TEXT_COLOR = HongColor.WHITE_100.hex

        const val DEFAULT_USE_SHAPE_CIRCLE = false

        var DEFAULT_TEXT_OPTION = HongTextBuilder()
            .typography(DEFAULT_TEXT_TYPO)
            .color(DEFAULT_TEXT_COLOR)
            .applyOption()
    }

    override var isValidComponent: Boolean = true

    override var width: Int = HongLayoutParam.WRAP_CONTENT.value
    override var height: Int = HongLayoutParam.WRAP_CONTENT.value
    override var margin: HongSpacingInfo = HongSpacingInfo(0f, 0f, 0f, 0f)
    override var padding: HongSpacingInfo = HongSpacingInfo(0f, 0f, 0f, 0f)
    override var click: ((HongWidgetCommonOption) -> Unit)? = null
    override var radius: HongRadiusInfo = HongRadiusInfo(
        all = DEFAULT_ALL_RADIUS,
        top = DEFAULT_TOP_RADIUS,
        bottom = DEFAULT_BOTTOM_RADIUS,
        topLeft = DEFAULT_TOP_START_RADIUS,
        topRight = DEFAULT_TOP_END_RADIUS,
        bottomLeft = DEFAULT_BOTTOM_START_RADIUS,
        bottomRight = DEFAULT_BOTTOM_END_RADIUS
    )
    override var backgroundColor: HongColor = HongColor.WHITE_100
    override var backgroundColorHex: String = HongColor.WHITE_100.hex
    override var border: HongBorderInfo = HongBorderInfo(
        width = 0,
        color = HongColor.WHITE_100.hex
    )
    override var useShapeCircle: Boolean = DEFAULT_USE_SHAPE_CIRCLE

    override var shadow = HongShadowInfo()

    var textOption = DEFAULT_TEXT_OPTION

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as HongTextButtonOption

        if (type != other.type) return false
        if (isValidComponent != other.isValidComponent) return false
        if (width != other.width) return false
        if (height != other.height) return false
        if (margin != other.margin) return false
        if (padding != other.padding) return false
        if (click != other.click) return false
        if (radius != other.radius) return false
        if (backgroundColor != other.backgroundColor) return false
        if (backgroundColorHex != other.backgroundColorHex) return false
        if (border != other.border) return false
        if (useShapeCircle != other.useShapeCircle) return false
        if (shadow != other.shadow) return false
        if (textOption != other.textOption) return false

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
        result = 31 * result + backgroundColor.hashCode()
        result = 31 * result + backgroundColorHex.hashCode()
        result = 31 * result + border.hashCode()
        result = 31 * result + useShapeCircle.hashCode()
        result = 31 * result + shadow.hashCode()
        result = 31 * result + textOption.hashCode()
        return result
    }

    override fun toString(): String {
        return "HongTextButtonOption(" +
                "type=$type, " +
                "isValidComponent=$isValidComponent, " +
                "width=$width, " +
                "height=$height, " +
                "margin=$margin, " +
                "padding=$padding, " +
                "click=$click, " +
                "radius=$radius, " +
                "backgroundColor=$backgroundColor, " +
                "backgroundColorHex='$backgroundColorHex', " +
                "border=$border, " +
                "useShapeCircle=$useShapeCircle, " +
                "shadow=$shadow, " +
                "textOption=$textOption" +
                ")"
    }
}