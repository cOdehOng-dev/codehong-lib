package com.codehong.library.widget.player

import com.codehong.library.widget.HongWidgetCommonBuilder
import com.codehong.library.widget.rule.radius.HongRadiusInfo

class HongVideoPlayerBuilder : HongWidgetCommonBuilder<HongVideoPlayerOption, HongVideoPlayerBuilder> {

    override val builder: HongVideoPlayerBuilder = this
    override val option: HongVideoPlayerOption = HongVideoPlayerOption()

    fun radius(radius: HongRadiusInfo) = apply {
        option.radius = radius
    }

    fun setVideoUrl(videoUrl: String?) = apply {
        option.videoUrl = videoUrl
    }

    fun ratio(ratio: String?) = apply {
        option.ratio = ratio
    }

    fun onPlayVideo(onPlayVideo: () -> Unit) = apply {
        option.onPlayVideo = onPlayVideo
    }

    fun onRenderingFinish(onRenderingFinish: () -> Unit) = apply {
        option.onRenderingFinish = onRenderingFinish
    }

    fun onReady(onReady: () -> Unit) = apply {
        option.onReady = onReady
    }

    fun onBuffering(onBuffering: () -> Unit) = apply {
        option.onBuffering = onBuffering
    }

    fun onEnd(onEnd: () -> Unit) = apply {
        option.onEnd = onEnd
    }

    fun onError(onError: () -> Unit) = apply {
        option.onError = onError
    }

    fun onPlayerReference(onPlayerReference: (() -> Unit) -> Unit) = apply {
        option.onPlayerReference = onPlayerReference
    }


    fun copy(inject: HongVideoPlayerOption): HongVideoPlayerBuilder {
        return HongVideoPlayerBuilder()
            .height(inject.height)
            .margin(inject.margin)
            .padding(inject.padding)
            .onClick(inject.click)
            .radius(inject.radius)
            .setVideoUrl(inject.videoUrl)
            .ratio(inject.ratio)
            .onPlayVideo(inject.onPlayVideo)
            .onRenderingFinish(inject.onRenderingFinish)
            .onReady(inject.onReady)
            .onBuffering(inject.onBuffering)
            .onEnd(inject.onEnd)
            .onError(inject.onError)
            .onPlayerReference(inject.onPlayerReference)
    }
}
