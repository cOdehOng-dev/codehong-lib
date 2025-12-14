package com.codehong.library.widget.header

import com.codehong.library.widget.HongWidgetCommonBuilder
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.typo.HongTypo

class HongHeaderCloseBuilder : HongWidgetCommonBuilder<HongHeaderCloseOption, HongHeaderCloseBuilder> {

    override val builder: HongHeaderCloseBuilder = this
    override val option: HongHeaderCloseOption = HongHeaderCloseOption()

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

    fun closeIconColor(color: HongColor) = apply {
        option.closeIconColorHex = color.hex
    }
    fun closeIconColor(colorHex: String) = apply {
        option.closeIconColorHex = colorHex
    }
    fun close(onClose: () -> Unit) = apply {
        option.onCloseClick = onClose
    }


    fun copy(inject: HongHeaderCloseOption): HongHeaderCloseBuilder {
        return HongHeaderCloseBuilder()
            .width(inject.width)
            .height(inject.height)
            .margin(inject.margin)
            .padding(inject.padding)
            .onClick(inject.click)
            .title(inject.title)
            .titleColor(inject.titleColorHex)
            .titleTypo(inject.titleTypo)
            .closeIconColor(inject.closeIconColorHex)
            .close(inject.onCloseClick)
            .backgroundColor(inject.backgroundColorHex)
    }

}