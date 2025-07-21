package com.codehong.library.widget.text.badge

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
import com.codehong.library.widget.text.HongTextOption

data class HongTextBadgeOption(
    override val type: HongWidgetType = HongWidgetType.TEXT_BADGE
) : HongWidgetCommonOption {

    companion object {
        val DEFAULT_TEXT_OPTION = HongTextBuilder()
            .color(HongColor.MAIN_ORANGE_100)
            .typography(HongTypo.CONTENTS_12_B)
            .applyOption()
    }

    override var isValidComponent: Boolean = true
    override var width: Int = HongLayoutParam.WRAP_CONTENT.value
    override var height: Int = HongLayoutParam.WRAP_CONTENT.value
    override var margin: HongSpacingInfo = HongSpacingInfo(0f, 0f, 0f, 0f)
    override var padding: HongSpacingInfo = HongSpacingInfo(0f, 0f, 0f, 0f)
    override var click: ((HongWidgetCommonOption) -> Unit)? = null
    override var useShapeCircle: Boolean = false

    override var radius: HongRadiusInfo = HongRadiusInfo()

    override var backgroundColorHex: String = HongColor.WHITE_100.hex

    override var border: HongBorderInfo = HongBorderInfo(
        width = 1,
        color = HongColor.WHITE_100.hex
    )

    override var shadow = HongShadowInfo()

    var text: String? = null
    var textOption: HongTextOption = DEFAULT_TEXT_OPTION

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as HongTextBadgeOption

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
        result = 31 * result + backgroundColorHex.hashCode()
        result = 31 * result + border.hashCode()
        result = 31 * result + useShapeCircle.hashCode()
        result = 31 * result + shadow.hashCode()
        result = 31 * result + textOption.hashCode()
        return result
    }

    override fun toString(): String {
        return "HongBadgeTextOption(" +
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
                "useShapeCircle=$useShapeCircle, " +
                "shadow=$shadow, " +
                "textOption=$textOption" +
                ")"
    }
}