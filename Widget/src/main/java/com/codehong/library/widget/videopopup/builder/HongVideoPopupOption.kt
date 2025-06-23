package com.codehong.library.widget.videopopup.builder

class HongVideoPopupOption {

    companion object {
        const val DEFAULT_RADIUS_DP = 0
        const val DEFAULT_SET_TOP_LEFT_RADIUS = false
        const val DEFAULT_SET_TOP_RIGHT_RADIUS = false
        const val DEFAULT_SET_BOTTOM_LEFT_RADIUS = false
        const val DEFAULT_SET_BOTTOM_RIGHT_RADIUS = false

        const val DEFAULT_BLOCK_TOUCH_OUTSIDE = true
    }

    var radiusDp: Int? = null
    var topLeft: Boolean? = null
    var topRight: Boolean? = null
    var bottomLeft: Boolean? = null
    var bottomRight: Boolean? = null

    var blockTouchOutside: Boolean? = null

    var videoUrl: String? = null
    var ratio: String? = null
    var landingLink: String? = null


    class Builder {
        private val option = HongVideoPopupOption()

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

        fun setBlockTouchOutside(blockTouchOutside: Boolean?): Builder {
            option.blockTouchOutside = blockTouchOutside ?: DEFAULT_BLOCK_TOUCH_OUTSIDE
            return this
        }

        fun setVideoUrl(videoUrl: String?): Builder {
            option.videoUrl = videoUrl
            return this
        }

        fun setRatio(ratio: String?): Builder {
            option.ratio = ratio
            return this
        }

        fun setLandingLink(landingLink: String?): Builder {
            option.landingLink = landingLink
            return this
        }

        fun build(): HongVideoPopupOption {
            return option
        }

        fun copy(inject: HongVideoPopupOption): Builder {
            return Builder()
                .setRadiusDp(inject.radiusDp)
                .setTopLeft(inject.topLeft)
                .setTopRight(inject.topRight)
                .setBottomLeft(inject.bottomLeft)
                .setBottomRight(inject.bottomRight)
                .setBlockTouchOutside(inject.blockTouchOutside)
                .setLandingLink(inject.landingLink)
                .setVideoUrl(inject.videoUrl)
                .setRatio(inject.ratio)
        }
    }
}

fun HongVideoPopupOption.builder(): HongVideoPopupOption.Builder {
    return HongVideoPopupOption.Builder().copy(this)
}