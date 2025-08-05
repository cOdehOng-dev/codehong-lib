package com.codehong.library.widget.label.select

import com.codehong.library.widget.HongWidgetCommonBuilder
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.keyboard.HongKeyboardActionType
import com.codehong.library.widget.rule.keyboard.HongKeyboardType
import com.codehong.library.widget.rule.typo.HongTypo
import com.codehong.library.widget.textfield.HongTextFieldBuilder

class HongLabelSelectInputBuilder : HongWidgetCommonBuilder<HongLabelSelectInputOption, HongLabelSelectInputBuilder> {

    override val builder: HongLabelSelectInputBuilder = this
    override val option: HongLabelSelectInputOption = HongLabelSelectInputOption()

    fun label(label: String?) = apply {
        option.label = label
    }
    fun labelColor(color: HongColor) = apply {
        option.labelColorHex = color.hex
    }
    fun labelColor(colorHex: String) = apply {
        option.labelColorHex = colorHex
    }
    fun labelTypo(typo: HongTypo) = apply {
        option.labelTypo = typo
    }

    fun description(description: String?) = apply {
        option.description = description
    }
    fun descriptionColor(color: HongColor) = apply {
        option.descriptionColorHex = color.hex
    }
    fun descriptionColor(colorHex: String) = apply {
        option.descriptionColorHex = colorHex
    }
    fun descriptionTypo(typo: HongTypo) = apply {
        option.descriptionTypo = typo
    }

    fun inputText(input: String?) = apply {
        this.option.input = input
        this.option.textFieldOption = HongTextFieldBuilder()
            .copy(option.textFieldOption)
            .input(input)
            .applyOption()
    }

    fun placeholder(placeholder: String?) = apply {
        this.option.placeholder = placeholder
        this.option.textFieldOption = HongTextFieldBuilder()
            .copy(option.textFieldOption)
            .placeholder(placeholder)
            .applyOption()
    }

    fun buttonText(buttonText: String?) = apply {
        this.option.buttonText = buttonText
    }

    fun buttonTextColor(color: HongColor) = apply {
        this.option.buttonTextColorHex = color.hex
    }
    fun buttonTextColor(colorHex: String) = apply {
        this.option.buttonTextColorHex = colorHex
    }

    fun buttonTextTypo(typo: HongTypo) = apply {
        this.option.buttonTextTypo = typo
    }

    fun selectPosition(selectPosition: Int) = apply {
        this.option.selectPosition = selectPosition
    }

    fun selectList(selectList: List<String>) = apply {
        this.option.selectList = selectList
    }

    fun useOnlyNumber(useOnlyNumber: Boolean) = apply {
        this.option.useOnlyNumber = useOnlyNumber
        option.textFieldOption = HongTextFieldBuilder()
            .copy(option.textFieldOption)
            .keyboardOption(
                Pair(
                    if (option.useOnlyNumber) HongKeyboardType.NUMBER else HongKeyboardType.TEXT,
                    HongKeyboardActionType.DONE
                )
            )
            .applyOption()
    }
    fun useDirectCallback(useDirectCallback: Boolean) = apply {
        this.option.useDirectCallback = useDirectCallback
    }

    fun showInput(showInput: Boolean) = apply {
        this.option.showInput = showInput
    }

    fun pickerCallback(pickerCallback: ((String, Int) -> Unit)?) = apply {
        this.option.pickerCallback = pickerCallback
    }

    fun inputCallback(inputCallback: ((String?) -> Unit)?) = apply {
        this.option.inputCallback = inputCallback
        option.textFieldOption = HongTextFieldBuilder()
            .copy(option.textFieldOption)
            .onTextChanged {
                inputCallback?.invoke(it)
            }
            .applyOption()
    }


    fun copy(inject: HongLabelSelectInputOption): HongLabelSelectInputBuilder {
        return HongLabelSelectInputBuilder()
            .width(inject.width)
            .height(inject.height)
            .margin(inject.margin)
            .padding(inject.padding)
            .onClick(inject.click)
            .label(inject.label)
            .labelColor(inject.labelColorHex)
            .labelTypo(inject.labelTypo)
            .description(inject.description)
            .descriptionColor(inject.descriptionColorHex)
            .descriptionTypo(inject.descriptionTypo)
            .inputText(inject.input)
            .placeholder(inject.placeholder)
            .buttonText(inject.buttonText)
            .buttonTextColor(inject.buttonTextColorHex)
            .buttonTextTypo(inject.buttonTextTypo)
            .selectPosition(inject.selectPosition)
            .selectList(inject.selectList)
            .useOnlyNumber(inject.useOnlyNumber)
            .useDirectCallback(inject.useDirectCallback)
            .showInput(inject.showInput)
            .pickerCallback(inject.pickerCallback)
            .inputCallback(inject.inputCallback)
    }
}