package com.codehong.library.widget.image

import coil.compose.AsyncImagePainter
import coil.request.CachePolicy
import com.codehong.library.widget.HongWidgetAdvanceOption
import com.codehong.library.widget.HongWidgetCommonOption
import com.codehong.library.widget.rule.HongBorderInfo
import com.codehong.library.widget.rule.HongLayoutParam
import com.codehong.library.widget.rule.HongScaleType
import com.codehong.library.widget.rule.HongShadowInfo
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.HongWidgetType
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.radius.HongRadiusInfo

data class HongImageOption(
    override val type: HongWidgetType = HongWidgetType.BADGE_TEXT
) : HongWidgetAdvanceOption {

    companion object {

        val DEFAULT_MEMORY_CACHE = CachePolicy.ENABLED
        val DEFAULT_DISK_CACHE = CachePolicy.ENABLED
    }

    override var isValidComponent: Boolean = true

    override var width: Int = HongLayoutParam.WRAP_CONTENT.value
    override var height: Int = HongLayoutParam.WRAP_CONTENT.value
    override var margin: HongSpacingInfo = HongSpacingInfo(0f, 0f, 0f, 0f)
    override var padding: HongSpacingInfo = HongSpacingInfo(0f, 0f, 0f, 0f)
    override var click: ((HongWidgetCommonOption) -> Unit)? = null

    override var radius: HongRadiusInfo = HongRadiusInfo()

    override var backgroundColor: HongColor = HongColor.TRANSPARENT
    override var backgroundColorHex: String = HongColor.TRANSPARENT.hex

    override var border: HongBorderInfo = HongBorderInfo()

    override var useShapeCircle: Boolean = false

    var drawableResId: Int? = null

    var imageUrl: String? = null
    var placeholder: Int? = null
    var error: Int? = null
    var onLoading: ((AsyncImagePainter.State.Loading) -> Unit)? = null
    var onSuccess: ((AsyncImagePainter.State.Success) -> Unit)? = null
    var onError: ((AsyncImagePainter.State.Error) -> Unit)? = null

    var scaleType: HongScaleType = HongScaleType.FIT_START

    var memoryCache = DEFAULT_MEMORY_CACHE
    var diskCache = DEFAULT_DISK_CACHE

    override var shadow = HongShadowInfo(
        color = HongColor.TRANSPARENT.hex,
        blur = 0f,
        offsetY = 0f,
        offsetX = 0f,
        spread = 0f
    )

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as HongImageOption

        if (type != other.type) return false
        if (isValidComponent != other.isValidComponent) return false
        if (width != other.width) return false
        if (height != other.height) return false
        if (margin != other.margin) return false
        if (padding != other.padding) return false
        if (click != other.click) return false
        if (radius != other.radius) return false
        if (backgroundColor != other.backgroundColor) return false
        if (backgroundColorHex != other.backgroundColorHex) return false
        if (border != other.border) return false
        if (useShapeCircle != other.useShapeCircle) return false
        if (drawableResId != other.drawableResId) return false
        if (imageUrl != other.imageUrl) return false
        if (placeholder != other.placeholder) return false
        if (error != other.error) return false
        if (onLoading != other.onLoading) return false
        if (onSuccess != other.onSuccess) return false
        if (onError != other.onError) return false
        if (scaleType != other.scaleType) return false
        if (memoryCache != other.memoryCache) return false
        if (diskCache != other.diskCache) return false
        if (shadow != other.shadow) return false

        return true
    }

    override fun hashCode(): Int {
        var result = type.hashCode()
        result = 31 * result + isValidComponent.hashCode()
        result = 31 * result + width
        result = 31 * result + height
        result = 31 * result + margin.hashCode()
        result = 31 * result + padding.hashCode()
        result = 31 * result + (click?.hashCode() ?: 0)
        result = 31 * result + radius.hashCode()
        result = 31 * result + backgroundColor.hashCode()
        result = 31 * result + backgroundColorHex.hashCode()
        result = 31 * result + border.hashCode()
        result = 31 * result + useShapeCircle.hashCode()
        result = 31 * result + (drawableResId ?: 0)
        result = 31 * result + (imageUrl?.hashCode() ?: 0)
        result = 31 * result + (placeholder ?: 0)
        result = 31 * result + (error ?: 0)
        result = 31 * result + (onLoading?.hashCode() ?: 0)
        result = 31 * result + (onSuccess?.hashCode() ?: 0)
        result = 31 * result + (onError?.hashCode() ?: 0)
        result = 31 * result + scaleType.hashCode()
        result = 31 * result + memoryCache.hashCode()
        result = 31 * result + diskCache.hashCode()
        result = 31 * result + shadow.hashCode()
        return result
    }

    override fun toString(): String {
        return "HongImageOption(" +
                "type=$type, " +
                "isValidComponent=$isValidComponent, " +
                "width=$width, " +
                "height=$height, " +
                "margin=$margin, " +
                "padding=$padding, " +
                "click=$click, " +
                "radius=$radius, " +
                "backgroundColor=$backgroundColor, " +
                "backgroundColorHex='$backgroundColorHex', " +
                "border=$border, " +
                "useShapeCircle=$useShapeCircle, " +
                "drawableResId=$drawableResId, " +
                "imageUrl=$imageUrl, " +
                "placeholder=$placeholder, " +
                "error=$error, " +
                "onLoading=$onLoading, " +
                "onSuccess=$onSuccess, " +
                "onError=$onError, " +
                "scaleType=$scaleType, " +
                "memoryCache=$memoryCache, " +
                "diskCache=$diskCache, " +
                "shadow=$shadow" +
                ")"
    }
}