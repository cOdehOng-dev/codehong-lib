package com.codehong.library.widget.header

import com.codehong.library.widget.HongWidgetCommonBuilder
import com.codehong.library.widget.text.HongTextOption

class HongCloseHeaderBuilder : HongWidgetCommonBuilder<HongCloseHeaderOption, HongCloseHeaderBuilder> {

    override val builder: HongCloseHeaderBuilder = this
    override val option: HongCloseHeaderOption = HongCloseHeaderOption()

    fun headerTitleTextOption(headerTitleTextOption: HongTextOption) = apply {
        option.headerTitleTextOption = headerTitleTextOption
    }

    fun close(onClose: (() -> Unit)?) = apply {
        option.close = onClose
    }


    fun copy(inject: HongCloseHeaderOption): HongCloseHeaderBuilder {
        return HongCloseHeaderBuilder()
            .width(inject.width)
            .height(inject.height)
            .margin(inject.margin)
            .padding(inject.padding)
            .onClick(inject.click)
            .headerTitleTextOption(inject.headerTitleTextOption)
            .close(inject.close)
            .backgroundColor(inject.backgroundColorHex)
    }

}