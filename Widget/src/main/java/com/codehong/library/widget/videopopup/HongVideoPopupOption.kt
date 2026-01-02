package com.codehong.library.widget.videopopup

import com.codehong.library.widget.HongWidgetCommonOption

import com.codehong.library.widget.player.HongVideoPlayerBuilder
import com.codehong.library.widget.rule.HongBorderInfo
import com.codehong.library.widget.rule.HongLayoutParam
import com.codehong.library.widget.rule.HongShadowInfo
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.HongWidgetType
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.radius.HongRadiusInfo

data class HongVideoPopupOption(
    override val type: HongWidgetType = HongWidgetType.VIDEO_POPUP
) : HongWidgetCommonOption {

    companion object {
        const val DEFAULT_BLOCK_TOUCH_OUTSIDE = true
        const val DEFAULT_RATIO = "16:9"
    }

    override var isValidComponent: Boolean = true

    override var width: Int = HongLayoutParam.MATCH_PARENT.value
    override var height: Int = HongLayoutParam.WRAP_CONTENT.value
    override var margin: HongSpacingInfo = HongSpacingInfo(0f, 0f, 0f, 0f)
    override var padding: HongSpacingInfo = HongSpacingInfo(0f, 0f, 0f, 0f)
    override var backgroundColorHex: String = HongColor.TRANSPARENT.hex
    override var click: ((HongWidgetCommonOption) -> Unit)? = null
    override var radius: HongRadiusInfo = HongRadiusInfo()
    override var shadow: HongShadowInfo = HongShadowInfo()
    override var border: HongBorderInfo = HongBorderInfo()
    override var useShapeCircle: Boolean = false

    var videoPlayerOption = HongVideoPlayerBuilder()
        .ratio(DEFAULT_RATIO)
        .applyOption()

    var blockTouchOutside: Boolean = DEFAULT_BLOCK_TOUCH_OUTSIDE
    var landingLink: String? = null

    var onShow: () -> Unit = {}
    var onHide: (isClickClose: Boolean) -> Unit = { _ -> }
    var showPopup: (Boolean) -> Unit = {}
    var clickLanding: ((String?) -> Unit)? = null

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as HongVideoPopupOption

        if (type != other.type) return false
        if (isValidComponent != other.isValidComponent) return false
        if (width != other.width) return false
        if (height != other.height) return false
        if (margin != other.margin) return false
        if (padding != other.padding) return false
        if (backgroundColorHex != other.backgroundColorHex) return false
        if (click != other.click) return false
        if (radius != other.radius) return false
        if (shadow != other.shadow) return false
        if (border != other.border) return false
        if (useShapeCircle != other.useShapeCircle) return false
        if (videoPlayerOption != other.videoPlayerOption) return false
        if (blockTouchOutside != other.blockTouchOutside) return false
        if (landingLink != other.landingLink) return false
        if (onShow != other.onShow) return false
        if (onHide != other.onHide) return false
        if (showPopup != other.showPopup) return false
        if (clickLanding != other.clickLanding) return false

        return true
    }

    override fun hashCode(): Int {
        var result = type.hashCode()
        result = 31 * result + isValidComponent.hashCode()
        result = 31 * result + width
        result = 31 * result + height
        result = 31 * result + margin.hashCode()
        result = 31 * result + padding.hashCode()
        result = 31 * result + backgroundColorHex.hashCode()
        result = 31 * result + (click?.hashCode() ?: 0)
        result = 31 * result + radius.hashCode()
        result = 31 * result + shadow.hashCode()
        result = 31 * result + border.hashCode()
        result = 31 * result + useShapeCircle.hashCode()
        result = 31 * result + videoPlayerOption.hashCode()
        result = 31 * result + blockTouchOutside.hashCode()
        result = 31 * result + (landingLink?.hashCode() ?: 0)
        result = 31 * result + onShow.hashCode()
        result = 31 * result + onHide.hashCode()
        result = 31 * result + showPopup.hashCode()
        result = 31 * result + (clickLanding?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String {
        return "HongVideoPopupOption(" +
                "type=$type, " +
                "isValidComponent=$isValidComponent, " +
                "width=$width, " +
                "height=$height, " +
                "margin=$margin, " +
                "padding=$padding, " +
                "backgroundColorHex='$backgroundColorHex', " +
                "click=$click, " +
                "radius=$radius, " +
                "shadow=$shadow, " +
                "border=$border, " +
                "useShapeCircle=$useShapeCircle, " +
                "videoPlayerOption=$videoPlayerOption, " +
                "blockTouchOutside=$blockTouchOutside, " +
                "landingLink=$landingLink, " +
                "onShow=$onShow, " +
                "onHide=$onHide, " +
                "showPopup=$showPopup, " +
                "clickLanding=$clickLanding" +
                ")"
    }


}
