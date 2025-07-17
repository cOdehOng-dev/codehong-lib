package com.codehong.library.widget.button

import com.codehong.library.widget.HongWidgetCommonBuilder
import com.codehong.library.widget.rule.HongBorderInfo
import com.codehong.library.widget.rule.HongShadowInfo
import com.codehong.library.widget.rule.radius.HongRadiusInfo
import com.codehong.library.widget.text.HongTextBuilder
import com.codehong.library.widget.text.HongTextOption

class HongTextButtonBuilder : HongWidgetCommonBuilder<HongTextButtonOption, HongTextButtonBuilder> {

    override val builder: HongTextButtonBuilder = this
    override val option: HongTextButtonOption = HongTextButtonOption()

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
        this.option.textOption = option
            ?: HongTextBuilder()
                .typography(HongTextButtonOption.DEFAULT_TEXT_TYPO)
                .color(HongTextButtonOption.DEFAULT_TEXT_COLOR)
                .applyOption()
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
    }
}