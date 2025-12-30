package com.codehong.library.widget.textfield.timer

import com.codehong.library.widget.HongWidgetCommonBuilder
import com.codehong.library.widget.image.HongImageOption
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.keyboard.HongKeyboardActionType
import com.codehong.library.widget.rule.keyboard.HongKeyboardType
import com.codehong.library.widget.rule.typo.HongTypo
import com.codehong.library.widget.text.def.HongTextOption
import com.codehong.library.widget.textfield.def.HongTextFieldOption

class HongTextFieldTimerBuilder :
    HongWidgetCommonBuilder<HongTextFieldTimerOption, HongTextFieldTimerBuilder> {

    override val builder: HongTextFieldTimerBuilder = this
    override val option: HongTextFieldTimerOption = HongTextFieldTimerOption()

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

    fun clearImageOption(option: HongImageOption?) = apply {
        this.option.clearImageOption = option
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

    fun underlineFinishColor(underlineFinishColor: HongColor?) = apply {
        option.underlineFinishColor = underlineFinishColor
        underlineFinishColor(underlineFinishColor?.hex)
    }

    fun underlineFinishColor(underlineFinishColorHex: String?) = apply {
        option.underlineFinishColorHex = underlineFinishColorHex
    }

    fun countDownTextOption(countDownTextOption: HongTextOption) = apply {
        this.option.countDownTextOption = countDownTextOption
    }

    fun min(min: Int) = apply {
        this.option.min = min
    }

    fun sec(sec: Int) = apply {
        this.option.sec = sec
    }

    fun onFinish(onFinish: (() -> Unit)?) = apply {
        option.onFinish = onFinish
    }


    fun copy(inject: HongTextFieldTimerOption?): HongTextFieldTimerBuilder {
        if (inject == null) return HongTextFieldTimerBuilder()

        return HongTextFieldTimerBuilder()
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
            .clearImageOption(inject.clearImageOption)
            .cursorColor(inject.cursorColor)
            .cursorColor(inject.cursorColorHex)
            .useHideKeyboard(inject.useHideKeyboard)
            .keyboardOption(inject.keyboardOption)
            .onTextChanged(inject.onTextChanged)
            .backgroundColor(inject.backgroundColorHex)
            .underlineFocusColor(inject.underlineFocusColor)
            .underlineFocusColor(inject.underlineFocusColorHex)
            .underlineOutFocusColor(inject.underlineOutFocusColor)
            .underlineOutFocusColor(inject.underlineOutFocusColorHex)
            .underlineHeight(inject.underlineHeight)
            .underlineFinishColor(inject.underlineFinishColor)
            .underlineFinishColor(inject.underlineFinishColorHex)
            .countDownTextOption(inject.countDownTextOption)
            .min(inject.min)
            .sec(inject.sec)
            .onFinish(inject.onFinish)

    }
}