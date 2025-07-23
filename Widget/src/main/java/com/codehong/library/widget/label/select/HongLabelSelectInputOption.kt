package com.codehong.library.widget.label.select

import android.os.Parcelable
import com.codehong.library.widget.HongWidgetCommonOption
import com.codehong.library.widget.R
import com.codehong.library.widget.button.text.HongTextButtonBuilder
import com.codehong.library.widget.button.text.HongTextButtonOption
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
import com.codehong.library.widget.text.HongTextOption
import com.codehong.library.widget.textfield.HongTextFieldBuilder
import com.codehong.library.widget.textfield.HongTextFieldOption
import kotlinx.parcelize.Parcelize

@Parcelize
data class HongLabelSelectInputOption(
    override val type: HongWidgetType = HongWidgetType.LABEL_SELECT_INPUT
) : HongWidgetCommonOption, Parcelable {

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
            .backgroundColor(HongColor.TRANSPARENT)
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
            .backgroundColor(HongColor.BLACK_05)
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

        val DEFAULT_BUTTON_TEXT = HongTextBuilder()
            .typography(HongTypo.BODY_15)
            .color(HongColor.MAIN_ORANGE_100)
            .applyOption()

        val DEFAULT_TEXT_BUTTON = HongTextButtonBuilder()
            .width(HongLayoutParam.MATCH_PARENT.value)
            .height(48)
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
                    top = 14f,
                    bottom = 14f
                )
            )
            .margin(
                HongSpacingInfo(
                    top = 10f,
                )
            )
            .textOption(DEFAULT_BUTTON_TEXT)
            .border(
                HongBorderInfo(
                    width = 1,
                    color = HongColor.MAIN_ORANGE_100.hex
                )
            )
            .backgroundColor(HongColor.WHITE_100)
            .applyOption()
    }

    override var isValidComponent: Boolean = true

    override var width: Int = HongLayoutParam.MATCH_PARENT.value
    override var height: Int = HongLayoutParam.WRAP_CONTENT.value
    override var margin: HongSpacingInfo = HongSpacingInfo(0f, 0f, 0f, 0f)
    override var padding: HongSpacingInfo = HongSpacingInfo(0f, 0f, 0f, 0f)
    
    override var backgroundColorHex: String = HongColor.WHITE_100.hex
    override var click: ((HongWidgetCommonOption) -> Unit)? = null
    override var radius: HongRadiusInfo = HongRadiusInfo()
    override var shadow: HongShadowInfo = HongShadowInfo()
    override var border: HongBorderInfo = HongBorderInfo()
    override var useShapeCircle: Boolean = false


    var label: String? = null
    var description: String? = null
    var labelOption: HongLabelOption = DEFAULT_LABEL_VIEW_OPTION


    var input: String? = null
    var placeholder: String? = null
    var textFieldOption: HongTextFieldOption = DEFAULT_TEXT_FIELD

    var buttonText: String? = null
    var buttonTextOption: HongTextOption = DEFAULT_BUTTON_TEXT
    var textButtonOption: HongTextButtonOption = DEFAULT_TEXT_BUTTON

    var selectPosition: Int = 0
    var selectList: List<String> = emptyList()

    var useOnlyNumber: Boolean = false

    var useDirectCallback: Boolean = false

    var showInput: Boolean = false

    var pickerCallback: ((String, Int) -> Unit)? = null
    var inputCallback: ((String?) -> Unit)? = null


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as HongLabelSelectInputOption

        if (type != other.type) return false
        if (isValidComponent != other.isValidComponent) return false
        if (width != other.width) return false
        if (height != other.height) return false
        if (margin != other.margin) return false
        if (padding != other.padding) return false
        if (backgroundColorHex != other.backgroundColorHex) return false
        if (click != other.click) return false
        if (label != other.label) return false
        if (description != other.description) return false
        if (labelOption != other.labelOption) return false
        if (input != other.input) return false
        if (placeholder != other.placeholder) return false
        if (textFieldOption != other.textFieldOption) return false
        if (buttonText != other.buttonText) return false
        if (buttonTextOption != other.buttonTextOption) return false
        if (textButtonOption != other.textButtonOption) return false
        if (selectPosition != other.selectPosition) return false
        if (selectList != other.selectList) return false
        if (useOnlyNumber != other.useOnlyNumber) return false
        if (useDirectCallback != other.useDirectCallback) return false
        if (showInput != other.showInput) return false
        if (pickerCallback != other.pickerCallback) return false
        if (inputCallback != other.inputCallback) return false

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
        result = 31 * result + (label?.hashCode() ?: 0)
        result = 31 * result + (description?.hashCode() ?: 0)
        result = 31 * result + labelOption.hashCode()
        result = 31 * result + (input?.hashCode() ?: 0)
        result = 31 * result + (placeholder?.hashCode() ?: 0)
        result = 31 * result + textFieldOption.hashCode()
        result = 31 * result + (buttonText?.hashCode() ?: 0)
        result = 31 * result + buttonTextOption.hashCode()
        result = 31 * result + textButtonOption.hashCode()
        result = 31 * result + selectPosition
        result = 31 * result + selectList.hashCode()
        result = 31 * result + useOnlyNumber.hashCode()
        result = 31 * result + useDirectCallback.hashCode()
        result = 31 * result + showInput.hashCode()
        result = 31 * result + (pickerCallback?.hashCode() ?: 0)
        result = 31 * result + (inputCallback?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String {
        return "HongLabelSelectInputOption(" +
                "type=$type, " +
                "isValidComponent=$isValidComponent, " +
                "width=$width, " +
                "height=$height, " +
                "margin=$margin, " +
                "padding=$padding, " +
                "backgroundColorHex='$backgroundColorHex', " +
                "click=$click, " +
                "label=$label, " +
                "description=$description, " +
                "labelOption=$labelOption, " +
                "input=$input, " +
                "placeholder=$placeholder, " +
                "textFieldOption=$textFieldOption, " +
                "buttonText=$buttonText, " +
                "buttonTextOption=$buttonTextOption, " +
                "textButtonOption=$textButtonOption, " +
                "selectPosition=$selectPosition, " +
                "selectList=$selectList, " +
                "useOnlyNumber=$useOnlyNumber, " +
                "useDirectCallback=$useDirectCallback, " +
                "showInput=$showInput, " +
                "pickerCallback=$pickerCallback, " +
                "inputCallback=$inputCallback" +
                ")"
    }
}
