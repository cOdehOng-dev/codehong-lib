package com.codehong.library.widget.image.blur

import com.codehong.library.widget.HongWidgetCommonOption
import com.codehong.library.widget.rule.HongBorderInfo
import com.codehong.library.widget.rule.HongLayoutParam
import com.codehong.library.widget.rule.HongScaleType
import com.codehong.library.widget.rule.HongShadowInfo
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.HongWidgetType
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.radius.HongRadiusInfo

data class HongImageBlurOption(
    override val type: HongWidgetType = HongWidgetType.IMAGE_BLUR
) : HongWidgetCommonOption {
    override var isValidComponent: Boolean = true

    override var width: Int = HongLayoutParam.WRAP_CONTENT.value
    override var height: Int = HongLayoutParam.WRAP_CONTENT.value
    override var margin: HongSpacingInfo = HongSpacingInfo(0f, 0f, 0f, 0f)
    override var padding: HongSpacingInfo = HongSpacingInfo(0f, 0f, 0f, 0f)
    override var click: ((HongWidgetCommonOption) -> Unit)? = null

    override var radius: HongRadiusInfo = HongRadiusInfo()

    override var backgroundColorHex: String = HongColor.TRANSPARENT.hex

    override var border: HongBorderInfo = HongBorderInfo()
    override var shadow = HongShadowInfo()

    override var useShapeCircle: Boolean = false

    var imageInfo: Any? = null

    var scaleType: HongScaleType = HongScaleType.CENTER_CROP

    var blur: Int = 30


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as HongImageBlurOption

        if (isValidComponent != other.isValidComponent) return false
        if (width != other.width) return false
        if (height != other.height) return false
        if (useShapeCircle != other.useShapeCircle) return false
        if (blur != other.blur) return false
        if (type != other.type) return false
        if (margin != other.margin) return false
        if (padding != other.padding) return false
        if (click != other.click) return false
        if (radius != other.radius) return false
        if (backgroundColorHex != other.backgroundColorHex) return false
        if (border != other.border) return false
        if (shadow != other.shadow) return false
        if (imageInfo != other.imageInfo) return false
        if (scaleType != other.scaleType) return false

        return true
    }

    override fun hashCode(): Int {
        var result = isValidComponent.hashCode()
        result = 31 * result + width
        result = 31 * result + height
        result = 31 * result + useShapeCircle.hashCode()
        result = 31 * result + blur
        result = 31 * result + type.hashCode()
        result = 31 * result + margin.hashCode()
        result = 31 * result + padding.hashCode()
        result = 31 * result + (click?.hashCode() ?: 0)
        result = 31 * result + radius.hashCode()
        result = 31 * result + backgroundColorHex.hashCode()
        result = 31 * result + border.hashCode()
        result = 31 * result + shadow.hashCode()
        result = 31 * result + (imageInfo?.hashCode() ?: 0)
        result = 31 * result + scaleType.hashCode()
        return result
    }

    override fun toString(): String {
        return "HongImageBlurOption(" +
                "type=$type, " +
                "isValidComponent=$isValidComponent, " +
                "width=$width, " +
                "height=$height, " +
                "margin=$margin, " +
                "padding=$padding, " +
                "click=$click, " +
                "radius=$radius, " +
                "backgroundColorHex='$backgroundColorHex', " +
                "border=$border, " +
                "shadow=$shadow, " +
                "useShapeCircle=$useShapeCircle, " +
                "imageInfo=$imageInfo, " +
                "scaleType=$scaleType, " +
                "blur=$blur" +
                ")"
    }
}
