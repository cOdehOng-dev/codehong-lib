package com.codehong.library.widget.toggleswitch

import com.codehong.library.widget.HongWidgetCommonBuilder
import com.codehong.library.widget.HongWidgetCommonOption
import com.codehong.library.widget.rule.HongBorderInfo
import com.codehong.library.widget.rule.color.HongColor

class HongSwitchBuilder : HongWidgetCommonBuilder<HongSwitchOption, HongSwitchBuilder> {

    override val builder: HongSwitchBuilder = this
    override val option: HongSwitchOption = HongSwitchOption()

    fun onColor(color: HongColor) = apply {
        option.onColor = color
        onColor(color.hex)
    }

    fun onColor(colorHex: String) = apply {
        option.onColorHex = colorHex
    }

    fun offColor(color: HongColor) = apply {
        option.offColor = color
        offColor(color.hex)
    }

    fun offColor(colorHex: String) = apply {
        option.offColorHex = colorHex
    }

    fun cursorSize(size: Int) = apply {
        option.cursorSize = size
    }

    fun cursorHorizontalMargin(padding: Int) = apply {
        option.cursorHorizontalMargin = padding
    }

    fun cursorColor(color: HongColor?) = apply {
        option.cursorColor = color
        color?.hex?.let { cursorColor(it) }
    }

    fun cursorColor(colorHex: String) = apply {
        option.cursorColorHex = colorHex
    }

    fun initialState(isOn: Boolean) = apply {
        option.initialState = isOn
    }

    fun switchClick(click:((HongWidgetCommonOption, Boolean) -> Unit)?) = apply {
        option.switchClick = click
    }

    fun border(borderInfo: HongBorderInfo) = apply {
        option.border = borderInfo
    }

    fun copy(inject: HongSwitchOption): HongSwitchBuilder {
        return HongSwitchBuilder()
            .width(inject.width)
            .height(inject.height)
            .margin(inject.margin)
            .padding(inject.padding)
            .onClick(inject.click)
            .backgroundColor(inject.backgroundColorHex)
            .onColor(inject.onColor)
            .onColor(inject.onColorHex)
            .offColor(inject.offColor)
            .offColor(inject.offColorHex)
            .cursorSize(inject.cursorSize)
            .cursorHorizontalMargin(inject.cursorHorizontalMargin)
            .cursorColor(inject.cursorColor)
            .cursorColor(inject.cursorColorHex)
            .initialState(inject.initialState)
            .switchClick(inject.switchClick)
            .border(inject.border)
    }
}