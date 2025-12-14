package com.codehong.library.widget.textfield.underline

import com.codehong.library.widget.HongWidgetCommonOption
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

data class HongTextFieldUnderlineOption(
    override val type: HongWidgetType = HongWidgetType.TEXT_FIELD_UNDERLINE
) : HongWidgetCommonOption {

    companion object {
        val DEFAULT_KEYBOARD_OPTION = Pair(
            HongKeyboardType.NUMBER,
            HongKeyboardActionType.DONE
        )
    }


    override var isValidComponent: Boolean = true

    override var width: Int = HongLayoutParam.WRAP_CONTENT.value
    override var height: Int = HongLayoutParam.WRAP_CONTENT.value
    override var margin: HongSpacingInfo = HongSpacingInfo()
    override var padding: HongSpacingInfo = HongSpacingInfo()
    override var click: ((HongWidgetCommonOption) -> Unit)? = null

    override var backgroundColorHex: String = HongColor.TRANSPARENT.hex

    override var radius: HongRadiusInfo = HongRadiusInfo()
    override var shadow: HongShadowInfo = HongShadowInfo()
    override var border: HongBorderInfo = HongBorderInfo()
    override var useShapeCircle: Boolean = false


    var input: String? = null
    var inputTypo: HongTypo = HongTypo.BODY_16_B
    var inputColorHex: String = HongColor.BLACK_100.hex

    var placeholder: String? = null
    var placeholderTypo: HongTypo = HongTypo.BODY_16
    var placeholderColorHex: String = HongColor.BLACK_30.hex

    var clearIconRes: Int? = null
    var clearIconSize: Int = 20
    var clearIconScaleType: HongScaleType = HongScaleType.CENTER_CROP
    var clearIconMargin: HongSpacingInfo = HongSpacingInfo(left = 8f)

    var cursorColorHex: String = HongColor.MAIN_ORANGE_100.hex
    var useHideKeyboard: Boolean = true

    var keyboardOption = DEFAULT_KEYBOARD_OPTION

    var onTextChanged: (String) -> Unit = {}

    var underlineFocusColor: String = HongColor.MAIN_ORANGE_100.hex

    var underlineOutFocusColor: String = HongColor.GRAY_20.hex

    var underlineHeight = 2

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as HongTextFieldUnderlineOption

        if (type != other.type) return false
        if (isValidComponent != other.isValidComponent) return false
        if (width != other.width) return false
        if (height != other.height) return false
        if (margin != other.margin) return false
        if (padding != other.padding) return false
        if (click != other.click) return false
        if (backgroundColorHex != other.backgroundColorHex) return false
        if (radius != other.radius) return false
        if (shadow != other.shadow) return false
        if (border != other.border) return false
        if (useShapeCircle != other.useShapeCircle) return false
        if (input != other.input) return false
        if (inputTypo != other.inputTypo) return false
        if (inputColorHex != other.inputColorHex) return false
        if (placeholder != other.placeholder) return false
        if (placeholderTypo != other.placeholderTypo) return false
        if (placeholderColorHex != other.placeholderColorHex) return false
        if (clearIconRes != other.clearIconRes) return false
        if (clearIconSize != other.clearIconSize) return false
        if (clearIconScaleType != other.clearIconScaleType) return false
        if (clearIconMargin != other.clearIconMargin) return false
        if (cursorColorHex != other.cursorColorHex) return false
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
        result = 31 * result + backgroundColorHex.hashCode()
        result = 31 * result + radius.hashCode()
        result = 31 * result + shadow.hashCode()
        result = 31 * result + border.hashCode()
        result = 31 * result + useShapeCircle.hashCode()
        result = 31 * result + (input?.hashCode() ?: 0)
        result = 31 * result + inputTypo.hashCode()
        result = 31 * result + inputColorHex.hashCode()
        result = 31 * result + (placeholder?.hashCode() ?: 0)
        result = 31 * result + placeholderTypo.hashCode()
        result = 31 * result + placeholderColorHex.hashCode()
        result = 31 * result + (clearIconRes ?: 0)
        result = 31 * result + clearIconSize
        result = 31 * result + clearIconScaleType.hashCode()
        result = 31 * result + clearIconMargin.hashCode()
        result = 31 * result + cursorColorHex.hashCode()
        result = 31 * result + useHideKeyboard.hashCode()
        result = 31 * result + keyboardOption.hashCode()
        result = 31 * result + onTextChanged.hashCode()
        result = 31 * result + underlineFocusColor.hashCode()
        result = 31 * result + underlineOutFocusColor.hashCode()
        result = 31 * result + underlineHeight
        return result
    }

    override fun toString(): String {
        return "HongTextFieldUnderlineOption(" +
                "type=$type, " +
                "isValidComponent=$isValidComponent, " +
                "width=$width, " +
                "height=$height, " +
                "margin=$margin, " +
                "padding=$padding, " +
                "click=$click, " +
                "backgroundColorHex='$backgroundColorHex', " +
                "radius=$radius, " +
                "shadow=$shadow, " +
                "border=$border, " +
                "useShapeCircle=$useShapeCircle, " +
                "input=$input, " +
                "inputTypo=$inputTypo, " +
                "inputColorHex='$inputColorHex', " +
                "placeholder=$placeholder, " +
                "placeholderTypo=$placeholderTypo, " +
                "placeholderColorHex='$placeholderColorHex', " +
                "clearIconRes=$clearIconRes, " +
                "clearIconSize=$clearIconSize, " +
                "clearIconScaleType=$clearIconScaleType, " +
                "clearIconMargin=$clearIconMargin, " +
                "cursorColorHex='$cursorColorHex', " +
                "useHideKeyboard=$useHideKeyboard, " +
                "keyboardOption=$keyboardOption, " +
                "onTextChanged=$onTextChanged, " +
                "underlineFocusColor='$underlineFocusColor', " +
                "underlineOutFocusColor='$underlineOutFocusColor', " +
                "underlineHeight=$underlineHeight" +
                ")"
    }


}