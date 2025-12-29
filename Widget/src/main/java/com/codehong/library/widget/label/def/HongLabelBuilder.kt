package com.codehong.library.widget.label.def

import com.codehong.library.widget.HongWidgetCommonBuilder
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.typo.HongTypo

class HongLabelBuilder : HongWidgetCommonBuilder<HongLabelOption, HongLabelBuilder> {

    override val builder: HongLabelBuilder = this
    override val option: HongLabelOption = HongLabelOption()

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

    fun copy(inject: HongLabelOption?): HongLabelBuilder {
        if (inject == null) return HongLabelBuilder()

        return HongLabelBuilder()
            .width(inject.width)
            .height(inject.height)
            .margin(inject.margin)
            .padding(inject.padding)
            .onClick(inject.click)
            .backgroundColor(inject.backgroundColorHex)

            .label(inject.label)
            .labelColor(inject.labelColorHex)
            .labelTypo(inject.labelTypo)
            .description(inject.description)
            .descriptionColor(inject.descriptionColorHex)
            .descriptionTypo(inject.descriptionTypo)
    }
}