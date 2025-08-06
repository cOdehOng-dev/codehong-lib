package com.codehong.library.widget.button.icon

import com.codehong.library.widget.HongWidgetCommonBuilder
import com.codehong.library.widget.rule.HongBorderInfo
import com.codehong.library.widget.rule.HongClickState
import com.codehong.library.widget.rule.button.HongButtonIconType
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.radius.HongRadiusInfo

class HongIconButtonBuilder : HongWidgetCommonBuilder<HongIconButtonOption, HongIconButtonBuilder> {

    override val builder: HongIconButtonBuilder = this
    override val option: HongIconButtonOption = HongIconButtonOption()

    fun buttonType(type: HongButtonIconType) = apply {
        option.buttonType = type
    }

    fun iconColor(color: HongColor) = apply {
        option.iconColorHex = color.hex
    }

    fun iconColor(colorHex: String) = apply {
        option.iconColorHex = colorHex
    }

    fun state(state: HongClickState) = apply {
        option.state = state
    }

    fun iconResId(resId: Int) = apply {
        option.iconResId = resId
    }

    fun border(border: HongBorderInfo) = apply {
        option.border = border
    }
    fun radius(radiusInfo: HongRadiusInfo) = apply {
        option.radius = radiusInfo
    }

    fun useShapeCircle(useShapeCircle: Boolean) = apply {
        option.useShapeCircle = useShapeCircle
    }

    fun copy(inject: HongIconButtonOption?): HongIconButtonBuilder {
        if (inject == null) return HongIconButtonBuilder()

        return HongIconButtonBuilder()
            .width(inject.width)
            .height(inject.height)
            .margin(inject.margin)
            .padding(inject.padding)
            .onClick(inject.click)
            .backgroundColor(inject.backgroundColorHex)
            .border(inject.border)
            .radius(inject.radius)
            .useShapeCircle(inject.useShapeCircle)
            .buttonType(inject.buttonType)
            .iconColor(inject.iconColorHex)
            .state(inject.state)
            .iconResId(inject.iconResId)

    }
}