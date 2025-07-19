package com.codehong.library.widget.button.text

import com.codehong.library.widget.HongWidgetCommonBuilder
import com.codehong.library.widget.rule.HongBorderInfo
import com.codehong.library.widget.rule.HongShadowInfo
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.HongState
import com.codehong.library.widget.rule.radius.HongRadiusInfo
import com.codehong.library.widget.text.HongTextOption

class HongTextButtonBuilder : HongWidgetCommonBuilder<HongTextButtonOption, HongTextButtonBuilder> {

    override val builder: HongTextButtonBuilder = this
    override val option: HongTextButtonOption = HongTextButtonOption()

    override fun padding(padding: HongSpacingInfo): HongTextButtonBuilder = apply {
        option.padding = HongSpacingInfo()
    }

    fun radius(radiusInfo: HongRadiusInfo) = apply {
        option.radius = radiusInfo
    }

    fun border(border: HongBorderInfo) = apply {
        option.border = border
    }

    fun shadow(shadowInfo: HongShadowInfo?) = apply {
        option.shadow = shadowInfo ?: HongShadowInfo()
    }

    fun textOption(option: HongTextOption?) = apply {
        this.option.textOption = option ?: HongTextButtonOption.DEFAULT_TEXT_OPTION
    }

    fun state(state: HongState) = apply {
        option.state = state
    }

    fun copy(inject: HongTextButtonOption?): HongTextButtonBuilder {
        if (inject == null) return HongTextButtonBuilder()
        return HongTextButtonBuilder()
            .width(inject.width)
            .height(inject.height)
            .margin(inject.margin)
            .padding(inject.padding)
            .onClick(inject.click)
            .radius(inject.radius)
            .shadow(inject.shadow)
            .textOption(inject.textOption)
            .backgroundColor(inject.backgroundColor)
            .backgroundColor(inject.backgroundColorHex)
            .border(inject.border)
            .state(inject.state)
    }
}