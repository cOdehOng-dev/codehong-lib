package com.codehong.library.widget.checkbox

import com.codehong.library.widget.HongWidgetCommonBuilder
import com.codehong.library.widget.rule.HongBorderInfo
import com.codehong.library.widget.rule.HongState
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.radius.HongRadiusInfo

class HongCheckboxBuilder : HongWidgetCommonBuilder<HongCheckboxOption, HongCheckboxBuilder> {

    override val builder: HongCheckboxBuilder = this
    override val option: HongCheckboxOption = HongCheckboxOption()

    fun size(size: Int) = apply {
        option.size = size
    }

    fun checkedColor(color: HongColor) = apply {
        option.checkedColor = color
        checkedColor(color.hex)
    }
    fun checkedColor(colorHex: String) = apply {
        option.checkedColorHex = colorHex
    }

    fun checkmarkColor(color: HongColor) = apply {
        option.checkmarkColor = color
        checkmarkColor(color.hex)
    }
    fun checkmarkColor(colorHex: String) = apply {
        option.checkmarkColorHex = colorHex
    }

    fun border(borderInfo: HongBorderInfo) = apply {
        option.border = borderInfo
    }

    fun radius(radiusInfo: HongRadiusInfo) = apply {
        option.radius = radiusInfo
    }
    
    fun checkState(checkState: Boolean) = apply {
        option.checkState = checkState
    }

    fun enableState(enableState: HongState) = apply {
        option.enableState = enableState
    }

    fun useShapeCircle(useShapeCircle: Boolean) = apply {
        option.useShapeCircle = useShapeCircle
    }

    fun copy(inject: HongCheckboxOption): HongCheckboxBuilder {
        return HongCheckboxBuilder()
            .width(inject.width)
            .height(inject.height)
            .margin(inject.margin)
            .padding(inject.padding)
            .onClick(inject.click)
            .size(inject.size)
            .backgroundColor(inject.backgroundColorHex)
            .checkedColor(inject.checkedColor)
            .checkedColor(inject.checkedColorHex)
            .checkmarkColor(inject.checkmarkColor)
            .checkmarkColor(inject.checkmarkColorHex)
            .border(inject.border)
            .radius(inject.radius)
            .checkState(inject.checkState)
            .enableState(inject.enableState)
            .useShapeCircle(inject.useShapeCircle)
    }
}