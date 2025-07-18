package com.codehong.library.widget.label.toggleswitch

import com.codehong.library.widget.HongWidgetCommonBuilder
import com.codehong.library.widget.label.HongLabelBuilder
import com.codehong.library.widget.label.HongLabelOption
import com.codehong.library.widget.text.HongTextBuilder
import com.codehong.library.widget.text.HongTextOption
import com.codehong.library.widget.toggleswitch.HongSwitchOption

class HongLabelSwitchBuilder : HongWidgetCommonBuilder<HongLabelSwitchOption, HongLabelSwitchBuilder> {

    override val builder: HongLabelSwitchBuilder = this
    override val option: HongLabelSwitchOption = HongLabelSwitchOption()

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
            .copy(labelTextOption ?: HongLabelSwitchOption.DEFAULT_LABEL_OPTION)
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
            .copy(descriptionTextOption ?: HongLabelSwitchOption.DEFAULT_DESCRIPTION_OPTION)
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

    fun switchOption(switchOption: HongSwitchOption?) = apply {
        option.switchOption = switchOption ?: HongLabelSwitchOption.DEFAULT_SWITCH_OPTION
    }

    fun copy(inject: HongLabelSwitchOption?): HongLabelSwitchBuilder {
        if (inject == null) return HongLabelSwitchBuilder()

        return HongLabelSwitchBuilder()
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
            .switchOption(inject.switchOption)
    }
}