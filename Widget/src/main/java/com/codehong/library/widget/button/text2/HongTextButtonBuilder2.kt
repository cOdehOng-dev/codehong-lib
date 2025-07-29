package com.codehong.library.widget.button.text2

import com.codehong.library.widget.HongWidgetCommonBuilder
import com.codehong.library.widget.rule.HongBorderInfo
import com.codehong.library.widget.rule.HongShadowInfo
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.HongState
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.radius.HongRadiusInfo
import com.codehong.library.widget.rule.typo.HongTypo

class HongTextButtonBuilder2 : HongWidgetCommonBuilder<HongTextButtonOption2, HongTextButtonBuilder2> {

    override val builder: HongTextButtonBuilder2 = this
    override val option: HongTextButtonOption2 = HongTextButtonOption2()

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




    fun copy(inject: HongTextButtonOption2?): HongTextButtonBuilder2 {
        if (inject == null) return HongTextButtonBuilder2()

        return HongTextButtonBuilder2()
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