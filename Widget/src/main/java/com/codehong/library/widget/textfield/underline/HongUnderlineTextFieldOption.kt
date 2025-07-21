package com.codehong.library.widget.textfield.underline

import com.codehong.library.widget.HongWidgetCommonOption
import com.codehong.library.widget.R
import com.codehong.library.widget.image.HongImageBuilder
import com.codehong.library.widget.image.HongImageOption
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
import com.codehong.library.widget.rule.typo.fontType
import com.codehong.library.widget.rule.typo.size
import com.codehong.library.widget.text.HongTextBuilder

data class HongUnderlineTextFieldOption(
    override val type: HongWidgetType = HongWidgetType.UNDERLINE_TEXT_FIELD
) : HongWidgetCommonOption {

    companion object {
        val DEFAULT_PLACEHOLDER_TYPO = HongTypo.BODY_16
        val DEFAULT_PLACEHOLDER_FONT = DEFAULT_PLACEHOLDER_TYPO.fontType().font

        val DEFAULT_INPUT_TYPO = HongTypo.BODY_16_B
        val DEFAULT_INPUT_FONT = DEFAULT_INPUT_TYPO.fontType().font
        val DEFAULT_INPUT_SIZE = DEFAULT_INPUT_TYPO.size()
        val DEFAULT_INPUT_COLOR = HongColor.BLACK_100.hex

        val DEFAULT_KEYBOARD_OPTION = Pair(
            HongKeyboardType.NUMBER,
            HongKeyboardActionType.DONE
        )

        val DEFAULT_PLACEHOLDER = HongTextBuilder()
            .width(HongLayoutParam.MATCH_PARENT.value)
            .typography(DEFAULT_PLACEHOLDER_TYPO)
            .color(HongColor.BLACK_30)
            .applyOption()

        val DEFAULT_INPUT = HongTextBuilder()
            .width(HongLayoutParam.MATCH_PARENT.value)
            .typography(DEFAULT_INPUT_TYPO)
            .color(DEFAULT_INPUT_COLOR)
            .applyOption()

        val DEFAULT_CLEAR_IMAGE = HongImageBuilder()
            .width(20)
            .height(20)
            .margin(
                HongSpacingInfo(
                    left = 8f,
                    right = 5f
                )
            )
            .drawableResId(R.drawable.honglib_ic_close)
            .applyOption()
    }


    override var isValidComponent: Boolean = true

    override var width: Int = HongLayoutParam.WRAP_CONTENT.value
    override var height: Int = HongLayoutParam.WRAP_CONTENT.value
    override var margin: HongSpacingInfo = HongSpacingInfo(0f, 0f, 0f, 0f)
    override var padding: HongSpacingInfo = HongSpacingInfo(0f, 0f, 0f, 0f)
    override var click: ((HongWidgetCommonOption) -> Unit)? = null

    override var backgroundColor: HongColor = HongColor.TRANSPARENT
    override var backgroundColorHex: String = HongColor.TRANSPARENT.hex

    override var radius: HongRadiusInfo = HongRadiusInfo()
    override var shadow: HongShadowInfo = HongShadowInfo()
    override var border: HongBorderInfo = HongBorderInfo()
    override var useShapeCircle: Boolean = false

    var clearImageOption: HongImageOption? = null

    var input: String? = null
    var placeholder: String? = null
    var placeholderTextOption = DEFAULT_PLACEHOLDER
    var inputTextOption = DEFAULT_INPUT

    var cursorColor: String = HongColor.MAIN_ORANGE_100.hex
    var useHideKeyboard: Boolean = true

    var keyboardOption = DEFAULT_KEYBOARD_OPTION

    var onTextChanged: (String) -> Unit = {}

    var underlineFocusColor: String = HongColor.MAIN_ORANGE_100.hex

    var underlineOutFocusColor: String = HongColor.GRAY_20.hex

    var underlineHeight = 2


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as HongUnderlineTextFieldOption

        if (type != other.type) return false
        if (isValidComponent != other.isValidComponent) return false
        if (width != other.width) return false
        if (height != other.height) return false
        if (margin != other.margin) return false
        if (padding != other.padding) return false
        if (click != other.click) return false
        if (backgroundColor != other.backgroundColor) return false
        if (backgroundColorHex != other.backgroundColorHex) return false
        if (radius != other.radius) return false
        if (shadow != other.shadow) return false
        if (border != other.border) return false
        if (useShapeCircle != other.useShapeCircle) return false
        if (clearImageOption != other.clearImageOption) return false
        if (input != other.input) return false
        if (placeholder != other.placeholder) return false
        if (placeholderTextOption != other.placeholderTextOption) return false
        if (inputTextOption != other.inputTextOption) return false
        if (cursorColor != other.cursorColor) return false
        if (useHideKeyboard != other.useHideKeyboard) return false
        if (keyboardOption != other.keyboardOption) return false
        if (onTextChanged != other.onTextChanged) return false
        if (underlineFocusColor != other.underlineFocusColor) return false
        if (underlineOutFocusColor != other.underlineOutFocusColor) return false
        if (underlineHeight != other.underlineHeight) return false

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
        result = 31 * result + backgroundColor.hashCode()
        result = 31 * result + backgroundColorHex.hashCode()
        result = 31 * result + radius.hashCode()
        result = 31 * result + shadow.hashCode()
        result = 31 * result + border.hashCode()
        result = 31 * result + useShapeCircle.hashCode()
        result = 31 * result + (clearImageOption?.hashCode() ?: 0)
        result = 31 * result + (input?.hashCode() ?: 0)
        result = 31 * result + (placeholder?.hashCode() ?: 0)
        result = 31 * result + placeholderTextOption.hashCode()
        result = 31 * result + inputTextOption.hashCode()
        result = 31 * result + cursorColor.hashCode()
        result = 31 * result + useHideKeyboard.hashCode()
        result = 31 * result + keyboardOption.hashCode()
        result = 31 * result + onTextChanged.hashCode()
        result = 31 * result + underlineFocusColor.hashCode()
        result = 31 * result + underlineOutFocusColor.hashCode()
        result = 31 * result + underlineHeight
        return result
    }

    override fun toString(): String {
        return "HongUnderlineTextFieldOption(" +
                "type=$type, " +
                "isValidComponent=$isValidComponent, " +
                "width=$width, " +
                "height=$height, " +
                "margin=$margin, " +
                "padding=$padding, " +
                "click=$click, " +
                "backgroundColor=$backgroundColor, " +
                "backgroundColorHex='$backgroundColorHex', " +
                "radius=$radius, " +
                "shadow=$shadow, " +
                "border=$border, " +
                "useShapeCircle=$useShapeCircle, " +
                "clearImageOption=$clearImageOption, " +
                "input=$input, " +
                "placeholder=$placeholder, " +
                "placeholderTextOption=$placeholderTextOption, " +
                "inputTextOption=$inputTextOption, " +
                "cursorColor='$cursorColor', " +
                "useHideKeyboard=$useHideKeyboard, " +
                "keyboardOption=$keyboardOption, " +
                "onTextChanged=$onTextChanged, " +
                "underlineFocusColor='$underlineFocusColor', " +
                "underlineOutFocusColor='$underlineOutFocusColor', " +
                "underlineHeight=$underlineHeight" +
                ")"
    }


}