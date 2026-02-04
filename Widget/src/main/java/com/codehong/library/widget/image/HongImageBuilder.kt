package com.codehong.library.widget.image

import coil.request.CachePolicy
import coil.size.Size
import com.codehong.library.widget.HongWidgetCommonBuilder
import com.codehong.library.widget.rule.HongBorderInfo
import com.codehong.library.widget.rule.HongScaleType
import com.codehong.library.widget.rule.HongShadowInfo
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.radius.HongRadiusInfo

class HongImageBuilder : HongWidgetCommonBuilder<HongImageOption, HongImageBuilder> {

    override val builder: HongImageBuilder = this
    override val option: HongImageOption = HongImageOption()

    fun imageInfo(imageInfo: Any?) = apply {
        option.imageInfo = imageInfo
    }

    fun radius(radiusInfo: HongRadiusInfo) = apply {
        option.radius = radiusInfo
    }

    fun border(border: HongBorderInfo) = apply {
        option.border = border
    }

    fun shadow(shadow: HongShadowInfo) = apply {
        option.shadow = shadow
    }

    fun scaleType(scaleType: HongScaleType) = apply {
        option.scaleType = scaleType
    }

    fun placeholder(placeholder: Int?) = apply {
        option.placeholder = placeholder
    }

    fun error(error: Int?) = apply {
        option.error = error
    }

    fun useShapeCircle(useShapeCircle: Boolean) = apply {
        option.useShapeCircle = useShapeCircle
    }

    fun onLoading(onLoading: (() -> Unit)?) = apply {
        option.onLoading = onLoading
    }

    fun onSuccess(onSuccess: (() -> Unit)?) = apply {
        option.onSuccess = onSuccess
    }

    fun onError(onError: (() -> Unit)?) = apply {
        option.onError = onError
    }

    fun memoryCache(memoryCache: CachePolicy) = apply {
        option.memoryCache = memoryCache
    }

    fun diskCache(diskCache: CachePolicy) = apply {
        option.diskCache = diskCache
    }

    fun imageColor(color: HongColor) = apply {
        option.imageColor = color.hex
    }
    fun imageColor(colorHex: String?) = apply {
        option.imageColor = colorHex
    }

    fun size(size: Size?) = apply {
        option.size = size
    }

    fun crossFade(crossFade: Boolean) = apply {
        option.crossFade = crossFade
    }

    fun copy(inject: HongImageOption?): HongImageBuilder {
        if (inject == null) return HongImageBuilder()
        return HongImageBuilder()
            .width(inject.width)
            .height(inject.height)
            .margin(inject.margin)
            .padding(inject.padding)
            .onClick(inject.click)
            .radius(inject.radius)
            .shadow(inject.shadow)
            .backgroundColor(inject.backgroundColorHex)
            .border(inject.border)
            .useShapeCircle(inject.useShapeCircle)
            .imageInfo(inject.imageInfo)
            .placeholder(inject.placeholder)
            .error(inject.error)
            .onLoading(inject.onLoading)
            .onSuccess(inject.onSuccess)
            .onError(inject.onError)
            .scaleType(inject.scaleType)
            .memoryCache(inject.memoryCache)
            .diskCache(inject.diskCache)
            .imageColor(inject.imageColor)
            .size(inject.size)
            .crossFade(inject.crossFade)
    }
}