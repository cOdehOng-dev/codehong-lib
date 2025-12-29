package com.codehong.library.widget.textfield.underline

import com.codehong.library.widget.HongWidgetCommonBuilder
import com.codehong.library.widget.rule.HongScaleType
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.keyboard.HongKeyboardActionType
import com.codehong.library.widget.rule.keyboard.HongKeyboardType
import com.codehong.library.widget.rule.typo.HongTypo
import com.codehong.library.widget.textfield.def.HongTextFieldOption

class HongTextFieldUnderlineBuilder : HongWidgetCommonBuilder<HongTextFieldUnderlineOption, HongTextFieldUnderlineBuilder> {

    override val builder: HongTextFieldUnderlineBuilder = this
    override val option: HongTextFieldUnderlineOption = HongTextFieldUnderlineOption()

    fun placeholder(placeholder: String?) = apply {
        option.placeholder = placeholder
    }

    fun placeholderColor(color: HongColor) = apply {
        option.placeholderColorHex = color.hex
    }
    fun placeholderColor(color: String) = apply {
        option.placeholderColorHex = color
    }

    fun placeholderTypo(typo: HongTypo) = apply {
        option.placeholderTypo = typo
    }

    fun input(input: String?) = apply {
        option.input = input
    }

    fun inputTypo(typo: HongTypo) = apply {
        option.inputTypo = typo
    }

    fun inputColor(color: HongColor) = apply {
        option.inputColorHex = color.hex
    }
    fun inputColor(color: String) = apply {
        option.inputColorHex = color
    }

    fun clearIconRes(resId: Int?) = apply {
        this.option.clearIconRes = resId
    }
    fun clearIconSize(size: Int) = apply {
        this.option.clearIconSize = size
    }
    fun clearIconScaleType(scaleType: HongScaleType) = apply {
        this.option.clearIconScaleType = scaleType
    }
    fun clearIconMargin(margin: HongSpacingInfo) = apply {
        this.option.clearIconMargin = margin
    }

    fun cursorColor(cursorColor: HongColor) = apply {
        option.cursorColorHex = cursorColor.hex
    }
    fun cursorColor(cursorColor: String) = apply {
        this.option.cursorColorHex = cursorColor
    }

    fun useHideKeyboard(useHideKeyboard: Boolean) = apply {
        this.option.useHideKeyboard = useHideKeyboard
    }

    fun keyboardOption(keyboardOption: Pair<HongKeyboardType, HongKeyboardActionType>?) = apply {
        this.option.keyboardOption = keyboardOption ?: HongTextFieldOption.DEFAULT_KEYBOARD_OPTION
    }

    fun onTextChanged(onTextChanged: ((String) -> Unit)) = apply {
        option.onTextChanged = onTextChanged
    }

    fun underlineFocusColor(underlineFocusColor: HongColor) = apply {
        underlineFocusColor(underlineFocusColor.hex)
    }

    fun underlineFocusColor(underlineFocusColorHex: String) = apply {
        option.underlineFocusColor = underlineFocusColorHex
    }

    fun underlineOutFocusColor(underlineOutFocusColor: HongColor) = apply {
        underlineOutFocusColor(underlineOutFocusColor.hex)
    }

    fun underlineOutFocusColor(underlineOutFocusColorHex: String) = apply {
        option.underlineOutFocusColor = underlineOutFocusColorHex
    }

    fun underlineHeight(underlineHeight: Int) = apply {
        option.underlineHeight = underlineHeight
    }

    fun copy(inject: HongTextFieldUnderlineOption?): HongTextFieldUnderlineBuilder {
        if (inject == null) return HongTextFieldUnderlineBuilder()

        return HongTextFieldUnderlineBuilder()
            .width(inject.width)
            .height(inject.height)
            .margin(inject.margin)
            .padding(inject.padding)
            .onClick(inject.click)
            .placeholder(inject.placeholder)
            .placeholderTypo(inject.placeholderTypo)
            .placeholderColor(inject.placeholderColorHex)
            .input(inject.input)
            .inputTypo(inject.inputTypo)
            .inputColor(inject.inputColorHex)
            .clearIconRes(inject.clearIconRes)
            .clearIconSize(inject.clearIconSize)
            .clearIconMargin(inject.clearIconMargin)
            .clearIconScaleType(inject.clearIconScaleType)
            .cursorColor(inject.cursorColorHex)
            .useHideKeyboard(inject.useHideKeyboard)
            .keyboardOption(inject.keyboardOption)
            .onTextChanged(inject.onTextChanged)
            .backgroundColor(inject.backgroundColorHex)
            .underlineFocusColor(inject.underlineFocusColor)
            .underlineOutFocusColor(inject.underlineOutFocusColor)
            .underlineHeight(inject.underlineHeight)

    }
}