package com.codehong.library.widget.label.input

import com.codehong.library.widget.HongWidgetCommonBuilder
import com.codehong.library.widget.label.HongLabelBuilder
import com.codehong.library.widget.label.HongLabelOption
import com.codehong.library.widget.label.checkbox.HongLabelCheckboxOption
import com.codehong.library.widget.text.HongTextBuilder
import com.codehong.library.widget.text.HongTextOption
import com.codehong.library.widget.textfield.HongTextFieldOption

class HongLabelInputBuilder : HongWidgetCommonBuilder<HongLabelInputOption, HongLabelInputBuilder> {

    override val builder: HongLabelInputBuilder = this
    override val option: HongLabelInputOption = HongLabelInputOption()

    fun label(label: String?) = apply {
        option.label = label
        labelOption(
            HongLabelBuilder()
                .copy(option.labelOption)
                .label(label ?: option.labelOption.labelTextOption.text)
                .applyOption()
        )
    }
    fun labelTextOption(labelTextOption: HongTextOption?) = apply{
        option.labelTextOption = HongTextBuilder()
            .copy(labelTextOption ?: HongLabelCheckboxOption.DEFAULT_LABEL_OPTION)
            .text(option.label ?: labelTextOption?.text)
            .applyOption()
        labelOption(
            HongLabelBuilder()
                .copy(option.labelOption)
                .labelTextOption(option.labelTextOption)
                .applyOption()
        )
    }

    fun description(description: String?) = apply {
        option.description = description
        labelOption(
            HongLabelBuilder()
                .copy(option.labelOption)
                .description(description ?: option.labelOption.description.toString())
                .applyOption()
        )
    }
    fun descriptionTextOption(descriptionTextOption: HongTextOption?) = apply {
        option.descriptionTextOption = HongTextBuilder()
            .copy(descriptionTextOption ?: HongLabelCheckboxOption.DEFAULT_DESCRIPTION_OPTION)
            .text(option.label ?: descriptionTextOption?.text)
            .applyOption()
        labelOption(
            HongLabelBuilder()
                .copy(option.labelOption)
                .descriptionTextOption(option.descriptionTextOption)
                .applyOption()
        )
    }

    fun labelOption(labelOption: HongLabelOption) = apply {
        option.labelOption = HongLabelBuilder()
            .copy(labelOption)
            .label(option.label ?: labelOption.labelTextOption.text)
            .labelTextOption(option.labelTextOption)
            .description(option.description ?: labelOption.descriptionTextOption.text)
            .descriptionTextOption(option.descriptionTextOption)
            .applyOption()
    }


    fun textFieldOption(textFieldOption: HongTextFieldOption) = apply {
        option.textFieldOption = textFieldOption
    }

    fun copy(inject: HongLabelInputOption): HongLabelInputBuilder {
        return HongLabelInputBuilder()
            .width(inject.width)
            .height(inject.height)
            .margin(inject.margin)
            .padding(inject.padding)
            .onClick(inject.click)
            .label(inject.label)
            .labelTextOption(inject.labelTextOption)
            .description(inject.description)
            .descriptionTextOption(inject.descriptionTextOption)
            .labelOption(inject.labelOption)
            .textFieldOption(inject.textFieldOption)
    }
}