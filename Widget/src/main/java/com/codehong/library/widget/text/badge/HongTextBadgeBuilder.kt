package com.codehong.library.widget.text.badge

import com.codehong.library.widget.HongWidgetCommonBuilder
import com.codehong.library.widget.rule.HongBorderInfo
import com.codehong.library.widget.rule.HongShadowInfo
import com.codehong.library.widget.rule.radius.HongRadiusInfo
import com.codehong.library.widget.text.HongTextBuilder
import com.codehong.library.widget.text.HongTextOption

class HongTextBadgeBuilder : HongWidgetCommonBuilder<HongTextBadgeOption, HongTextBadgeBuilder> {

    override val builder: HongTextBadgeBuilder = this
    override val option: HongTextBadgeOption = HongTextBadgeOption()

    fun border(borderInfo: HongBorderInfo) = apply {
        option.border = borderInfo
    }

    fun radius(radiusInfo: HongRadiusInfo) = apply {
        option.radius = radiusInfo
    }

    fun shadow(shadow: HongShadowInfo) = apply {
        option.shadow = shadow
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
            .text(textOption.text ?: option.text)
            .applyOption()
    }

    fun copy(inject: HongTextBadgeOption): HongTextBadgeBuilder {
        return HongTextBadgeBuilder()
            .width(inject.width)
            .height(inject.height)
            .margin(inject.margin)
            .padding(inject.padding)
            .onClick(inject.click)
            .backgroundColor(inject.backgroundColorHex)
            .border(inject.border)
            .radius(inject.radius)
            .shadow(inject.shadow)
            .text(inject.text)
            .textOption(inject.textOption)
    }
}