package com.codehong.library.widget.textfield

import com.codehong.library.widget.HongWidgetCommonBuilder
import com.codehong.library.widget.image.HongImageOption
import com.codehong.library.widget.rule.HongBorderInfo
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.keyboard.HongKeyboardActionType
import com.codehong.library.widget.rule.keyboard.HongKeyboardType
import com.codehong.library.widget.rule.radius.HongRadiusInfo
import com.codehong.library.widget.text.HongTextBuilder
import com.codehong.library.widget.text.HongTextOption

class HongTextFieldBuilder : HongWidgetCommonBuilder<HongTextFieldOption, HongTextFieldBuilder> {

    override val builder: HongTextFieldBuilder = this
    override val option: HongTextFieldOption = HongTextFieldOption()


    fun radius(radius: HongRadiusInfo) = apply {
        option.radius = radius
    }

    fun border(border: HongBorderInfo) = apply {
        option.border = border
    }

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

    fun singleLine(singleLine: Boolean) = apply {
        this.option.singleLine = singleLine
    }

    fun maxLines(maxLines: Int) = apply {
        this.option.maxLines = maxLines
    }

    fun minLines(minLines: Int) = apply {
        this.option.minLines = minLines
    }

    fun keyboardOption(keyboardOption: Pair<HongKeyboardType, HongKeyboardActionType>?) = apply {
        this.option.keyboardOption = keyboardOption ?: HongTextFieldOption.DEFAULT_KEYBOARD_OPTION
    }

    fun delayInputCallback(delay: Long) = apply {
        this.option.delayInputCallback = delay
    }

    fun onTextChanged(onTextChanged: ((String) -> Unit)) = apply {
        option.onTextChanged = onTextChanged
    }

    fun copy(inject: HongTextFieldOption): HongTextFieldBuilder {
        return HongTextFieldBuilder()
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
            .singleLine(inject.singleLine)
            .maxLines(inject.maxLines)
            .minLines(inject.minLines)
            .keyboardOption(inject.keyboardOption)
            .onTextChanged(inject.onTextChanged)
            .delayInputCallback(inject.delayInputCallback)
            .backgroundColor(inject.backgroundColor)
            .backgroundColor(inject.backgroundColorHex)
            .radius(inject.radius)
            .border(inject.border)
    }
}
