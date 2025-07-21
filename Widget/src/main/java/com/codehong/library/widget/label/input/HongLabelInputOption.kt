package com.codehong.library.widget.label.input

import com.codehong.library.widget.HongWidgetCommonOption

import com.codehong.library.widget.R
import com.codehong.library.widget.image.HongImageBuilder
import com.codehong.library.widget.label.HongLabelBuilder
import com.codehong.library.widget.label.HongLabelOption
import com.codehong.library.widget.rule.HongBorderInfo
import com.codehong.library.widget.rule.HongLayoutParam
import com.codehong.library.widget.rule.HongScaleType
import com.codehong.library.widget.rule.HongShadowInfo
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.HongWidgetType
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.keyboard.HongKeyboardActionType
import com.codehong.library.widget.rule.keyboard.HongKeyboardType
import com.codehong.library.widget.rule.radius.HongRadiusInfo
import com.codehong.library.widget.rule.typo.HongTypo
import com.codehong.library.widget.text.HongTextBuilder
import com.codehong.library.widget.textfield.HongTextFieldBuilder
import com.codehong.library.widget.textfield.HongTextFieldOption

data class HongLabelInputOption(
    override val type: HongWidgetType = HongWidgetType.LABEL_INPUT
)  : HongWidgetCommonOption {

    companion object {
        val DEFAULT_LABEL_OPTION = HongTextBuilder()
            .width(HongLayoutParam.MATCH_PARENT.value)
            .typography(HongTypo.BODY_15_B)
            .color(HongColor.BLACK_100)
            .applyOption()

        val DEFAULT_DESCRIPTION_OPTION = HongTextBuilder()
            .width(HongLayoutParam.MATCH_PARENT.value)
            .typography(HongTypo.CONTENTS_10)
            .margin(
                HongSpacingInfo(
                    top = 2f
                )
            )
            .color("#333333")
            .applyOption()

        val DEFAULT_LABEL_VIEW_OPTION = HongLabelBuilder()
            .width(HongLayoutParam.MATCH_PARENT.value)
            .labelTextOption(DEFAULT_LABEL_OPTION)
            .descriptionTextOption(DEFAULT_DESCRIPTION_OPTION)
            .applyOption()

        val DEFAULT_TEXT_FIELD = HongTextFieldBuilder()
            .width(HongLayoutParam.MATCH_PARENT.value)
            .radius(
                HongRadiusInfo(
                    topLeft = 10,
                    topRight = 10,
                    bottomLeft = 10,
                    bottomRight = 10
                )
            )
            .padding(
                HongSpacingInfo(
                    top = 11f,
                    bottom = 11f,
                    left = 10f,
                    right = 10f
                )
            )
            .backgroundColor(HongColor.BLACK_5)
            .inputTextOption(
                HongTextBuilder()
                    .backgroundColor(HongColor.TRANSPARENT)
                    .padding(
                        HongSpacingInfo(
                            top = 4f,
                            bottom = 4f,
                            left = 4f,
                            right = 4f
                        )
                    )
                    .typography(HongTypo.BODY_14)
                    .color(HongColor.BLACK_100)
                    .applyOption()
            )
            .placeholderTextOption(
                HongTextBuilder()
                    .copy(HongTextFieldOption.DEFAULT_PLACEHOLDER)
                    .padding(
                        HongSpacingInfo(
                            top = 4f,
                            bottom = 4f,
                            left = 4f,
                            right = 4f
                        )
                    )
                    .typography(HongTypo.BODY_14)
                    .color(HongColor.BLACK_100)
                    .applyOption()
            )
            .clearImageOption(
                HongImageBuilder()
                    .width(18)
                    .height(18)
                    .scaleType(HongScaleType.CENTER_CROP)
                    .drawableResId(R.drawable.honglib_ic_close)
                    .applyOption()
            )
            .cursorColor(HongColor.MAIN_ORANGE_100)
            .keyboardOption(Pair(HongKeyboardType.TEXT, HongKeyboardActionType.DONE))
            .applyOption()
    }

    override var isValidComponent: Boolean = true
    override var width: Int = HongLayoutParam.WRAP_CONTENT.value
    override var height: Int = HongLayoutParam.WRAP_CONTENT.value
    override var margin: HongSpacingInfo = HongSpacingInfo(0f, 0f, 0f, 0f)
    override var padding: HongSpacingInfo = HongSpacingInfo(0f, 0f, 0f, 0f)
    override var click: ((HongWidgetCommonOption) -> Unit)? = null
    override var useShapeCircle: Boolean = false
    override var shadow: HongShadowInfo = HongShadowInfo()
    override var border: HongBorderInfo = HongBorderInfo()
    override var radius: HongRadiusInfo = HongRadiusInfo()

    override var backgroundColorHex: String = HongColor.TRANSPARENT.hex

    var label: String? = null
    var labelTextOption = DEFAULT_LABEL_OPTION

    var description: String? = null
    var descriptionTextOption = DEFAULT_DESCRIPTION_OPTION

    var labelOption: HongLabelOption = DEFAULT_LABEL_VIEW_OPTION

    var textFieldOption: HongTextFieldOption = DEFAULT_TEXT_FIELD

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as HongLabelInputOption

        if (type != other.type) return false
        if (isValidComponent != other.isValidComponent) return false
        if (width != other.width) return false
        if (height != other.height) return false
        if (margin != other.margin) return false
        if (padding != other.padding) return false
        if (click != other.click) return false
        if (backgroundColorHex != other.backgroundColorHex) return false
        if (labelTextOption != other.labelTextOption) return false
        if (descriptionTextOption != other.descriptionTextOption) return false
        if (labelOption != other.labelOption) return false
        if (label != other.label) return false
        if (description != other.description) return false
        if (textFieldOption != other.textFieldOption) return false

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
        result = 31 * result + labelTextOption.hashCode()
        result = 31 * result + descriptionTextOption.hashCode()
        result = 31 * result + labelOption.hashCode()
        result = 31 * result + (label?.hashCode() ?: 0)
        result = 31 * result + (description?.hashCode() ?: 0)
        result = 31 * result + textFieldOption.hashCode()
        return result
    }

    override fun toString(): String {
        return "HongLabelInputOption(" +
                "type=$type, " +
                "isValidComponent=$isValidComponent, " +
                "width=$width, " +
                "height=$height, " +
                "margin=$margin, " +
                "padding=$padding, " +
                "click=$click, " +
                "backgroundColorHex='$backgroundColorHex', " +
                "labelTextOption=$labelTextOption, " +
                "descriptionTextOption=$descriptionTextOption, " +
                "labelOption=$labelOption, " +
                "label=$label, " +
                "description=$description, " +
                "textFieldOption=$textFieldOption" +
                ")"
    }
}
