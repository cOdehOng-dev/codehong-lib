package com.codehong.library.widget.textfield.number

import com.codehong.library.widget.HongWidgetCommonBuilder
import com.codehong.library.widget.rule.HongScaleType
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.keyboard.HongKeyboardActionType
import com.codehong.library.widget.rule.keyboard.HongKeyboardType
import com.codehong.library.widget.rule.typo.HongTypo

class HongTextFieldNumberBuilder
    : HongWidgetCommonBuilder<HongTextFieldNumberOption, HongTextFieldNumberBuilder> {
    override val builder: HongTextFieldNumberBuilder = this
    override val option: HongTextFieldNumberOption = HongTextFieldNumberOption()

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
        this.option.keyboardOption = keyboardOption ?: HongTextFieldNumberOption.DEFAULT_KEYBOARD_OPTION
    }

    fun onTextChanged(onTextChanged: ((String) -> Unit)) = apply {
        option.onTextChanged = onTextChanged
    }

    fun useDecimal(useDecimal: Boolean) = apply {
        option.useDecimal = useDecimal
    }

    fun copy(inject: HongTextFieldNumberOption?): HongTextFieldNumberBuilder {
        if (inject == null) return HongTextFieldNumberBuilder()

        return HongTextFieldNumberBuilder()
            .width(inject.width)
            .height(inject.height)
            .margin(inject.margin)
            .padding(inject.padding)
            .onClick(inject.click)
            .placeholder(inject.placeholder)
            .placeholderColor(inject.placeholderColorHex)
            .placeholderTypo(inject.placeholderTypo)
            .input(inject.input)
            .inputTypo(inject.inputTypo)
            .inputColor(inject.inputColorHex)
            .clearIconRes(inject.clearIconRes)
            .clearIconSize(inject.clearIconSize)
            .clearIconScaleType(inject.clearIconScaleType)
            .clearIconMargin(inject.clearIconMargin)
            .cursorColor(inject.cursorColorHex)
            .useHideKeyboard(inject.useHideKeyboard)
            .keyboardOption(inject.keyboardOption)
            .onTextChanged(inject.onTextChanged)
            .backgroundColor(inject.backgroundColorHex)
            .useDecimal(inject.useDecimal)
    }
}