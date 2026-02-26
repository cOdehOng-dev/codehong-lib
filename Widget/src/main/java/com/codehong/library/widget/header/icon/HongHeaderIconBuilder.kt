package com.codehong.library.widget.header.icon

import androidx.annotation.DrawableRes
import com.codehong.library.widget.HongWidgetCommonBuilder
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.typo.HongTypo

class HongHeaderIconBuilder : HongWidgetCommonBuilder<HongHeaderIconOption, HongHeaderIconBuilder> {

    override val builder: HongHeaderIconBuilder = this
    override val option: HongHeaderIconOption = HongHeaderIconOption()

    fun title(title: String?) = apply {
        option.title = title
    }

    fun titleTypo(typo: HongTypo) = apply {
        option.titleTypo = typo
    }

    fun titleColor(color: HongColor) = apply {
        option.titleColorHex = color.hex
    }

    fun titleColor(colorHex: String) = apply {
        option.titleColorHex = colorHex
    }

    fun icon(@DrawableRes iconRes: Int?) = apply {
        option.iconRes = iconRes
    }

    fun iconColor(color: HongColor) = apply {
        option.iconColorHex = color.hex
    }

    fun iconColor(colorHex: String) = apply {
        option.iconColorHex = colorHex
    }

    fun onClickBack(onBack: () -> Unit) = apply {
        option.onClickBack = onBack
    }

    fun copy(inject: HongHeaderIconOption): HongHeaderIconBuilder {
        return HongHeaderIconBuilder()
            .width(inject.width)
            .height(inject.height)
            .margin(inject.margin)
            .padding(inject.padding)
            .onClick(inject.click)
            .title(inject.title)
            .titleColor(inject.titleColorHex)
            .titleTypo(inject.titleTypo)
            .icon(inject.iconRes)
            .iconColor(inject.iconColorHex)
            .onClickBack(inject.onClickBack)
            .backgroundColor(inject.backgroundColorHex)
    }
}
