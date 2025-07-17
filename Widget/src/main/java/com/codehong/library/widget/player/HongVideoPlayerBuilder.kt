package com.codehong.library.widget.player

import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.radius.HongRadiusInfo

class HongVideoPlayerBuilder {

    val option = HongVideoPlayerOption()

    fun height(height: Int?) = apply {
        height?.let { option.height = it }
    }

    fun margin(margin: HongSpacingInfo) = apply {
        option.margin = margin
    }

    fun padding(padding: HongSpacingInfo) = apply {
        option.padding = padding
    }

    fun onClick(onClick:( (HongVideoPlayerOption) -> Unit)?) = apply {
        option.click = {
            if (it is HongVideoPlayerOption) {
                onClick?.invoke(it)
            }
        }
    }


    fun radius(radius: HongRadiusInfo) = apply {
        option.radius = radius
    }

    fun setVideoUrl(videoUrl: String?) = apply {
        option.videoUrl = videoUrl
    }

    fun ratio(ratio: String?) = apply {
        option.ratio = ratio
    }

    fun applyOption(): HongVideoPlayerOption {
        return option
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
    }
}
