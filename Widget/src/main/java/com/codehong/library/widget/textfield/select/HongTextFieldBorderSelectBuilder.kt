package com.codehong.library.widget.textfield.select

import com.codehong.library.widget.HongWidgetCommonBuilder
import com.codehong.library.widget.rule.HongInputState
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.radius.HongRadiusInfo
import com.codehong.library.widget.rule.typo.HongTypo

class HongTextFieldBorderSelectBuilder :
    HongWidgetCommonBuilder<HongTextFieldBorderSelectOption, HongTextFieldBorderSelectBuilder> {

    override val builder: HongTextFieldBorderSelectBuilder = this
    override val option: HongTextFieldBorderSelectOption = HongTextFieldBorderSelectOption()


    fun inputRadius(radiusInfo: HongRadiusInfo) = apply {
        option.inputRadius = radiusInfo
    }

    fun enableBorderColor(color: HongColor) = apply {
        option.enableBorderColorHex = color.hex
    }
    fun enableBorderColor(hex: String) = apply {
        option.enableBorderColorHex = hex
    }

    fun focusedBorderColor(color: HongColor) = apply {
        option.focusedBorderColorHex = color.hex
    }
    fun focusedBorderColor(hex: String) = apply {
        option.focusedBorderColorHex = hex
    }

    fun inputBackgroundColor(color: HongColor) = apply {
        option.inputBackgroundColorHex = color.hex
    }
    fun inputBackgroundColor(hex: String) = apply {
        option.inputBackgroundColorHex = hex
    }

    fun label(label: String) = apply {
        option.label = label
    }
    fun labelColorHex(hex: String) = apply {
        option.labelColoHex = hex
    }
    fun labelTypo(typo: HongTypo) = apply {
        option.labelTypo = typo
    }

    fun initialInput(input: String) = apply {
        option.initialInput = input
    }
    fun inputTextColor(color: HongColor) = apply {
        option.inputTextColorHex = color.hex
    }
    fun inputTextColor(hex: String) = apply {
        option.inputTextColorHex = hex
    }

    fun placeholder(placeholder: String) = apply {
        option.placeholder = placeholder
    }
    fun placeholderColor(color: HongColor) = apply {
        option.placeholderColorHex = color.hex
    }
    fun placeholderColor(hex: String) = apply {
        option.placeholderColorHex = hex
    }
    fun placeholderTypo(typo: HongTypo) = apply {
        option.placeholderTypo = typo
    }

    fun helperText(helperText: String) = apply {
        option.helperText = helperText
    }

    fun helperTextTypo(typo: HongTypo) = apply {
        option.helperTextTypo = typo
    }

    fun isRequired(isRequired: Boolean) = apply {
        option.isRequired = isRequired
    }

    fun state(state: HongInputState) = apply {
        option.state = state
    }


    fun useNumberKeypad(useNumberKeypad: Boolean) = apply {
        option.useNumberKeypad = useNumberKeypad
    }

    fun useDirectInput(useDirectInput: Boolean) = apply {
        option.useDirectInput = useDirectInput
    }

    fun onChangeInput(onChange: (String) -> Unit) = apply {
        option.onChangeInput = onChange
    }

    fun onSelectionClick(onClick: () -> Unit) = apply {
        option.onSelectionClick = onClick
    }

    fun copy(inject: HongTextFieldBorderSelectOption?): HongTextFieldBorderSelectBuilder {
        if (inject == null) return HongTextFieldBorderSelectBuilder()

        return HongTextFieldBorderSelectBuilder()
            .inputRadius(inject.inputRadius)
            .enableBorderColor(inject.enableBorderColorHex)
            .focusedBorderColor(inject.focusedBorderColorHex)
            .inputBackgroundColor(inject.inputBackgroundColorHex)
            .label(inject.label)
            .labelColorHex(inject.labelColoHex)
            .labelTypo(inject.labelTypo)
            .initialInput(inject.initialInput)
            .inputTextColor(inject.inputTextColorHex)
            .placeholder(inject.placeholder)
            .placeholderColor(inject.placeholderColorHex)
            .placeholderTypo(inject.placeholderTypo)
            .helperText(inject.helperText)
            .helperTextTypo(inject.helperTextTypo)
            .isRequired(inject.isRequired)
            .state(inject.state)
            .onChangeInput(inject.onChangeInput)
            .onSelectionClick(inject.onSelectionClick)
            .useDirectInput(inject.useDirectInput)
            .useNumberKeypad(inject.useNumberKeypad)
    }
}