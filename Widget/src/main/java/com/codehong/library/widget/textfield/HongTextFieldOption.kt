package com.codehong.library.widget.textfield

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
import com.codehong.library.widget.rule.typo.HongFont
import com.codehong.library.widget.text.HongTextBuilder

data class HongTextFieldOption(
    override val type: HongWidgetType = HongWidgetType.TEXT_FILED
) : HongWidgetCommonOption {

    companion object {
        val DEFAULT_PLACEHOLDER_FONT_TYPE = HongFont.PRETENDARD_400
        const val DEFAULT_PLACEHOLDER_SIZE = 16
        val DEFAULT_PLACEHOLDER_COLOR = HongColor.BLACK_30.hex

        val DEFAULT_INPUT_FONT_TYPE = HongFont.PRETENDARD_700
        const val DEFAULT_INPUT_SIZE = 16
        val DEFAULT_INPUT_COLOR = HongColor.BLACK_100.hex

        const val DEFAULT_USE_HIDE_KEYBOARD = true
        const val DEFAULT_MAX_LINES = Int.MAX_VALUE
        const val DEFAULT_MIN_LINES = 1

        val DEFAULT_KEYBOARD_OPTION = Pair(
            HongKeyboardType.TEXT,
            HongKeyboardActionType.DONE
        )

        const val DEFAULT_SINGLE_LINE = true

        const val DEFAULT_USE_SHAPE_CIRCLE = false

        const val DEFAULT_DELAY_INPUT_CALLBACK = 0L

        val DEFAULT_PLACEHOLDER = HongTextBuilder()
            .width(HongLayoutParam.MATCH_PARENT.value)
            .fontType(DEFAULT_PLACEHOLDER_FONT_TYPE)
            .size(DEFAULT_PLACEHOLDER_SIZE)
            .color(DEFAULT_PLACEHOLDER_COLOR)
            .applyOption()

        val DEFAULT_INPUT = HongTextBuilder()
            .width(HongLayoutParam.MATCH_PARENT.value)
            .fontType(DEFAULT_INPUT_FONT_TYPE)
            .size(DEFAULT_INPUT_SIZE)
            .color(DEFAULT_INPUT_COLOR)
            .applyOption()

        val DEFAULT_CLEAR_IMAGE = HongImageBuilder()
            .width(20)
            .height(20)
            .margin(
                HongSpacingInfo(
                    left = 8f
                )
            )
            .drawableResId(R.drawable.honglib_ic_20_close)
            .applyOption()
    }


    override var isValidComponent: Boolean = true

    override var width: Int = HongLayoutParam.WRAP_CONTENT.value
    override var height: Int = HongLayoutParam.WRAP_CONTENT.value
    override var margin: HongSpacingInfo = HongSpacingInfo(0f, 0f, 0f, 0f)
    override var padding: HongSpacingInfo = HongSpacingInfo(0f, 0f, 0f, 0f)
    override var click: ((HongWidgetCommonOption) -> Unit)? = null

    override var backgroundColor: HongColor = HongColor.BLACK_5
    override var backgroundColorHex: String = HongColor.BLACK_5.hex

    override var radius: HongRadiusInfo = HongRadiusInfo()
    override var shadow: HongShadowInfo = HongShadowInfo()
    override var border: HongBorderInfo = HongBorderInfo()

    override var useShapeCircle: Boolean = DEFAULT_USE_SHAPE_CIRCLE

    var placeholderTextOption = DEFAULT_PLACEHOLDER
    var inputTextOption = DEFAULT_INPUT
    var clearImageOption: HongImageOption? = null

    var input: String? = null
    var placeholder: String? = null

    var cursorColor: HongColor = HongColor.MAIN_ORANGE_100
    var cursorColorHex: String = HongColor.MAIN_ORANGE_100.hex
    var useHideKeyboard: Boolean = DEFAULT_USE_HIDE_KEYBOARD
    var singleLine: Boolean = DEFAULT_SINGLE_LINE
    var maxLines: Int = DEFAULT_MAX_LINES
    var minLines: Int = DEFAULT_MIN_LINES

    var keyboardOption = DEFAULT_KEYBOARD_OPTION

    var delayInputCallback = DEFAULT_DELAY_INPUT_CALLBACK

    var onTextChanged: (String) -> Unit = {}


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as HongTextFieldOption

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
        if (placeholderTextOption != other.placeholderTextOption) return false
        if (inputTextOption != other.inputTextOption) return false
        if (clearImageOption != other.clearImageOption) return false
        if (input != other.input) return false
        if (placeholder != other.placeholder) return false
        if (cursorColor != other.cursorColor) return false
        if (cursorColorHex != other.cursorColorHex) return false
        if (useHideKeyboard != other.useHideKeyboard) return false
        if (singleLine != other.singleLine) return false
        if (maxLines != other.maxLines) return false
        if (minLines != other.minLines) return false
        if (keyboardOption != other.keyboardOption) return false
        if (delayInputCallback != other.delayInputCallback) return false
        if (onTextChanged != other.onTextChanged) return false

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
        result = 31 * result + placeholderTextOption.hashCode()
        result = 31 * result + inputTextOption.hashCode()
        result = 31 * result + (clearImageOption?.hashCode() ?: 0)
        result = 31 * result + (input?.hashCode() ?: 0)
        result = 31 * result + (placeholder?.hashCode() ?: 0)
        result = 31 * result + cursorColor.hashCode()
        result = 31 * result + cursorColorHex.hashCode()
        result = 31 * result + useHideKeyboard.hashCode()
        result = 31 * result + singleLine.hashCode()
        result = 31 * result + maxLines
        result = 31 * result + minLines
        result = 31 * result + keyboardOption.hashCode()
        result = 31 * result + delayInputCallback.hashCode()
        result = 31 * result + onTextChanged.hashCode()
        return result
    }

    override fun toString(): String {
        return "HongTextFieldOption(" +
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
                "placeholderTextOption=$placeholderTextOption, " +
                "inputTextOption=$inputTextOption, " +
                "clearImageOption=$clearImageOption, " +
                "input=$input, " +
                "placeholder=$placeholder, " +
                "cursorColor=$cursorColor, " +
                "cursorColorHex='$cursorColorHex', " +
                "useHideKeyboard=$useHideKeyboard, " +
                "singleLine=$singleLine, " +
                "maxLines=$maxLines, " +
                "minLines=$minLines, " +
                "keyboardOption=$keyboardOption, " +
                "delayInputCallback=$delayInputCallback, " +
                "onTextChanged=$onTextChanged" +
                ")"
    }
}