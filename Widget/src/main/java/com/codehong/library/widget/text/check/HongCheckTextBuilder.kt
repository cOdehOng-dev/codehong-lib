package com.codehong.library.widget.text.check

import com.codehong.library.widget.HongWidgetCommonBuilder
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.text.HongTextBuilder
import com.codehong.library.widget.text.HongTextOption

class HongCheckTextBuilder : HongWidgetCommonBuilder<HongCheckTextOption, HongCheckTextBuilder> {

    override val builder: HongCheckTextBuilder = this
    override val option: HongCheckTextOption = HongCheckTextOption()

    fun size(size: Int) = apply {
        option.size = size
    }
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

    fun checkColor(color: HongColor) = apply {
        option.checkColor = color
        checkColor(color.hex)
    }

    fun checkColor(colorHex: String) = apply {
        option.checkColorHex = colorHex
    }

    fun uncheckColor(color: HongColor) = apply {
        option.uncheckColor = color
        uncheckColor(color.hex)
    }
    fun uncheckColor(colorHex: String) = apply {
        option.uncheckColorHex = colorHex
    }

    fun checkState(checkState: Boolean) = apply {
        option.checkState = checkState
    }

    fun onCheck(onCheck: ((Boolean) -> Unit)?) = apply {
        option.onCheck = onCheck
    }

    fun copy(inject: HongCheckTextOption?): HongCheckTextBuilder {
        if (inject == null) return HongCheckTextBuilder()
        return HongCheckTextBuilder()
            .width(inject.width)
            .height(inject.height)
            .margin(inject.margin)
            .padding(inject.padding)
            .onClick(inject.click)
            .backgroundColor(inject.backgroundColor)
            .backgroundColor(inject.backgroundColorHex)
            .text(inject.text)
            .textOption(inject.textOption)
            .checkColor(inject.checkColor)
            .checkColor(inject.checkColorHex)
            .uncheckColor(inject.uncheckColor)
            .uncheckColor(inject.uncheckColorHex)
            .checkState(inject.checkState)
            .size(inject.size)
            .onCheck(inject.onCheck)
    }

}