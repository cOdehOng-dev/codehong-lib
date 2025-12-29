package com.codehong.library.widget.label.select

import android.os.Parcelable
import com.codehong.library.widget.HongWidgetCommonOption
import com.codehong.library.widget.R
import com.codehong.library.widget.rule.HongBorderInfo
import com.codehong.library.widget.rule.HongLayoutParam
import com.codehong.library.widget.rule.HongShadowInfo
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.HongWidgetType
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.keyboard.HongKeyboardActionType
import com.codehong.library.widget.rule.keyboard.HongKeyboardType
import com.codehong.library.widget.rule.radius.HongRadiusInfo
import com.codehong.library.widget.rule.typo.HongTypo
import com.codehong.library.widget.textfield.def.HongTextFieldBuilder
import com.codehong.library.widget.textfield.def.HongTextFieldOption
import kotlinx.parcelize.Parcelize

@Parcelize
data class HongLabelSelectInputOption(
    override val type: HongWidgetType = HongWidgetType.LABEL_SELECT_INPUT
) : HongWidgetCommonOption, Parcelable {

    companion object {
        val DEFAULT_TEXT_FIELD = HongTextFieldBuilder()
            .width(HongLayoutParam.MATCH_PARENT.value)
            .margin(
                HongSpacingInfo(
                    top = 10f
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
            .padding(
                HongSpacingInfo(
                    top = 11f,
                    bottom = 11f,
                    left = 10f,
                    right = 10f
                )
            )
            .backgroundColor(HongColor.BLACK_05)
            .inputTypo(HongTypo.BODY_14)
            .inputColor(HongColor.BLACK_100)
            .placeholderPadding(
                HongSpacingInfo(
                    top = 4f,
                    bottom = 4f,
                    left = 4f,
                    right = 4f
                )
            )
            .placeholderTypo(HongTypo.BODY_14)
            .placeholderColor(HongColor.BLACK_100)
            .clearIconSize(18)
            .clearIconRes(R.drawable.honglib_ic_close)
            .cursorColor(HongColor.MAIN_ORANGE_100)
            .keyboardOption(Pair(HongKeyboardType.TEXT, HongKeyboardActionType.DONE))
            .applyOption()

    }

    override var isValidComponent: Boolean = true

    override var width: Int = HongLayoutParam.MATCH_PARENT.value
    override var height: Int = HongLayoutParam.WRAP_CONTENT.value
    override var margin: HongSpacingInfo = HongSpacingInfo()
    override var padding: HongSpacingInfo = HongSpacingInfo()
    
    override var backgroundColorHex: String = HongColor.WHITE_100.hex
    override var click: ((HongWidgetCommonOption) -> Unit)? = null
    override var radius: HongRadiusInfo = HongRadiusInfo()
    override var shadow: HongShadowInfo = HongShadowInfo()
    override var border: HongBorderInfo = HongBorderInfo()
    override var useShapeCircle: Boolean = false


    var label: String? = null
    var labelColorHex: String = HongColor.BLACK_100.hex
    var labelTypo: HongTypo = HongTypo.BODY_15_B

    var description: String? = null
    var descriptionColorHex: String = HongColor.BLACK_60.hex
    var descriptionTypo: HongTypo = HongTypo.CONTENTS_10


    var input: String? = null
    var placeholder: String? = null

    var textFieldOption: HongTextFieldOption = DEFAULT_TEXT_FIELD


    var buttonText: String? = null
    var buttonTextColorHex: String = HongColor.MAIN_ORANGE_100.hex
    var buttonTextTypo = HongTypo.BODY_15


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
        if (radius != other.radius) return false
        if (shadow != other.shadow) return false
        if (border != other.border) return false
        if (useShapeCircle != other.useShapeCircle) return false
        if (label != other.label) return false
        if (labelColorHex != other.labelColorHex) return false
        if (labelTypo != other.labelTypo) return false
        if (description != other.description) return false
        if (descriptionColorHex != other.descriptionColorHex) return false
        if (descriptionTypo != other.descriptionTypo) return false
        if (input != other.input) return false
        if (placeholder != other.placeholder) return false
        if (textFieldOption != other.textFieldOption) return false
        if (buttonText != other.buttonText) return false
        if (buttonTextColorHex != other.buttonTextColorHex) return false
        if (buttonTextTypo != other.buttonTextTypo) return false
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
        result = 31 * result + radius.hashCode()
        result = 31 * result + shadow.hashCode()
        result = 31 * result + border.hashCode()
        result = 31 * result + useShapeCircle.hashCode()
        result = 31 * result + (label?.hashCode() ?: 0)
        result = 31 * result + labelColorHex.hashCode()
        result = 31 * result + labelTypo.hashCode()
        result = 31 * result + (description?.hashCode() ?: 0)
        result = 31 * result + descriptionColorHex.hashCode()
        result = 31 * result + descriptionTypo.hashCode()
        result = 31 * result + (input?.hashCode() ?: 0)
        result = 31 * result + (placeholder?.hashCode() ?: 0)
        result = 31 * result + textFieldOption.hashCode()
        result = 31 * result + (buttonText?.hashCode() ?: 0)
        result = 31 * result + buttonTextColorHex.hashCode()
        result = 31 * result + buttonTextTypo.hashCode()
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
                "radius=$radius, " +
                "shadow=$shadow, " +
                "border=$border, " +
                "useShapeCircle=$useShapeCircle, " +
                "label=$label, " +
                "labelColorHex='$labelColorHex', " +
                "labelTypo=$labelTypo, " +
                "description=$description, " +
                "descriptionColorHex='$descriptionColorHex', " +
                "descriptionTypo=$descriptionTypo, " +
                "input=$input, " +
                "placeholder=$placeholder, " +
                "textFieldOption=$textFieldOption, " +
                "buttonText=$buttonText, " +
                "buttonTextColorHex='$buttonTextColorHex', " +
                "buttonTextTypo=$buttonTextTypo, " +
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
