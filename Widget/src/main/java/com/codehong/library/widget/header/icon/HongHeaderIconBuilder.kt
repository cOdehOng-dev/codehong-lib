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

    fun backIcon(@DrawableRes iconRes: Int) = apply {
        option.backIconRes = iconRes
    }

    fun backIconColor(color: HongColor) = apply {
        option.backIconColorHex = color.hex
    }

    fun backIconColor(colorHex: String) = apply {
        option.backIconColorHex = colorHex
    }

    fun onBack(onBack: () -> Unit) = apply {
        option.onBackClick = onBack
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
            .apply {
                inject.backIconRes?.let { backIcon(it) }
            }
            .backIconColor(inject.backIconColorHex)
            .onBack(inject.onBackClick)
            .backgroundColor(inject.backgroundColorHex)
    }
}
