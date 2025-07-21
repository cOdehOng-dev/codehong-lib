package com.codehong.library.widget.button.select

import com.codehong.library.widget.HongWidgetCommonBuilder
import com.codehong.library.widget.button.text.HongTextButtonOption
import com.codehong.library.widget.rule.HongLayoutParam

class HongSelectButtonBuilder : HongWidgetCommonBuilder<HongSelectButtonOption, HongSelectButtonBuilder> {

    override val builder: HongSelectButtonBuilder = this
    override val option: HongSelectButtonOption = HongSelectButtonOption()

    override fun width(width: Int?): HongSelectButtonBuilder = apply {
        this.option.width = HongLayoutParam.MATCH_PARENT.value
    }

    fun negativeTextButtonOption(option: HongTextButtonOption?) = apply {
        this.option.negativeTextButtonOption = option ?: HongSelectButtonOption.DEFAULT_NEGATIVE_TEXT_BUTTON_OPTION
    }

    fun positiveTextButtonOption(option: HongTextButtonOption?) = apply {
        this.option.positiveTextButtonOption = option ?: HongSelectButtonOption.DEFAULT_POSITIVE_TEXT_BUTTON_OPTION
    }

    fun negativeClick(click: (() -> Unit)?) = apply {
        this.option.negativeClick = click
    }

    fun positiveClick(click: (() -> Unit)?) = apply {
        this.option.positiveClick = click
    }

    fun copy(inject: HongSelectButtonOption?): HongSelectButtonBuilder {
        if (inject == null) return HongSelectButtonBuilder()
        return HongSelectButtonBuilder()
            .width(inject.width)
            .height(inject.height)
            .margin(inject.margin)
            .padding(inject.padding)
            .onClick(inject.click)
            .backgroundColor(inject.backgroundColorHex)
            .negativeTextButtonOption(inject.negativeTextButtonOption)
            .positiveTextButtonOption(inject.positiveTextButtonOption)
            .negativeClick(inject.negativeClick)
            .positiveClick(inject.positiveClick)
    }
}