package com.codehong.library.widget.label.select

import com.codehong.library.widget.HongWidgetCommonBuilder
import com.codehong.library.widget.button.text.HongTextButtonBuilder
import com.codehong.library.widget.button.text.HongTextButtonOption
import com.codehong.library.widget.label.HongLabelBuilder
import com.codehong.library.widget.label.HongLabelOption
import com.codehong.library.widget.text.HongTextBuilder
import com.codehong.library.widget.text.HongTextOption
import com.codehong.library.widget.textfield.HongTextFieldBuilder
import com.codehong.library.widget.textfield.HongTextFieldOption

class HongLabelSelectInputBuilder : HongWidgetCommonBuilder<HongLabelSelectInputOption, HongLabelSelectInputBuilder> {

    override val builder: HongLabelSelectInputBuilder = this
    override val option: HongLabelSelectInputOption = HongLabelSelectInputOption()

    fun label(label: String?) = apply {
        this.option.label = label
        labelOption(
            HongLabelBuilder()
                .copy(option.labelOption)
                .label(label)
                .applyOption()
        )
    }

    fun description(description: String?) = apply {
        this.option.description = description
        labelOption(
            HongLabelBuilder()
                .copy(option.labelOption)
                .description(description)
                .applyOption()
        )
    }

    fun labelOption(labelOption: HongLabelOption) = apply {
        this.option.labelOption = labelOption
    }

    fun inputText(input: String?) = apply {
        this.option.input = input
        textFieldOption(
            HongTextFieldBuilder()
                .copy(option.textFieldOption)
                .input(input)
                .applyOption()
        )
    }

    fun placeholder(placeholder: String?) = apply {
        this.option.placeholder = placeholder
        textFieldOption(
            HongTextFieldBuilder()
                .copy(option.textFieldOption)
                .placeholder(placeholder)
                .applyOption()
        )
    }

    fun textFieldOption(textFieldOption: HongTextFieldOption) = apply {
        this.option.textFieldOption = textFieldOption
    }

    fun buttonText(buttonText: String?) = apply {
        this.option.buttonText = buttonText
        buttonTextOption(
            HongTextBuilder()
                .copy(option.buttonTextOption)
                .text(buttonText)
                .applyOption()
        )
        textButtonOption(
            HongTextButtonBuilder()
                .copy(option.textButtonOption)
                .textOption(
                    HongTextBuilder()
                        .copy(option.buttonTextOption)
                        .text(buttonText)
                        .applyOption()
                )
                .applyOption()
        )
    }

    fun buttonTextOption(buttonTextOption: HongTextOption) = apply {
        this.option.buttonTextOption = buttonTextOption
        textButtonOption(
            HongTextButtonBuilder()
                .copy(option.textButtonOption)
                .textOption(buttonTextOption)
                .applyOption()
        )
    }

    fun textButtonOption(textButtonOption: HongTextButtonOption) = apply {
        this.option.textButtonOption = textButtonOption
    }

    fun selectPosition(selectPosition: Int) = apply {
        this.option.selectPosition = selectPosition
    }

    fun selectList(selectList: List<String>) = apply {
        this.option.selectList = selectList
    }

    fun useOnlyNumber(useOnlyNumber: Boolean) = apply {
        this.option.useOnlyNumber = useOnlyNumber
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
    }


    fun copy(inject: HongLabelSelectInputOption): HongLabelSelectInputBuilder {
        return HongLabelSelectInputBuilder()
            .width(inject.width)
            .height(inject.height)
            .margin(inject.margin)
            .padding(inject.padding)
            .onClick(inject.click)
            .labelOption(inject.labelOption)
            .label(inject.label)
            .description(inject.description)
            .textFieldOption(inject.textFieldOption)
            .inputText(inject.input)
            .placeholder(inject.placeholder)
            .textButtonOption(inject.textButtonOption)
            .buttonTextOption(inject.buttonTextOption)
            .buttonText(inject.buttonText)
            .selectPosition(inject.selectPosition)
            .selectList(inject.selectList)
            .useOnlyNumber(inject.useOnlyNumber)
            .useDirectCallback(inject.useDirectCallback)
            .showInput(inject.showInput)
            .pickerCallback(inject.pickerCallback)
            .inputCallback(inject.inputCallback)
    }
}