package com.codehong.library.widget.button.text

import com.codehong.library.widget.HongWidgetCommonBuilder
import com.codehong.library.widget.rule.HongBorderInfo
import com.codehong.library.widget.rule.HongShadowInfo
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.HongState
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.radius.HongRadiusInfo
import com.codehong.library.widget.rule.typo.HongTypo

class HongButtonTextBuilder : HongWidgetCommonBuilder<HongButtonTextOption, HongButtonTextBuilder> {

    override val builder: HongButtonTextBuilder = this
    override val option: HongButtonTextOption = HongButtonTextOption()

    override fun padding(padding: HongSpacingInfo) = apply {
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

    fun state(state: HongState) = apply {
        option.state = state
    }

    fun text(text: String?) = apply {
        option.text = text ?: ""
    }

    fun textTypo(typo: HongTypo) = apply {
        option.textTypo = typo
    }

    fun textColor(color: HongColor) = apply {
        option.textColorHex = color.hex
    }

    fun textColor(colorHex: String) = apply {
        option.textColorHex = colorHex
    }


    fun copy(inject: HongButtonTextOption?): HongButtonTextBuilder {
        if (inject == null) return HongButtonTextBuilder()

        return HongButtonTextBuilder()
            .width(inject.width)
            .height(inject.height)
            .margin(inject.margin)
            .padding(inject.padding)
            .onClick(inject.click)
            .radius(inject.radius)
            .shadow(inject.shadow)
            .backgroundColor(inject.backgroundColorHex)
            .border(inject.border)
            .text(inject.text)
            .textTypo(inject.textTypo)
            .textColor(inject.textColorHex)
            .state(inject.state)
    }
}