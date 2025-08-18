package com.codehong.library.widget.text.count

import com.codehong.library.widget.HongWidgetCommonBuilder
import com.codehong.library.widget.rule.HongCountType
import com.codehong.library.widget.rule.button.HongButtonIconType
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.typo.HongTypo

class HongTextCountBuilder : HongWidgetCommonBuilder<HongTextCountOption, HongTextCountBuilder> {

    override val builder: HongTextCountBuilder = this
    override val option: HongTextCountOption = HongTextCountOption()

    fun countType(countType: HongCountType) = apply {
        option.countType = countType
    }

    fun startCount(count: Number) = apply {
        option.startCount = count
    }

    fun countTypo(typo: HongTypo) = apply {
        option.countTypo = typo
    }

    fun countColor(color: HongColor) = apply {
        option.countColorHex = color.hex
    }
    fun countColor(colorHex: String) = apply {
        option.countColorHex = colorHex
    }

    fun unitText(unitText: String) = apply {
        option.unitText = unitText
    }

    fun unitTypo(typo: HongTypo) = apply {
        option.unitTypo = typo
    }

    fun unitColor(colorHex: String) = apply {
        option.unitColorHex = colorHex
    }

    fun minCount(minCount: Number) = apply {
        option.minCount = minCount
    }

    fun maxCount(maxCount: Number?) = apply {
        option.maxCount = maxCount
    }

    fun amount(amount: Number) = apply {
        option.amount = amount
    }

    fun buttonType(buttonType: HongButtonIconType) = apply {
        option.buttonType = buttonType
    }

    fun onCountChange(onCountChange: (String) -> Unit) = apply {
        option.onCountChange = onCountChange
    }

    fun copy(inject: HongTextCountOption?): HongTextCountBuilder {
        if (inject == null) return HongTextCountBuilder()
        return HongTextCountBuilder()
            .margin(inject.margin)
            .padding(inject.padding)
            .countType(inject.countType)
            .startCount(inject.startCount)
            .countTypo(inject.countTypo)
            .countColor(inject.countColorHex)
            .unitText(inject.unitText)
            .unitTypo(inject.unitTypo)
            .unitColor(inject.unitColorHex)
            .minCount(inject.minCount)
            .maxCount(inject.maxCount)
            .amount(inject.amount)
            .buttonType(inject.buttonType)
            .onCountChange(inject.onCountChange)
    }
}