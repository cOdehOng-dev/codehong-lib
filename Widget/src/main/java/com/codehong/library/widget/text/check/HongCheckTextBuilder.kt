package com.codehong.library.widget.text.check

import com.codehong.library.widget.HongWidgetCommonBuilder
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.text.HongTextBuilder
import com.codehong.library.widget.text.HongTextOption

class HongCheckTextBuilder : HongWidgetCommonBuilder<HongCheckTextOption, HongCheckTextBuilder> {

    override val builder: HongCheckTextBuilder = this
    override val option: HongCheckTextOption = HongCheckTextOption()

    override fun padding(padding: HongSpacingInfo) = apply {
        option.padding = HongSpacingInfo()
    }

    fun checkSize(size: Int) = apply {
        option.checkSize = size
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
        checkColor(color.hex)
    }

    fun checkColor(colorHex: String) = apply {
        option.checkColor = colorHex
    }

    fun uncheckColor(color: HongColor) = apply {
        uncheckColor(color.hex)
    }
    fun uncheckColor(colorHex: String) = apply {
        option.uncheckColor = colorHex
    }

    fun checkState(checkState: Boolean) = apply {
        option.checkState = checkState
    }

    fun onCheck(onCheck: ((Boolean) -> Unit)?) = apply {
        option.onCheck = onCheck
    }

    fun arrowSize(size: Int) = apply {
        option.arrowSize = size
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
            .uncheckColor(inject.uncheckColor)
            .checkState(inject.checkState)
            .checkSize(inject.checkSize)
            .onCheck(inject.onCheck)
            .arrowSize(inject.arrowSize)
    }

}