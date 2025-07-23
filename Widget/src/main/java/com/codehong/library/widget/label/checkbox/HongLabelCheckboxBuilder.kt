package com.codehong.library.widget.label.checkbox

import com.codehong.library.widget.HongWidgetCommonBuilder
import com.codehong.library.widget.checkbox.HongCheckboxBuilder
import com.codehong.library.widget.checkbox.HongCheckboxOption
import com.codehong.library.widget.label.HongLabelBuilder
import com.codehong.library.widget.label.HongLabelOption
import com.codehong.library.widget.rule.HongPosition
import com.codehong.library.widget.text.HongTextBuilder
import com.codehong.library.widget.text.HongTextOption

class HongLabelCheckboxBuilder : HongWidgetCommonBuilder<HongLabelCheckboxOption, HongLabelCheckboxBuilder> {

    override val builder: HongLabelCheckboxBuilder = this
    override val option: HongLabelCheckboxOption = HongLabelCheckboxOption()

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

    fun checkState(isChecked: Boolean?) = apply {
        option.isChecked = isChecked
        checkboxOption(
            HongCheckboxBuilder()
                .copy(option.checkboxOption)
                .checkState(isChecked ?: option.checkboxOption.checkState)
                .applyOption()
        )
    }
    fun checkboxSize(size: Int?) = apply {
        option.checkboxSize = size
        checkboxOption(
            HongCheckboxBuilder()
                .copy(option.checkboxOption)
                .size(size ?: option.checkboxOption.size)
                .applyOption()
        )
    }
    fun checkboxOption(checkboxOption: HongCheckboxOption) = apply {
        option.checkboxOption = HongCheckboxBuilder()
            .copy(checkboxOption)
            .size(option.checkboxSize ?: checkboxOption.size)
            .checkState(option.isChecked ?: checkboxOption.checkState)
            .applyOption()
    }

    fun checkboxPosition(position: HongPosition?) = apply {
        option.checkboxPosition = position ?: HongPosition.LEFT
    }


    fun copy(inject: HongLabelCheckboxOption?): HongLabelCheckboxBuilder {
        if (inject == null) return HongLabelCheckboxBuilder()
        return HongLabelCheckboxBuilder()
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
            .checkState(inject.isChecked)
            .checkboxSize(inject.checkboxSize)
            .checkboxOption(inject.checkboxOption)
            .checkboxPosition(inject.checkboxPosition)
    }
}