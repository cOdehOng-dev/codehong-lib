package com.codehong.library.widget.button.select

import com.codehong.library.widget.HongWidgetCommonBuilder
import com.codehong.library.widget.rule.HongLayoutParam
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.typo.HongTypo

class HongButtonSelectBuilder : HongWidgetCommonBuilder<HongButtonSelectOption, HongButtonSelectBuilder> {

    override val builder: HongButtonSelectBuilder = this
    override val option: HongButtonSelectOption = HongButtonSelectOption()

    override fun width(width: Int?): HongButtonSelectBuilder = apply {
        this.option.width = HongLayoutParam.MATCH_PARENT.value
    }

    fun negativeText(text: String?) = apply {
        option.negativeText = text ?: HongButtonSelectOption.DEFAULT_NEGATIVE_TEXT
    }

    fun negativeTextTypo(typo: HongTypo) = apply {
        option.negativeTextTypo = typo
    }

    fun negativeTextColor(color: HongColor) = apply {
        negativeTextColor(color.hex)
    }

    fun negativeTextColor(colorHex: String) = apply {
        option.negativeTextColorHex = colorHex
    }

    fun negativeBorderColor(color: HongColor) = apply {
        option.negativeBorderColorHex = color.hex
    }

    fun negativeBorderColor(colorHex: String) = apply {
        option.negativeBorderColorHex = colorHex
    }


    fun positiveText(text: String?) = apply {
        option.positiveText = text ?: HongButtonSelectOption.DEFAULT_POSITIVE_TEXT
    }

    fun positiveTextTypo(typo: HongTypo) = apply {
        option.positiveTextTypo = typo
    }

    fun positiveTextColor(color: HongColor) = apply {
        positiveTextColor(color.hex)
    }

    fun positiveTextColor(colorHex: String) = apply {
        option.positiveTextColorHex = colorHex
    }

    fun positiveBackgroundColor(color: HongColor) = apply {
        option.positiveBackgroundColorHex = color.hex
    }

    fun positiveBackgroundColor(colorHex: String) = apply {
        option.positiveBackgroundColorHex = colorHex
    }

    fun onNegativeClick(click: (() -> Unit)?) = apply {
        option.onClickNegative = click
    }

    fun onPositiveClick(click: (() -> Unit)?) = apply {
        option.onClickPositive = click
    }

    fun copy(inject: HongButtonSelectOption?): HongButtonSelectBuilder {
        if (inject == null) return HongButtonSelectBuilder()

        return HongButtonSelectBuilder()
            .width(inject.width)
            .height(inject.height)
            .margin(inject.margin)
            .padding(inject.padding)
            .onClick(inject.click)
            .backgroundColor(inject.backgroundColorHex)
            .negativeText(inject.negativeText)
            .negativeTextTypo(inject.negativeTextTypo)
            .negativeTextColor(inject.negativeTextColorHex)
            .negativeBorderColor(inject.negativeBorderColorHex)
            .positiveText(inject.positiveText)
            .positiveTextTypo(inject.positiveTextTypo)
            .positiveTextColor(inject.positiveTextColorHex)
            .positiveBackgroundColor(inject.positiveBackgroundColorHex)
            .onNegativeClick(inject.onClickNegative)
            .onPositiveClick(inject.onClickPositive)
    }
}