package com.codehong.library.widget.label.input

import com.codehong.library.widget.HongWidgetCommonBuilder
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.typo.HongTypo
import com.codehong.library.widget.textfield.HongTextFieldOption

class HongLabelInputBuilder : HongWidgetCommonBuilder<HongLabelInputOption, HongLabelInputBuilder> {

    override val builder: HongLabelInputBuilder = this
    override val option: HongLabelInputOption = HongLabelInputOption()

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
            .labelColor(inject.labelColorHex)
            .labelTypo(inject.labelTypo)
            .description(inject.description)
            .descriptionColor(inject.descriptionColorHex)
            .descriptionTypo(inject.descriptionTypo)

            .textFieldOption(inject.textFieldOption)
    }
}