package com.codehong.library.widget.videopopup

import com.codehong.library.widget.player.HongVideoPlayerBuilder
import com.codehong.library.widget.player.HongVideoPlayerOption
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.radius.HongRadiusInfo

class HongVideoPopupBuilder {

    val option = HongVideoPopupOption()

    fun height(height: Int?) = apply {
        height?.let { option.height = it }
    }

    fun margin(margin: HongSpacingInfo) = apply {
        option.margin = margin
    }

    fun padding(padding: HongSpacingInfo) = apply {
        option.padding = padding
    }

    fun onClick(onClick: ((HongVideoPopupOption) -> Unit)? = null) = apply {
        option.click = {
            if (it is HongVideoPopupOption) {
                onClick?.invoke(it)
            }
        }
    }

    fun videoPlayerOption(videoPlayerOption: HongVideoPlayerOption?) = apply {
        option.videoPlayerOption = videoPlayerOption ?: HongVideoPlayerBuilder()
            .radius(
                HongRadiusInfo(
                    all = HongVideoPlayerOption.DEFAULT_ALL_RADIUS,
                    top = HongVideoPlayerOption.DEFAULT_TOP_RADIUS,
                    bottom = HongVideoPlayerOption.DEFAULT_BOTTOM_RADIUS,
                    topLeft = HongVideoPlayerOption.DEFAULT_TOP_LEFT_RADIUS,
                    topRight = HongVideoPlayerOption.DEFAULT_TOP_RIGHT_RADIUS,
                    bottomLeft = HongVideoPlayerOption.DEFAULT_BOTTOM_LEFT_RADIUS,
                    bottomRight = HongVideoPlayerOption.DEFAULT_BOTTOM_RIGHT_RADIUS
                )
            )
            .applyOption()
    }


    fun blockTouchOutside(blockTouchOutside: Boolean) = apply {
        option.blockTouchOutside = blockTouchOutside
    }

    fun landingLink(landingLink: String?) = apply {
        option.landingLink = landingLink
    }

    fun applyOption(): HongVideoPopupOption {
        return option
    }

    fun copy(inject: HongVideoPopupOption): HongVideoPopupBuilder {
        return HongVideoPopupBuilder()
            .height(inject.height)
            .margin(inject.margin)
            .padding(inject.padding)
            .onClick(inject.click)
            .videoPlayerOption(inject.videoPlayerOption)
            .blockTouchOutside(inject.blockTouchOutside)
            .landingLink(inject.landingLink)
    }
}
