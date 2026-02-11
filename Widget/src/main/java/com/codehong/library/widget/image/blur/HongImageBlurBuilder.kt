package com.codehong.library.widget.image.blur

import com.codehong.library.widget.HongWidgetCommonBuilder
import com.codehong.library.widget.rule.HongScaleType
import com.codehong.library.widget.rule.radius.HongRadiusInfo

class HongImageBlurBuilder : HongWidgetCommonBuilder<HongImageBlurOption, HongImageBlurBuilder> {

    override val builder: HongImageBlurBuilder = this
    override val option: HongImageBlurOption = HongImageBlurOption()

    fun imageInfo(imageInfo: Any?) = apply {
        option.imageInfo = imageInfo
    }

    fun scaleType(scaleType: HongScaleType) = apply {
        option.scaleType = scaleType
    }

    fun radius(radiusInfo: HongRadiusInfo) = apply {
        option.radius = radiusInfo
    }

    fun useShapeCircle(useShapeCircle: Boolean) = apply {
        option.useShapeCircle = useShapeCircle
    }

    fun blur(blur: Int) = apply {
        option.blur = blur
    }

    fun copy(inject: HongImageBlurOption?): HongImageBlurBuilder {
        if (inject == null) return HongImageBlurBuilder()
        return HongImageBlurBuilder()
            .width(inject.width)
            .height(inject.height)
            .margin(inject.margin)
            .padding(inject.padding)
            .radius(inject.radius)
            .useShapeCircle(inject.useShapeCircle)
            .imageInfo(inject.imageInfo)
            .scaleType(inject.scaleType)
            .blur(inject.blur)
    }
}