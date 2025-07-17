package com.codehong.library.widget.label.toggle

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
                .labelTextOption(
                    HongTextBuilder()
                        .copy(option.labelOption.labelTextOption)
                        .text(label)
                        .applyOption()
                )
                .applyOption()
        )
    }
    fun labelTextOption(labelTextOption: HongTextOption) = apply{
        option.labelTextOption = labelTextOption
        labelOption(
            HongLabelBuilder()
                .copy(option.labelOption)
                .label(option.label)
                .labelTextOption(labelTextOption)
                .applyOption()
        )
    }


    fun description(description: String?) = apply {
        option.description = description
        labelOption(
            HongLabelBuilder()
                .copy(option.labelOption)
                .descriptionTextOption(
                    HongTextBuilder()
                        .copy(option.labelOption.descriptionTextOption)
                        .text(description)
                        .applyOption()
                )
                .applyOption()
        )
    }

    fun descriptionTextOption(descriptionTextOption: HongTextOption) = apply {
        option.descriptionTextOption = descriptionTextOption
        labelOption(
            HongLabelBuilder()
                .copy(option.labelOption)
                .description(option.description)
                .descriptionTextOption(descriptionTextOption)
                .applyOption()
        )
    }

    fun labelOption(labelOption: HongLabelOption) = apply {
        option.labelOption = labelOption
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