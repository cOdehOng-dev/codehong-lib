package com.codehong.library.widget.textfield.def

import com.codehong.library.widget.HongWidgetCommonBuilder
import com.codehong.library.widget.rule.HongBorderInfo
import com.codehong.library.widget.rule.HongScaleType
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.keyboard.HongKeyboardActionType
import com.codehong.library.widget.rule.keyboard.HongKeyboardType
import com.codehong.library.widget.rule.radius.HongRadiusInfo
import com.codehong.library.widget.rule.typo.HongTypo

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

    fun placeholderPadding(padding: HongSpacingInfo) = apply {
        option.placeholderPadding = padding
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
            .backgroundColor(inject.backgroundColorHex)
            .placeholder(inject.placeholder)
            .placeholderColor(inject.placeholderColorHex)
            .placeholderTypo(inject.placeholderTypo)
            .placeholderPadding(inject.placeholderPadding)
            .input(inject.input)
            .inputTypo(inject.inputTypo)
            .inputColor(inject.inputColorHex)
            .clearIconRes(inject.clearIconRes)
            .clearIconSize(inject.clearIconSize)
            .clearIconMargin(inject.clearIconMargin)
            .clearIconScaleType(inject.clearIconScaleType)
            .cursorColor(inject.cursorColorHex)
            .useHideKeyboard(inject.useHideKeyboard)
            .singleLine(inject.singleLine)
            .maxLines(inject.maxLines)
            .minLines(inject.minLines)
            .keyboardOption(inject.keyboardOption)
            .onTextChanged(inject.onTextChanged)
            .delayInputCallback(inject.delayInputCallback)
            .radius(inject.radius)
            .border(inject.border)
    }
}
