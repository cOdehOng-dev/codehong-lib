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
                .text(label)
                .applyOption()
        )
    }

    fun description(description: String?) = apply {
        this.option.description = description
        descriptionTextOption(
            HongTextBuilder()
                .copy(option.descriptionTextOption)
                .text(description)
                .applyOption()
        )
    }

    fun labelTextOption(option: HongTextOption?) = apply {
        this.option.labelTextOption = option ?: HongLabelOption.DEFAULT_LABEL_OPTION
    }

    fun descriptionTextOption(option: HongTextOption?) = apply {
        this.option.descriptionTextOption = option ?: HongLabelOption.DEFAULT_DESCRIPTION_OPTION
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