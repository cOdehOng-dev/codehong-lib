package com.codehong.library.widget.text.check

import com.codehong.library.widget.HongWidgetCommonOption
import com.codehong.library.widget.button.text.HongTextButtonOption.Companion.DEFAULT_USE_SHAPE_CIRCLE
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

data class HongCheckTextOption(
    override val type: HongWidgetType = HongWidgetType.CHECK_TEXT,
) : HongWidgetCommonOption {

    companion object {
        val DEFAULT_TEXT_OPTION = HongTextBuilder()
            .width(HongLayoutParam.WRAP_CONTENT.value)
            .height(HongLayoutParam.WRAP_CONTENT.value)
            .typography(HongTypo.BODY_13)
            .color(HongColor.GRAY_70)
            .applyOption()
    }

    override var isValidComponent: Boolean = true
    override var width: Int = HongLayoutParam.WRAP_CONTENT.value
    override var height: Int = HongLayoutParam.WRAP_CONTENT.value
    override var margin: HongSpacingInfo = HongSpacingInfo()
    override var padding: HongSpacingInfo = HongSpacingInfo()
    override var click: ((HongWidgetCommonOption) -> Unit)? = null
    override var radius: HongRadiusInfo = HongRadiusInfo()
    override var backgroundColor: HongColor = HongColor.TRANSPARENT
    override var backgroundColorHex: String = HongColor.TRANSPARENT.hex
    override var border: HongBorderInfo = HongBorderInfo()
    override var useShapeCircle: Boolean = DEFAULT_USE_SHAPE_CIRCLE
    override var shadow = HongShadowInfo()

    var text: String? = null
    var textOption: HongTextOption = DEFAULT_TEXT_OPTION

    var size: Int = 23
    var checkColor: HongColor = HongColor.MAIN_ORANGE_100
    var checkColorHex: String = HongColor.MAIN_ORANGE_100.hex

    var uncheckColor: HongColor = HongColor.GRAY_60
    var uncheckColorHex: String = HongColor.GRAY_60.hex

    var checkState: Boolean = false

    var onCheck: ((Boolean) -> Unit)? = null


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as HongCheckTextOption

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
        if (text != other.text) return false
        if (textOption != other.textOption) return false
        if (size != other.size) return false
        if (checkColor != other.checkColor) return false
        if (checkColorHex != other.checkColorHex) return false
        if (uncheckColor != other.uncheckColor) return false
        if (uncheckColorHex != other.uncheckColorHex) return false
        if (checkState != other.checkState) return false
        if (onCheck != other.onCheck) return false

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
        result = 31 * result + (text?.hashCode() ?: 0)
        result = 31 * result + textOption.hashCode()
        result = 31 * result + size
        result = 31 * result + checkColor.hashCode()
        result = 31 * result + checkColorHex.hashCode()
        result = 31 * result + uncheckColor.hashCode()
        result = 31 * result + uncheckColorHex.hashCode()
        result = 31 * result + checkState.hashCode()
        result = 31 * result + (onCheck?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String {
        return "HongCheckTextOption(" +
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
                "text=$text, " +
                "textOption=$textOption, " +
                "size=$size, " +
                "checkColor=$checkColor, " +
                "checkColorHex='$checkColorHex', " +
                "uncheckColor=$uncheckColor, " +
                "uncheckColorHex='$uncheckColorHex', " +
                "checkState=$checkState, " +
                "onCheck=$onCheck" +
                ")"
    }


}