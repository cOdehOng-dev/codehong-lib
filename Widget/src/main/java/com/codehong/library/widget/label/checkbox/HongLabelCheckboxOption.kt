package com.codehong.library.widget.label.checkbox

import com.codehong.library.widget.HongWidgetCommonOption
import com.codehong.library.widget.checkbox.HongCheckboxBuilder
import com.codehong.library.widget.rule.HongBorderInfo
import com.codehong.library.widget.rule.HongLayoutParam
import com.codehong.library.widget.rule.HongPosition
import com.codehong.library.widget.rule.HongShadowInfo
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.HongWidgetType
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.radius.HongRadiusInfo
import com.codehong.library.widget.rule.typo.HongTypo

data class HongLabelCheckboxOption(
    override val type: HongWidgetType = HongWidgetType.LABEL_CHECKBOX
) : HongWidgetCommonOption {

    companion object {
        const val DEFAULT_BETWEEN_SPACER = 10

        val DEFAULT_CHECKBOX_OPTION = HongCheckboxBuilder()
            .size(22)
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

    override var backgroundColorHex: String = HongColor.TRANSPARENT.hex


    var label: String? = null
    var labelColorHex: String = HongColor.BLACK_100.hex
    var labelTypo: HongTypo = HongTypo.BODY_15_B

    var description: String? = null
    var descriptionColorHex: String = HongColor.BLACK_60.hex
    var descriptionTypo: HongTypo = HongTypo.CONTENTS_10

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
        if (backgroundColorHex != other.backgroundColorHex) return false
        if (label != other.label) return false
        if (labelColorHex != other.labelColorHex) return false
        if (labelTypo != other.labelTypo) return false
        if (description != other.description) return false
        if (descriptionColorHex != other.descriptionColorHex) return false
        if (descriptionTypo != other.descriptionTypo) return false
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
        result = 31 * result + backgroundColorHex.hashCode()
        result = 31 * result + (label?.hashCode() ?: 0)
        result = 31 * result + labelColorHex.hashCode()
        result = 31 * result + labelTypo.hashCode()
        result = 31 * result + (description?.hashCode() ?: 0)
        result = 31 * result + descriptionColorHex.hashCode()
        result = 31 * result + descriptionTypo.hashCode()
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
                "backgroundColorHex='$backgroundColorHex', " +
                "label=$label, " +
                "labelColorHex='$labelColorHex', " +
                "labelTypo=$labelTypo, " +
                "description=$description, " +
                "descriptionColorHex='$descriptionColorHex', " +
                "descriptionTypo=$descriptionTypo, " +
                "checkboxOption=$checkboxOption, " +
                "checkboxSize=$checkboxSize, " +
                "isChecked=$isChecked, " +
                "checkboxPosition=$checkboxPosition" +
                ")"
    }
}