package com.codehong.library.widget.button.select

import com.codehong.library.widget.HongWidgetAdvanceOption
import com.codehong.library.widget.HongWidgetCommonOption
import com.codehong.library.widget.button.text.HongTextButtonBuilder
import com.codehong.library.widget.button.text.HongTextButtonOption
import com.codehong.library.widget.rule.HongBorderInfo
import com.codehong.library.widget.rule.HongLayoutParam
import com.codehong.library.widget.rule.HongShadowInfo
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.HongWidgetType
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.radius.HongRadiusInfo
import com.codehong.library.widget.rule.typo.HongTypo
import com.codehong.library.widget.text.HongTextBuilder

data class HongSelectButtonOption(
    override val type: HongWidgetType = HongWidgetType.SELECT_BUTTON
) : HongWidgetAdvanceOption {

    companion object {
        val DEFAULT_NEGATIVE_TEXT_BUTTON_OPTION = HongTextButtonBuilder()
            .width(HongLayoutParam.MATCH_PARENT.value)
            .height(48)
            .padding(
                HongSpacingInfo(
                    top = 8f,
                    bottom = 8f
                )
            )
            .radius(
                HongRadiusInfo(
                    topLeft = 10,
                    topRight = 10,
                    bottomLeft = 10,
                    bottomRight = 10
                )
            )
            .border(
                HongBorderInfo(
                    width = 1,
                    color = HongColor.MAIN_ORANGE_100.hex
                )
            )
            .textOption(
                HongTextBuilder()
                    .text("취소")
                    .typography(HongTypo.BODY_15_B)
                    .color(HongColor.MAIN_ORANGE_100.hex)
                    .applyOption()
            )
            .backgroundColor(HongColor.WHITE_100.hex)
            .applyOption()

        val DEFAULT_POSITIVE_TEXT_BUTTON_OPTION = HongTextButtonBuilder()
            .width(HongLayoutParam.MATCH_PARENT.value)
            .height(48)
            .padding(
                HongSpacingInfo(
                    top = 8f,
                    bottom = 8f
                )
            )
            .radius(
                HongRadiusInfo(
                    topLeft = 10,
                    topRight = 10,
                    bottomLeft = 10,
                    bottomRight = 10
                )
            )
            .textOption(
                HongTextBuilder()
                    .text("확인")
                    .typography(HongTypo.BODY_15_B)
                    .color(HongColor.WHITE_100.hex)
                    .applyOption()
            )
            .backgroundColor(HongColor.MAIN_ORANGE_100)
            .applyOption()
    }

    override var isValidComponent: Boolean = true
    override var width: Int = HongLayoutParam.MATCH_PARENT.value
    override var height: Int = HongLayoutParam.WRAP_CONTENT.value
    override var margin: HongSpacingInfo = HongSpacingInfo(0f, 0f, 0f, 0f)
    override var padding: HongSpacingInfo = HongSpacingInfo(0f, 0f, 0f, 0f)
    override var backgroundColor: HongColor = HongColor.WHITE_100
    override var backgroundColorHex: String = HongColor.WHITE_100.hex
    override var click: ((HongWidgetCommonOption) -> Unit)? = null
    override var border: HongBorderInfo = HongBorderInfo()
    override var useShapeCircle: Boolean = false
    override var shadow = HongShadowInfo()
    override var radius: HongRadiusInfo = HongRadiusInfo()

    var negativeTextButtonOption: HongTextButtonOption = DEFAULT_NEGATIVE_TEXT_BUTTON_OPTION
    var positiveTextButtonOption: HongTextButtonOption = DEFAULT_POSITIVE_TEXT_BUTTON_OPTION

    var positiveClick: (() -> Unit)? = null
    var negativeClick: (() -> Unit)? = null


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as HongSelectButtonOption

        if (type != other.type) return false
        if (isValidComponent != other.isValidComponent) return false
        if (width != other.width) return false
        if (height != other.height) return false
        if (margin != other.margin) return false
        if (padding != other.padding) return false
        if (backgroundColor != other.backgroundColor) return false
        if (backgroundColorHex != other.backgroundColorHex) return false
        if (click != other.click) return false
        if (border != other.border) return false
        if (useShapeCircle != other.useShapeCircle) return false
        if (shadow != other.shadow) return false
        if (radius != other.radius) return false
        if (negativeTextButtonOption != other.negativeTextButtonOption) return false
        if (positiveTextButtonOption != other.positiveTextButtonOption) return false
        if (positiveClick != other.positiveClick) return false
        if (negativeClick != other.negativeClick) return false

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
        result = 31 * result + border.hashCode()
        result = 31 * result + useShapeCircle.hashCode()
        result = 31 * result + shadow.hashCode()
        result = 31 * result + radius.hashCode()
        result = 31 * result + negativeTextButtonOption.hashCode()
        result = 31 * result + positiveTextButtonOption.hashCode()
        result = 31 * result + (positiveClick?.hashCode() ?: 0)
        result = 31 * result + (negativeClick?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String {
        return "HongSelectButtonOption(" +
                "type=$type, " +
                "isValidComponent=$isValidComponent, " +
                "width=$width, " +
                "height=$height, " +
                "margin=$margin, " +
                "padding=$padding, " +
                "backgroundColor=$backgroundColor, " +
                "backgroundColorHex='$backgroundColorHex', " +
                "click=$click, " +
                "border=$border, " +
                "useShapeCircle=$useShapeCircle, " +
                "shadow=$shadow, " +
                "radius=$radius, " +
                "negativeTextButtonOption=$negativeTextButtonOption, " +
                "positiveTextButtonOption=$positiveTextButtonOption, " +
                "positiveClick=$positiveClick, " +
                "negativeClick=$negativeClick" +
                ")"
    }
}