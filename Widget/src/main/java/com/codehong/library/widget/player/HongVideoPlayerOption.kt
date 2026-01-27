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

    override var isValidComponent: Boolean = true
    override var width: Int = HongLayoutParam.MATCH_PARENT.value
    override var height: Int = HongLayoutParam.WRAP_CONTENT.value
    override var margin: HongSpacingInfo = HongSpacingInfo()
    override var padding: HongSpacingInfo = HongSpacingInfo()
    override var click: ((HongWidgetCommonOption) -> Unit)? = null
    override var radius: HongRadiusInfo = HongRadiusInfo()
    override var border: HongBorderInfo = HongBorderInfo()
    override var shadow: HongShadowInfo = HongShadowInfo()
    override var backgroundColorHex: String = HongColor.TRANSPARENT.hex
    override var useShapeCircle: Boolean = false

    var videoUrl: String? = null
    var ratio: String? = null
    var onPlayVideo: () -> Unit = {}
    var onRenderingFinish: () -> Unit = {}
    var onReady: () -> Unit = {}
    var onBuffering: () -> Unit = {}
    var onEnd: () -> Unit = {}
    var onError: () -> Unit = {}
    var onPlayerReference: (() -> Unit) -> Unit = {}

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is HongVideoPlayerOption) return false
        return type == other.type &&
            isValidComponent == other.isValidComponent &&
            width == other.width &&
            height == other.height &&
            margin == other.margin &&
            padding == other.padding &&
            click == other.click &&
            radius == other.radius &&
            border == other.border &&
            shadow == other.shadow &&
            backgroundColorHex == other.backgroundColorHex &&
            useShapeCircle == other.useShapeCircle &&
            videoUrl == other.videoUrl &&
            ratio == other.ratio &&
            onPlayVideo == other.onPlayVideo &&
            onRenderingFinish == other.onRenderingFinish &&
            onReady == other.onReady &&
            onBuffering == other.onBuffering &&
            onEnd == other.onEnd &&
            onError == other.onError &&
            onPlayerReference == other.onPlayerReference
    }

    override fun hashCode(): Int = listOf(
        type, isValidComponent, width, height, margin, padding, click,
        radius, border, shadow, backgroundColorHex, useShapeCircle,
        videoUrl, ratio, onPlayVideo, onRenderingFinish, onReady,
        onBuffering, onEnd, onError, onPlayerReference
    ).fold(0) { acc, value -> 31 * acc + (value?.hashCode() ?: 0) }

    override fun toString(): String = buildString {
        append("HongVideoPlayerOption(")
        append("type=$type, ")
        append("isValidComponent=$isValidComponent, ")
        append("width=$width, ")
        append("height=$height, ")
        append("margin=$margin, ")
        append("padding=$padding, ")
        append("click=$click, ")
        append("radius=$radius, ")
        append("border=$border, ")
        append("shadow=$shadow, ")
        append("backgroundColorHex='$backgroundColorHex', ")
        append("useShapeCircle=$useShapeCircle, ")
        append("videoUrl=$videoUrl, ")
        append("ratio=$ratio")
        append(")")
    }
}