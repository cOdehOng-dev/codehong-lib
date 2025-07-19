package com.codehong.library.widget.label.checkbox

import com.codehong.library.widget.HongWidgetCommonOption

import com.codehong.library.widget.checkbox.HongCheckboxBuilder
import com.codehong.library.widget.label.HongLabelBuilder
import com.codehong.library.widget.label.HongLabelOption
import com.codehong.library.widget.rule.HongBorderInfo
import com.codehong.library.widget.rule.HongLayoutParam
import com.codehong.library.widget.rule.HongPosition
import com.codehong.library.widget.rule.HongShadowInfo
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.HongWidgetType
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.radius.HongRadiusInfo
import com.codehong.library.widget.rule.typo.HongTypo
import com.codehong.library.widget.text.HongTextBuilder

data class HongLabelCheckboxOption(
    override val type: HongWidgetType = HongWidgetType.LABEL_CHECKBOX
) : HongWidgetCommonOption {

    companion object {
        val DEFAULT_BETTWEN_SPACER = 10
        val DEFAULT_LABEL_OPTION = HongTextBuilder()
            .width(HongLayoutParam.WRAP_CONTENT.value)
            .typography(HongTypo.BODY_15_B)
            .color(HongColor.BLACK_100)
            .applyOption()

        val DEFAULT_DESCRIPTION_OPTION = HongTextBuilder()
            .width(HongLayoutParam.WRAP_CONTENT.value)
            .typography(HongTypo.CONTENTS_10)
            .margin(
                HongSpacingInfo(
                    top = 2f
                )
            )
            .color("#333333")
            .applyOption()

        val DEFAULT_LABEL_VIEW_OPTION = HongLabelBuilder()
            .width(HongLayoutParam.WRAP_CONTENT.value)
            .backgroundColor(HongColor.TRANSPARENT)
            .labelTextOption(DEFAULT_LABEL_OPTION)
            .descriptionTextOption(DEFAULT_DESCRIPTION_OPTION)
            .applyOption()

        val DEFAULT_CHECKBOX_SIZE = 22
        val DEFAULT_CHECKBOX_OPTION = HongCheckboxBuilder()
            .size(DEFAULT_CHECKBOX_SIZE)
            .backgroundColor(HongColor.TRANSPARENT)
            .checkedColor(HongColor.MAIN_ORANGE_100)
            .checkmarkColor(HongColor.WHITE_100)
            .border(
                HongBorderInfo(
                    width = 2,
                    color = HongColor.GRAY_20.hex
                )
            )
            .radius(
                HongRadiusInfo(
                    topLeft = 4,
                    topRight = 4,
                    bottomLeft = 4,
                    bottomRight = 4
                )
            )
            .applyOption()
    }

    override var isValidComponent: Boolean = true

    override var width: Int = HongLayoutParam.WRAP_CONTENT.value
    override var height: Int = HongLayoutParam.WRAP_CONTENT.value
    override var margin: HongSpacingInfo = HongSpacingInfo(0f, 0f, 0f, 0f)
    override var padding: HongSpacingInfo = HongSpacingInfo(0f, 0f, 0f, 0f)
    override var click: ((HongWidgetCommonOption) -> Unit)? = null
    override var radius: HongRadiusInfo = HongRadiusInfo()
    override var border: HongBorderInfo = HongBorderInfo()
    override var useShapeCircle: Boolean = false
    override var shadow = HongShadowInfo()

    override var backgroundColor: HongColor = HongColor.TRANSPARENT
    override var backgroundColorHex: String = HongColor.TRANSPARENT.hex


    var label: String? = null
    var labelTextOption = DEFAULT_LABEL_OPTION
    var description: String? = null
    var descriptionTextOption = DEFAULT_DESCRIPTION_OPTION

    var labelOption: HongLabelOption = DEFAULT_LABEL_VIEW_OPTION

    var checkboxOption = DEFAULT_CHECKBOX_OPTION

    var checkboxSize: Int? = null
    var isChecked: Boolean? = null

    var checkboxPosition: HongPosition = HongPosition.LEFT


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as HongLabelCheckboxOption

        if (type != other.type) return false
        if (isValidComponent != other.isValidComponent) return false
        if (width != other.width) return false
        if (height != other.height) return false
        if (margin != other.margin) return false
        if (padding != other.padding) return false
        if (click != other.click) return false
        if (radius != other.radius) return false
        if (border != other.border) return false
        if (useShapeCircle != other.useShapeCircle) return false
        if (shadow != other.shadow) return false
        if (backgroundColor != other.backgroundColor) return false
        if (backgroundColorHex != other.backgroundColorHex) return false
        if (label != other.label) return false
        if (labelTextOption != other.labelTextOption) return false
        if (description != other.description) return false
        if (descriptionTextOption != other.descriptionTextOption) return false
        if (labelOption != other.labelOption) return false
        if (checkboxOption != other.checkboxOption) return false
        if (checkboxSize != other.checkboxSize) return false
        if (isChecked != other.isChecked) return false
        if (checkboxPosition != other.checkboxPosition) return false

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
        result = 31 * result + border.hashCode()
        result = 31 * result + useShapeCircle.hashCode()
        result = 31 * result + shadow.hashCode()
        result = 31 * result + backgroundColor.hashCode()
        result = 31 * result + backgroundColorHex.hashCode()
        result = 31 * result + (label?.hashCode() ?: 0)
        result = 31 * result + labelTextOption.hashCode()
        result = 31 * result + (description?.hashCode() ?: 0)
        result = 31 * result + descriptionTextOption.hashCode()
        result = 31 * result + labelOption.hashCode()
        result = 31 * result + checkboxOption.hashCode()
        result = 31 * result + (checkboxSize ?: 0)
        result = 31 * result + (isChecked?.hashCode() ?: 0)
        result = 31 * result + checkboxPosition.hashCode()
        return result
    }

    override fun toString(): String {
        return "HongLabelCheckboxOption(" +
                "type=$type, " +
                "isValidComponent=$isValidComponent, " +
                "width=$width, " +
                "height=$height, " +
                "margin=$margin, " +
                "padding=$padding, " +
                "click=$click, " +
                "radius=$radius, " +
                "border=$border, " +
                "useShapeCircle=$useShapeCircle, " +
                "shadow=$shadow, " +
                "backgroundColor=$backgroundColor, " +
                "backgroundColorHex='$backgroundColorHex', " +
                "label=$label, " +
                "labelTextOption=$labelTextOption, " +
                "description=$description, " +
                "descriptionTextOption=$descriptionTextOption, " +
                "labelOption=$labelOption, " +
                "checkboxOption=$checkboxOption, " +
                "checkboxSize=$checkboxSize, " +
                "isChecked=$isChecked, " +
                "checkboxPosition=$checkboxPosition" +
                ")"
    }


}