package com.codehong.library.widget.badge

import com.codehong.library.widget.HongWidgetCommonBuilder
import com.codehong.library.widget.rule.HongBorderInfo
import com.codehong.library.widget.rule.HongShadowInfo
import com.codehong.library.widget.rule.radius.HongRadiusInfo
import com.codehong.library.widget.text.HongTextOption

class HongBadgeTextBuilder : HongWidgetCommonBuilder<HongBadgeTextOption, HongBadgeTextBuilder> {

    override val builder: HongBadgeTextBuilder = this
    override val option: HongBadgeTextOption = HongBadgeTextOption()

    fun border(borderInfo: HongBorderInfo) = apply {
        option.border = borderInfo
    }

    fun radius(radiusInfo: HongRadiusInfo) = apply {
        option.radius = radiusInfo
    }

    fun shadow(shadow: HongShadowInfo) = apply {
        option.shadow = shadow
    }

    fun textOption(option: HongTextOption?) = apply {
        this.option.textOption = option ?: HongBadgeTextOption.DEFAULT_TEXT_OPTION
    }

    fun copy(inject: HongBadgeTextOption): HongBadgeTextBuilder {
        return HongBadgeTextBuilder()
            .width(inject.width)
            .height(inject.height)
            .margin(inject.margin)
            .padding(inject.padding)
            .onClick(inject.click)
            .backgroundColor(inject.backgroundColor)
            .backgroundColor(inject.backgroundColorHex)
            .border(inject.border)
            .radius(inject.radius)
            .shadow(inject.shadow)
            .textOption(inject.textOption)
    }
}