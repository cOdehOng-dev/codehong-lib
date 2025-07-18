package com.codehong.library.widget.label

import com.codehong.library.widget.HongWidgetCommonBuilder
import com.codehong.library.widget.text.HongTextBuilder
import com.codehong.library.widget.text.HongTextOption

class HongLabelBuilder : HongWidgetCommonBuilder<HongLabelOption, HongLabelBuilder> {

    override val builder: HongLabelBuilder = this
    override val option: HongLabelOption = HongLabelOption()

    fun label(label: String?) = apply {
        option.label = label
        labelTextOption(
            HongTextBuilder()
                .copy(option.labelTextOption)
                .text(label ?: option.labelTextOption.text)
                .applyOption()
        )
    }

    fun description(description: String?) = apply {
        option.description = description
        descriptionTextOption(
            HongTextBuilder()
                .copy(option.descriptionTextOption)
                .text(description ?: option.descriptionTextOption.text)
                .applyOption()
        )
    }

    fun labelTextOption(labelTextOption: HongTextOption?) = apply {
        option.labelTextOption = HongTextBuilder()
            .copy(labelTextOption ?: HongLabelOption.DEFAULT_LABEL_OPTION)
            .text(option.label ?: labelTextOption?.text)
            .applyOption()
    }

    fun descriptionTextOption(descriptionTextOption: HongTextOption?) = apply {
        option.descriptionTextOption = HongTextBuilder()
            .copy(descriptionTextOption ?: HongLabelOption.DEFAULT_DESCRIPTION_OPTION)
            .text(option.description ?: descriptionTextOption?.text)
            .applyOption()
    }

    fun copy(inject: HongLabelOption): HongLabelBuilder {
        return HongLabelBuilder()
            .width(inject.width)
            .height(inject.height)
            .margin(inject.margin)
            .padding(inject.padding)
            .onClick(inject.click)
            .backgroundColor(inject.backgroundColor)
            .backgroundColor(inject.backgroundColorHex)
            .label(inject.label)
            .labelTextOption(inject.labelTextOption)
            .description(inject.description)
            .descriptionTextOption(inject.descriptionTextOption)
    }
}