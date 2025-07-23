package com.codehong.library.widget.text.badge

import com.codehong.library.widget.HongWidgetCommonBuilder
import com.codehong.library.widget.rule.HongBorderInfo
import com.codehong.library.widget.rule.HongShadowInfo
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.radius.HongRadiusInfo
import com.codehong.library.widget.rule.typo.HongTypo

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
    }

    fun textColor(textColor: HongColor) = apply {
        option.textColorHex = textColor.hex
    }
    fun textColor(textColorHex: String?) = apply {
        option.textColorHex = textColorHex
    }

    fun textTypo(typo: HongTypo) = apply {
        option.textTypography = typo
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
            .textColor(inject.textColorHex)
            .textTypo(inject.textTypography)
    }
}