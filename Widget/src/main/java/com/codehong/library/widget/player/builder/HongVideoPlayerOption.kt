package com.codehong.library.widget.player.builder

import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView

class HongVideoPlayerOption {

    companion object {
        const val DEFAULT_RADIUS_DP = 0
        const val DEFAULT_SET_TOP_LEFT_RADIUS = false
        const val DEFAULT_SET_TOP_RIGHT_RADIUS = false
        const val DEFAULT_SET_BOTTOM_LEFT_RADIUS = false
        const val DEFAULT_SET_BOTTOM_RIGHT_RADIUS = false
    }

    var radiusDp: Int? = null
    var topLeft: Boolean? = null
    var topRight: Boolean? = null
    var bottomLeft: Boolean? = null
    var bottomRight: Boolean? = null

    var videoPlayer: ExoPlayer? = null
    var videoSurfaceView: PlayerView? = null

    var isShowPlayer = false

    var playerUrl: String? = null
    var ratio: String? = null


    class Builder {
        private val option = HongVideoPlayerOption()

        fun setRadiusDp(radiusDp: Int?): Builder {
            option.radiusDp = radiusDp ?: DEFAULT_RADIUS_DP
            return this
        }

        fun setTopLeft(topLeft: Boolean?): Builder {
            option.topLeft = topLeft ?: DEFAULT_SET_TOP_LEFT_RADIUS
            return this
        }

        fun setTopRight(topRight: Boolean?): Builder {
            option.topRight = topRight ?: DEFAULT_SET_TOP_RIGHT_RADIUS
            return this
        }

        fun setBottomLeft(bottomLeft: Boolean?): Builder {
            option.bottomLeft = bottomLeft ?: DEFAULT_SET_BOTTOM_LEFT_RADIUS
            return this
        }

        fun setBottomRight(bottomRight: Boolean?): Builder {
            option.bottomRight = bottomRight ?: DEFAULT_SET_BOTTOM_RIGHT_RADIUS
            return this
        }

        fun setVideoPlayer(videoPlayer: ExoPlayer?): Builder {
            option.videoPlayer = videoPlayer
            return this
        }

        fun setVideoSurfaceView(videoSurfaceView: PlayerView?): Builder {
            option.videoSurfaceView = videoSurfaceView
            return this
        }

        fun setPlayerUrl(playerUrl: String?): Builder {
            option.playerUrl = playerUrl
            return this
        }

        fun setRatio(ratio: String?): Builder {
            option.ratio = ratio
            return this
        }

        fun setShowPlayer(isShowPlayer: Boolean): Builder {
            option.isShowPlayer = isShowPlayer
            return this
        }

        fun build(): HongVideoPlayerOption {
            return option
        }

        fun copy(inject: HongVideoPlayerOption): Builder {
            return Builder()
                .setRadiusDp(inject.radiusDp)
                .setTopLeft(inject.topLeft)
                .setTopRight(inject.topRight)
                .setBottomLeft(inject.bottomLeft)
                .setBottomRight(inject.bottomRight)
                .setVideoPlayer(inject.videoPlayer)
                .setVideoSurfaceView(inject.videoSurfaceView)
                .setPlayerUrl(inject.playerUrl)
                .setRatio(inject.ratio)
        }
    }
}
fun HongVideoPlayerOption.builder(): HongVideoPlayerOption.Builder {
    return HongVideoPlayerOption.Builder().copy(this)
}