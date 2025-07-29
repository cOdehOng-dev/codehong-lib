package com.codehong.library.widget.label.checkbox

import com.codehong.library.widget.HongWidgetCommonBuilder
import com.codehong.library.widget.checkbox.HongCheckboxBuilder
import com.codehong.library.widget.checkbox.HongCheckboxOption
import com.codehong.library.widget.rule.HongPosition
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.typo.HongTypo

class HongLabelCheckboxBuilder : HongWidgetCommonBuilder<HongLabelCheckboxOption, HongLabelCheckboxBuilder> {

    override val builder: HongLabelCheckboxBuilder = this
    override val option: HongLabelCheckboxOption = HongLabelCheckboxOption()

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
            .checkState(inject.isChecked)
            .checkboxSize(inject.checkboxSize)
            .checkboxOption(inject.checkboxOption)
            .checkboxPosition(inject.checkboxPosition)


            .label(inject.label)
            .labelColor(inject.labelColorHex)
            .labelTypo(inject.labelTypo)
            .description(inject.description)
            .descriptionColor(inject.descriptionColorHex)
            .descriptionTypo(inject.descriptionTypo)
    }
}