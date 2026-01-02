package com.codehong.library.widget.videopopup

import com.codehong.library.widget.HongWidgetCommonBuilder
import com.codehong.library.widget.player.HongVideoPlayerBuilder
import com.codehong.library.widget.player.HongVideoPlayerOption
import com.codehong.library.widget.rule.radius.HongRadiusInfo

class HongVideoPopupBuilder : HongWidgetCommonBuilder<HongVideoPopupOption, HongVideoPopupBuilder> {

    override val builder: HongVideoPopupBuilder = this
    override val option: HongVideoPopupOption = HongVideoPopupOption()

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

    fun onShow(onShow: () -> Unit) = apply {
        option.onShow = onShow
    }

    fun onHide(onHide: (isClickClose: Boolean) -> Unit) = apply {
        option.onHide = onHide
    }

    fun showPopup(showPopup: (Boolean) -> Unit) = apply {
        option.showPopup = showPopup
    }

    fun clickLanding(clickLanding: ((String?) -> Unit)?) = apply {
        option.clickLanding = clickLanding
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
            .onShow(inject.onShow)
            .onHide(inject.onHide)
            .showPopup(inject.showPopup)
            .clickLanding(inject.clickLanding)
    }
}
