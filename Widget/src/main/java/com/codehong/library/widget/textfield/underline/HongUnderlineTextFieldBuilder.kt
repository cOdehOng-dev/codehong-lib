package com.codehong.library.widget.textfield.underline

import com.codehong.library.widget.HongWidgetCommonBuilder
import com.codehong.library.widget.image.HongImageOption
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.keyboard.HongKeyboardActionType
import com.codehong.library.widget.rule.keyboard.HongKeyboardType
import com.codehong.library.widget.text.HongTextBuilder
import com.codehong.library.widget.text.HongTextOption
import com.codehong.library.widget.textfield.HongTextFieldOption

class HongUnderlineTextFieldBuilder : HongWidgetCommonBuilder<HongUnderlineTextFieldOption, HongUnderlineTextFieldBuilder> {

    override val builder: HongUnderlineTextFieldBuilder = this
    override val option: HongUnderlineTextFieldOption = HongUnderlineTextFieldOption()

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
        option.cursorColor = cursorColor
        cursorColor(cursorColor.hex)
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
        option.underlineFocusColor = underlineFocusColor
        underlineFocusColor(underlineFocusColor.hex)
    }

    fun underlineFocusColor(underlineFocusColorHex: String) = apply {
        option.underlineFocusColorHex = underlineFocusColorHex
    }

    fun underlineOutFocusColor(underlineOutFocusColor: HongColor) = apply {
        option.underlineOutFocusColor = underlineOutFocusColor
        underlineOutFocusColor(underlineOutFocusColor.hex)
    }

    fun underlineOutFocusColor(underlineOutFocusColorHex: String) = apply {
        option.underlineOutFocusColorHex = underlineOutFocusColorHex
    }

    fun underlineHeight(underlineHeight: Int) = apply {
        option.underlineHeight = underlineHeight
    }

    fun copy(inject: HongUnderlineTextFieldOption?): HongUnderlineTextFieldBuilder {
        if (inject == null) return HongUnderlineTextFieldBuilder()

        return HongUnderlineTextFieldBuilder()
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
            .cursorColor(inject.cursorColorHex)
            .useHideKeyboard(inject.useHideKeyboard)
            .keyboardOption(inject.keyboardOption)
            .onTextChanged(inject.onTextChanged)
            .backgroundColor(inject.backgroundColor)
            .backgroundColor(inject.backgroundColorHex)
            .underlineFocusColor(inject.underlineFocusColor)
            .underlineFocusColor(inject.underlineFocusColorHex)
            .underlineOutFocusColor(inject.underlineOutFocusColor)
            .underlineOutFocusColor(inject.underlineOutFocusColorHex)
            .underlineHeight(inject.underlineHeight)

    }
}