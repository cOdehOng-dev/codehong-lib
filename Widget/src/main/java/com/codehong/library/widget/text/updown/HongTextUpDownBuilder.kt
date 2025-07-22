package com.codehong.library.widget.text.updown

import com.codehong.library.widget.HongWidgetCommonBuilder
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.typo.HongTypo

class HongTextUpDownBuilder : HongWidgetCommonBuilder<HongTextUpDownOption, HongTextUpDownBuilder> {

    override val builder: HongTextUpDownBuilder = this
    override val option: HongTextUpDownOption = HongTextUpDownOption()


    fun amount(amount: Int) = apply {
        option.amount = amount
    }

    fun unit(unit: String?) = apply {
        option.unit = unit
    }

    fun displayTypo(typo: HongTypo) = apply {
        option.displayTypo = typo
    }

    fun displayColor(color: HongColor) = apply {
        option.displayColorHex = color.hex
    }
    fun displayColor(colorHex: String) = apply {
        option.displayColorHex = colorHex
    }

    fun gap(gap: Int) = apply {
        option.gap = gap
    }

    fun useDecimal(useDecimal: Boolean) = apply {
        option.useDecimal = useDecimal
    }

    fun onResult(onResult: (Int) -> Unit) = apply {
        option.onResult = onResult
    }

    fun buttonSize(size: Int) = apply {
        option.buttonSize = size
    }

    fun spaceBetweenButtonAndDisplay(gap: Int) = apply {
        option.spaceButtonAndDisplay = gap
    }

    fun copy(inject: HongTextUpDownOption?): HongTextUpDownBuilder {
        if (inject == null) return HongTextUpDownBuilder()

        return HongTextUpDownBuilder()
            .width(inject.width)
            .height(inject.height)
            .margin(inject.margin)
            .padding(inject.padding)
            .onClick(inject.click)
            .amount(inject.amount)
            .unit(inject.unit)
            .backgroundColor(inject.backgroundColorHex)
            .onResult(inject.onResult)
            .gap(inject.gap)
            .displayTypo(inject.displayTypo)
            .displayColor(inject.displayColorHex)
            .useDecimal(inject.useDecimal)
            .buttonSize(inject.buttonSize)
            .spaceBetweenButtonAndDisplay(inject.spaceButtonAndDisplay)
    }
}