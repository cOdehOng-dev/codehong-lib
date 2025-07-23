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

data class HongTextBadgeOption(
    override val type: HongWidgetType = HongWidgetType.TEXT_BADGE
) : HongWidgetCommonOption {

    override var isValidComponent: Boolean = true
    override var width: Int = HongLayoutParam.WRAP_CONTENT.value
    override var height: Int = HongLayoutParam.WRAP_CONTENT.value
    override var margin: HongSpacingInfo = HongSpacingInfo()
    override var padding: HongSpacingInfo = HongSpacingInfo()
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
    var textColorHex: String? = null
    var textTypography: HongTypo = HongTypo.CONTENTS_12_B

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
        if (useShapeCircle != other.useShapeCircle) return false
        if (radius != other.radius) return false
        if (backgroundColorHex != other.backgroundColorHex) return false
        if (border != other.border) return false
        if (shadow != other.shadow) return false
        if (text != other.text) return false
        if (textColorHex != other.textColorHex) return false
        if (textTypography != other.textTypography) return false

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
        result = 31 * result + useShapeCircle.hashCode()
        result = 31 * result + radius.hashCode()
        result = 31 * result + backgroundColorHex.hashCode()
        result = 31 * result + border.hashCode()
        result = 31 * result + shadow.hashCode()
        result = 31 * result + (text?.hashCode() ?: 0)
        result = 31 * result + (textColorHex?.hashCode() ?: 0)
        result = 31 * result + textTypography.hashCode()
        return result
    }

    override fun toString(): String {
        return "HongTextBadgeOption(" +
                "type=$type, " +
                "isValidComponent=$isValidComponent, " +
                "width=$width, " +
                "height=$height, " +
                "margin=$margin, " +
                "padding=$padding, " +
                "click=$click, " +
                "useShapeCircle=$useShapeCircle, " +
                "radius=$radius, " +
                "backgroundColorHex='$backgroundColorHex', " +
                "border=$border, " +
                "shadow=$shadow, " +
                "text=$text, " +
                "textColorHex=$textColorHex, " +
                "textTypography=$textTypography" +
                ")"
    }


}