package com.codehong.library.widget.label.input

import com.codehong.library.widget.HongWidgetCommonBuilder
import com.codehong.library.widget.rule.HongScaleType
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.keyboard.HongKeyboardActionType
import com.codehong.library.widget.rule.keyboard.HongKeyboardType
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


    fun placeholder(placeholder: String?) = apply {
        option.placeholder = placeholder
    }

    fun placeholderColor(color: HongColor) = apply {
        option.placeholderColorHex = color.hex
    }
    fun placeholderColor(color: String) = apply {
        option.placeholderColorHex = color
    }

    fun placeholderTypo(typo: HongTypo) = apply {
        option.placeholderTypo = typo
    }

    fun input(input: String?) = apply {
        option.input = input
    }

    fun inputTypo(typo: HongTypo) = apply {
        option.inputTypo = typo
    }

    fun inputColor(color: HongColor) = apply {
        option.inputColorHex = color.hex
    }
    fun inputColor(color: String) = apply {
        option.inputColorHex = color
    }

    fun clearIconRes(resId: Int?) = apply {
        this.option.clearIconRes = resId
    }
    fun clearIconSize(size: Int) = apply {
        this.option.clearIconSize = size
    }
    fun clearIconScaleType(scaleType: HongScaleType) = apply {
        this.option.clearIconScaleType = scaleType
    }
    fun clearIconMargin(margin: HongSpacingInfo) = apply {
        this.option.clearIconMargin = margin
    }

    fun keyboardOption(keyboardOption: Pair<HongKeyboardType, HongKeyboardActionType>?) = apply {
        this.option.keyboardOption = keyboardOption ?: HongTextFieldOption.DEFAULT_KEYBOARD_OPTION
    }

    fun onTextChanged(onTextChanged: ((String) -> Unit)) = apply {
        option.onTextChanged = onTextChanged
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
            .placeholder(inject.placeholder)
            .placeholderColor(inject.placeholderColorHex)
            .placeholderTypo(inject.placeholderTypo)
            .input(inject.input)
            .inputTypo(inject.inputTypo)
            .inputColor(inject.inputColorHex)
            .clearIconRes(inject.clearIconRes)
            .clearIconSize(inject.clearIconSize)
            .clearIconMargin(inject.clearIconMargin)
            .clearIconScaleType(inject.clearIconScaleType)
            .keyboardOption(inject.keyboardOption)
            .onTextChanged(inject.onTextChanged)
    }
}