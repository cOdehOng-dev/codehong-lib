package com.codehong.library.widget.icon

import com.codehong.library.widget.HongWidgetCommonBuilder
import com.codehong.library.widget.rule.HongScaleType
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.icon.HongIconType

class HongIconBuilder  : HongWidgetCommonBuilder<HongIconOption, HongIconBuilder> {

    override val builder: HongIconBuilder = this
    override val option: HongIconOption = HongIconOption()

    fun iconType(iconType: HongIconType) = apply {
        option.iconType = iconType
    }

    fun iconResId(iconResId: Int?) = apply {
        option.iconResId = iconResId
    }

    fun iconColor(iconColor: HongColor) = apply {
        option.iconColorHex = iconColor.hex
    }

    fun iconColor(iconColorHex: String) = apply {
        option.iconColorHex = iconColorHex
    }

    fun iconScaleType(iconScaleType: HongScaleType) = apply {
        option.iconScaleType = iconScaleType
    }

    fun copy(inject: HongIconOption?): HongIconBuilder {
        if (inject == null) {
            return HongIconBuilder()
        }

        return HongIconBuilder()
            .margin(inject.margin)
            .padding(inject.padding)
            .onClick(inject.click)
            .iconType(inject.iconType)
            .iconResId(inject.iconResId)
            .iconColor(inject.iconColorHex)
            .iconScaleType(inject.iconScaleType)
            .backgroundColor(inject.backgroundColorHex)
    }
}