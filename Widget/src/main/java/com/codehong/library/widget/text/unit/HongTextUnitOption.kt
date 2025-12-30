package com.codehong.library.widget.text.unit

import com.codehong.library.widget.HongWidgetCommonOption
import com.codehong.library.widget.rule.HongBorderInfo
import com.codehong.library.widget.rule.HongLayoutParam
import com.codehong.library.widget.rule.HongShadowInfo
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.HongWidgetType
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.radius.HongRadiusInfo
import com.codehong.library.widget.rule.typo.HongTypo
import com.codehong.library.widget.text.def.HongTextBuilder
import com.codehong.library.widget.text.def.HongTextOption

data class HongTextUnitOption(
    override val type: HongWidgetType = HongWidgetType.TEXT
) : HongWidgetCommonOption {

    companion object {
        val DEFAULT_TEXT_OPTION = HongTextBuilder()
            .typography(HongTypo.BODY_16)
            .color(HongColor.BLACK_100)
            .applyOption()
    }

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

    var text: String? = null
    var textOption: HongTextOption = DEFAULT_TEXT_OPTION

    var unitText: String? = null
    var unitTextOption: HongTextOption = DEFAULT_TEXT_OPTION

    var useNumberDecimal = false

    var useUnit = true

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as HongTextUnitOption

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
        if (text != other.text) return false
        if (textOption != other.textOption) return false
        if (unitText != other.unitText) return false
        if (unitTextOption != other.unitTextOption) return false
        if (useNumberDecimal != other.useNumberDecimal) return false
        if (useUnit != other.useUnit) return false

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
        result = 31 * result + (text?.hashCode() ?: 0)
        result = 31 * result + textOption.hashCode()
        result = 31 * result + (unitText?.hashCode() ?: 0)
        result = 31 * result + unitTextOption.hashCode()
        result = 31 * result + useNumberDecimal.hashCode()
        result = 31 * result + useUnit.hashCode()
        return result
    }

    override fun toString(): String {
        return "HongTextUnitOption(" +
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
                "text=$text, " +
                "textOption=$textOption, " +
                "unitText=$unitText, " +
                "unitTextOption=$unitTextOption, " +
                "useNumberDecimal=$useNumberDecimal, " +
                "useUnit=$useUnit" +
                ")"
    }


}