package com.codehong.library.widget.label.toggleswitch

import com.codehong.library.widget.HongWidgetCommonBuilder
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.typo.HongTypo
import com.codehong.library.widget.toggleswitch.HongSwitchOption

class HongLabelSwitchBuilder : HongWidgetCommonBuilder<HongLabelSwitchOption, HongLabelSwitchBuilder> {

    override val builder: HongLabelSwitchBuilder = this
    override val option: HongLabelSwitchOption = HongLabelSwitchOption()

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
            .switchOption(inject.switchOption)

            .label(inject.label)
            .labelColor(inject.labelColorHex)
            .labelTypo(inject.labelTypo)
            .description(inject.description)
            .descriptionColor(inject.descriptionColorHex)
            .descriptionTypo(inject.descriptionTypo)
    }
}