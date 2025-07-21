package com.codehong.library.widget.text.unit

import com.codehong.library.widget.HongWidgetCommonBuilder
import com.codehong.library.widget.text.HongTextBuilder
import com.codehong.library.widget.text.HongTextOption

class HongTextUnitBuilder : HongWidgetCommonBuilder<HongTextUnitOption, HongTextUnitBuilder> {

    override val builder: HongTextUnitBuilder = this
    override val option: HongTextUnitOption = HongTextUnitOption()

    fun text(text: String?) = apply {
        option.text = text
        option.textOption = HongTextBuilder()
            .copy(option.textOption)
            .text(text ?: option.textOption.text)
            .applyOption()
    }
    fun textOption(textOption: HongTextOption) = apply {
        option.textOption = HongTextBuilder()
            .copy(textOption)
            .text(option.text ?: textOption.text)
            .applyOption()
    }

    fun unitText(unit: String?) = apply {
        option.unitText = unit
        option.unitTextOption = HongTextBuilder()
            .copy(option.textOption)
            .text(unit ?: option.unitTextOption.text)
            .applyOption()
    }

    fun useNumberDecimal(use: Boolean) = apply {
        option.textOption = HongTextBuilder()
            .copy(option.textOption)
            .useNumberDecimal(use)
            .applyOption()
    }

    fun copy(inject: HongTextUnitOption?): HongTextUnitBuilder {
        if (inject == null) return HongTextUnitBuilder()

        return HongTextUnitBuilder()
            .width(inject.width)
            .height(inject.height)
            .margin(inject.margin)
            .padding(inject.padding)
            .onClick(inject.click)
            .text(inject.text)
            .textOption(inject.textOption)
            .unitText(inject.unitText)
            .backgroundColor(inject.backgroundColorHex)
    }
}