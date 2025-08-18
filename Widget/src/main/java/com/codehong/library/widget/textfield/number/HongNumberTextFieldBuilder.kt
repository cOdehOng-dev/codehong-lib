package com.codehong.library.widget.textfield.number

import com.codehong.library.widget.HongWidgetCommonBuilder
import com.codehong.library.widget.image.HongImageOption
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.keyboard.HongKeyboardActionType
import com.codehong.library.widget.rule.keyboard.HongKeyboardType
import com.codehong.library.widget.text.HongTextBuilder
import com.codehong.library.widget.text.HongTextOption

class HongNumberTextFieldBuilder
    : HongWidgetCommonBuilder<HongNumberTextFieldOption, HongNumberTextFieldBuilder> {
    override val builder: HongNumberTextFieldBuilder = this
    override val option: HongNumberTextFieldOption = HongNumberTextFieldOption()

    fun placeholder(placeholder: String?) = apply {
        option.placeholder = placeholder
        placeholderTextOption(
            HongTextBuilder()
                .copy(option.placeholderTextOption)
                .text(placeholder ?: option.placeholder)
                .applyOption()
        )
    }
    fun placeholderTextOption(placeholderTextOption: HongTextOption) = apply {
        this.option.placeholderTextOption = HongTextBuilder()
            .copy(placeholderTextOption)
            .text(this.option.placeholder ?: placeholderTextOption.text)
            .applyOption()

    }

    fun input(input: String?) = apply {
        option.input = input
        option.inputTextOption = HongTextBuilder()
            .copy(option.inputTextOption)
            .text(input ?: option.inputTextOption.text)
            .applyOption()
    }
    fun inputTextOption(inputTextOption: HongTextOption) = apply {
        this.option.inputTextOption = HongTextBuilder()
            .copy(inputTextOption)
            .text(this.option.input ?: inputTextOption.text)
            .applyOption()
    }

    fun clearImageOption(option: HongImageOption?) = apply {
        this.option.clearImageOption = option
    }

    fun cursorColor(cursorColor: HongColor) = apply {
        cursorColor(cursorColor.hex)
    }
    fun cursorColor(cursorColor: String) = apply {
        this.option.cursorColor = cursorColor
    }

    fun useHideKeyboard(useHideKeyboard: Boolean) = apply {
        this.option.useHideKeyboard = useHideKeyboard
    }

    fun keyboardOption(keyboardOption: Pair<HongKeyboardType, HongKeyboardActionType>?) = apply {
        this.option.keyboardOption = keyboardOption ?: HongNumberTextFieldOption.DEFAULT_KEYBOARD_OPTION
    }

    fun onTextChanged(onTextChanged: ((String) -> Unit)) = apply {
        option.onTextChanged = onTextChanged
    }

    fun useDecimal(useDecimal: Boolean) = apply {
        option.useDecimal = useDecimal
    }

    fun copy(inject: HongNumberTextFieldOption?): HongNumberTextFieldBuilder {
        if (inject == null) return HongNumberTextFieldBuilder()

        return HongNumberTextFieldBuilder()
            .width(inject.width)
            .height(inject.height)
            .margin(inject.margin)
            .padding(inject.padding)
            .onClick(inject.click)
            .placeholder(inject.placeholder)
            .placeholderTextOption(inject.placeholderTextOption)
            .input(inject.input)
            .inputTextOption(inject.inputTextOption)
            .clearImageOption(inject.clearImageOption)
            .cursorColor(inject.cursorColor)
            .useHideKeyboard(inject.useHideKeyboard)
            .keyboardOption(inject.keyboardOption)
            .onTextChanged(inject.onTextChanged)
            .backgroundColor(inject.backgroundColorHex)
            .useDecimal(inject.useDecimal)
    }
}