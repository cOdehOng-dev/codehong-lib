package com.codehong.library.widget.player

import com.codehong.library.widget.HongWidgetCommonOption

import com.codehong.library.widget.rule.HongBorderInfo
import com.codehong.library.widget.rule.HongLayoutParam
import com.codehong.library.widget.rule.HongShadowInfo
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.HongWidgetType
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.radius.HongRadiusInfo

data class HongVideoPlayerOption(
    override val type: HongWidgetType = HongWidgetType.VIDEO_PLAYER
) : HongWidgetCommonOption {

    companion object {
        const val DEFAULT_ALL_RADIUS = 0
        const val DEFAULT_TOP_RADIUS = 0
        const val DEFAULT_BOTTOM_RADIUS = 0
        const val DEFAULT_TOP_LEFT_RADIUS = 0
        const val DEFAULT_TOP_RIGHT_RADIUS = 0
        const val DEFAULT_BOTTOM_LEFT_RADIUS = 0
        const val DEFAULT_BOTTOM_RIGHT_RADIUS = 0
    }

    override var isValidComponent: Boolean = true

    override var width: Int = HongLayoutParam.MATCH_PARENT.value
    override var height: Int = HongLayoutParam.WRAP_CONTENT.value
    override var margin: HongSpacingInfo = HongSpacingInfo(0f, 0f, 0f, 0f)
    override var padding: HongSpacingInfo = HongSpacingInfo(0f, 0f, 0f, 0f)
    override var click: ((HongWidgetCommonOption) -> Unit)? = null

    override var radius: HongRadiusInfo = HongRadiusInfo(
        all = DEFAULT_ALL_RADIUS,
        top = DEFAULT_TOP_RADIUS,
        bottom = DEFAULT_BOTTOM_RADIUS,
        topLeft = DEFAULT_TOP_LEFT_RADIUS,
        topRight = DEFAULT_TOP_RIGHT_RADIUS,
        bottomLeft = DEFAULT_BOTTOM_LEFT_RADIUS,
        bottomRight = DEFAULT_BOTTOM_RIGHT_RADIUS
    )

    override var border: HongBorderInfo = HongBorderInfo()

    override var shadow: HongShadowInfo = HongShadowInfo()

    override var backgroundColor: HongColor = HongColor.TRANSPARENT
    override var backgroundColorHex: String = HongColor.TRANSPARENT.hex
    override var useShapeCircle: Boolean = false

    var videoUrl: String? = null
    var ratio: String? = null

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as HongVideoPlayerOption

        if (type != other.type) return false
        if (isValidComponent != other.isValidComponent) return false
        if (width != other.width) return false
        if (height != other.height) return false
        if (margin != other.margin) return false
        if (padding != other.padding) return false
        if (click != other.click) return false
        if (radius != other.radius) return false
        if (border != other.border) return false
        if (shadow != other.shadow) return false
        if (backgroundColor != other.backgroundColor) return false
        if (backgroundColorHex != other.backgroundColorHex) return false
        if (useShapeCircle != other.useShapeCircle) return false
        if (videoUrl != other.videoUrl) return false
        if (ratio != other.ratio) return false

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
        result = 31 * result + border.hashCode()
        result = 31 * result + shadow.hashCode()
        result = 31 * result + backgroundColor.hashCode()
        result = 31 * result + backgroundColorHex.hashCode()
        result = 31 * result + useShapeCircle.hashCode()
        result = 31 * result + (videoUrl?.hashCode() ?: 0)
        result = 31 * result + (ratio?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String {
        return "HongVideoPlayerOption(" +
                "type=$type, " +
                "isValidComponent=$isValidComponent, " +
                "width=$width, " +
                "height=$height, " +
                "margin=$margin, " +
                "padding=$padding, " +
                "click=$click, " +
                "radius=$radius, " +
                "border=$border, " +
                "shadow=$shadow, " +
                "backgroundColor=$backgroundColor, " +
                "backgroundColorHex='$backgroundColorHex', " +
                "useShapeCircle=$useShapeCircle, " +
                "videoUrl=$videoUrl, " +
                "ratio=$ratio" +
                ")"
    }
}