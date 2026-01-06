package com.codehong.library.widget.button.select

import com.codehong.library.widget.HongWidgetCommonOption
import com.codehong.library.widget.rule.HongBorderInfo
import com.codehong.library.widget.rule.HongLayoutParam
import com.codehong.library.widget.rule.HongShadowInfo
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.HongWidgetType
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.radius.HongRadiusInfo
import com.codehong.library.widget.rule.typo.HongTypo

data class HongButtonSelectOption(
    override val type: HongWidgetType = HongWidgetType.BUTTON_SELECT
) : HongWidgetCommonOption {

    companion object {
        const val DEFAULT_NEGATIVE_TEXT = "취소"
        const val DEFAULT_POSITIVE_TEXT = "확인"
    }

    override var isValidComponent: Boolean = true
    override var width: Int = HongLayoutParam.MATCH_PARENT.value
    override var height: Int = HongLayoutParam.WRAP_CONTENT.value
    override var margin: HongSpacingInfo = HongSpacingInfo()
    override var padding: HongSpacingInfo = HongSpacingInfo()
    
    override var backgroundColorHex: String = HongColor.WHITE_100.hex
    override var click: ((HongWidgetCommonOption) -> Unit)? = null
    override var border: HongBorderInfo = HongBorderInfo()
    override var useShapeCircle: Boolean = false
    override var shadow = HongShadowInfo()
    override var radius: HongRadiusInfo = HongRadiusInfo()

    var negativeText: String = "취소"
    var negativeTextTypo: HongTypo = HongTypo.BODY_15_B
    var negativeTextColorHex: String = HongColor.MAIN_ORANGE_100.hex
    var negativeBorderColorHex: String = HongColor.MAIN_ORANGE_100.hex

    var positiveText: String = "확인"
    var positiveTextTypo: HongTypo = HongTypo.BODY_15_B
    var positiveTextColorHex: String = HongColor.WHITE_100.hex
    var positiveBackgroundColorHex: String = HongColor.MAIN_ORANGE_100.hex

    var onClickPositive: (() -> Unit)? = null
    var onClickNegative: (() -> Unit)? = null

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as HongButtonSelectOption

        if (type != other.type) return false
        if (isValidComponent != other.isValidComponent) return false
        if (width != other.width) return false
        if (height != other.height) return false
        if (margin != other.margin) return false
        if (padding != other.padding) return false
        if (backgroundColorHex != other.backgroundColorHex) return false
        if (click != other.click) return false
        if (border != other.border) return false
        if (useShapeCircle != other.useShapeCircle) return false
        if (shadow != other.shadow) return false
        if (radius != other.radius) return false
        if (negativeText != other.negativeText) return false
        if (negativeTextTypo != other.negativeTextTypo) return false
        if (negativeTextColorHex != other.negativeTextColorHex) return false
        if (negativeBorderColorHex != other.negativeBorderColorHex) return false
        if (positiveText != other.positiveText) return false
        if (positiveTextTypo != other.positiveTextTypo) return false
        if (positiveTextColorHex != other.positiveTextColorHex) return false
        if (positiveBackgroundColorHex != other.positiveBackgroundColorHex) return false
        if (onClickPositive != other.onClickPositive) return false
        if (onClickNegative != other.onClickNegative) return false

        return true
    }

    override fun hashCode(): Int {
        var result = type.hashCode()
        result = 31 * result + isValidComponent.hashCode()
        result = 31 * result + width
        result = 31 * result + height
        result = 31 * result + margin.hashCode()
        result = 31 * result + padding.hashCode()
        result = 31 * result + backgroundColorHex.hashCode()
        result = 31 * result + (click?.hashCode() ?: 0)
        result = 31 * result + border.hashCode()
        result = 31 * result + useShapeCircle.hashCode()
        result = 31 * result + shadow.hashCode()
        result = 31 * result + radius.hashCode()
        result = 31 * result + negativeText.hashCode()
        result = 31 * result + negativeTextTypo.hashCode()
        result = 31 * result + negativeTextColorHex.hashCode()
        result = 31 * result + negativeBorderColorHex.hashCode()
        result = 31 * result + positiveText.hashCode()
        result = 31 * result + positiveTextTypo.hashCode()
        result = 31 * result + positiveTextColorHex.hashCode()
        result = 31 * result + positiveBackgroundColorHex.hashCode()
        result = 31 * result + (onClickPositive?.hashCode() ?: 0)
        result = 31 * result + (onClickNegative?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String {
        return "HongButtonSelectOption(" +
                "type=$type, " +
                "isValidComponent=$isValidComponent, " +
                "width=$width, " +
                "height=$height, " +
                "margin=$margin, " +
                "padding=$padding, " +
                "backgroundColorHex='$backgroundColorHex', " +
                "click=$click, " +
                "border=$border, " +
                "useShapeCircle=$useShapeCircle, " +
                "shadow=$shadow, " +
                "radius=$radius, " +
                "negativeText='$negativeText', " +
                "negativeTextTypo=$negativeTextTypo, " +
                "negativeTextColorHex='$negativeTextColorHex', " +
                "negativeBorderColorHex='$negativeBorderColorHex', " +
                "positiveText='$positiveText', " +
                "positiveTextTypo=$positiveTextTypo, " +
                "positiveTextColorHex='$positiveTextColorHex', " +
                "positiveBackgroundColorHex='$positiveBackgroundColorHex', " +
                "onClickPositive=$onClickPositive, " +
                "onClickNegative=$onClickNegative" +
                ")"
    }


}