package com.codehong.library.widget.player.builder

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.annotation.OptIn
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.AspectRatioFrameLayout
import com.codehong.library.widget.databinding.HonglibViewVideoPlayerBinding
import com.codehong.library.widget.util.Utils
import com.codehong.library.widget.util.applyRatio
import com.google.android.material.shape.MaterialShapeDrawable
import com.google.android.material.shape.ShapeAppearanceModel

class HongVideoPlayerBuilderView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {
    private val binding =
        HonglibViewVideoPlayerBinding.inflate(LayoutInflater.from(context), this, true)

    private var playerListener: Player.Listener? = null

    var option = HongVideoPlayerOption()
        private set

    fun set(
        option: HongVideoPlayerOption,
        onReady: () -> Unit = {},
        onEnd: () -> Unit = {},
        onError: () -> Unit = {},
    ): HongVideoPlayerBuilderView {
        this.option = option
        this.playerListener = object : Player.Listener {
            override fun onRenderedFirstFrame() {
                super.onRenderedFirstFrame()
                binding.clPlayerContainer.animate().alpha(1f).setDuration(50).start()
            }

            override fun onPlaybackStateChanged(playbackState: Int) {
                when (playbackState) {
                    Player.STATE_IDLE -> {
                        clearPlayer()
                        onError()
                    }

                    Player.STATE_ENDED -> {
                        clearPlayer()
                        onEnd()
                    }

                    Player.STATE_READY -> {
                        onReady()
                    }

                    Player.STATE_BUFFERING -> {}
                    else -> {}
                }
            }
        }

        with(binding.vPlayer) {
            val radius = Utils.dpToFloatPx(
                context,
                option.radiusDp ?: HongVideoPlayerOption.DEFAULT_RADIUS_DP
            )
            val shapeDrawable = MaterialShapeDrawable().apply {
                shapeAppearanceModel = ShapeAppearanceModel()
                    .toBuilder()
                    .setTopLeftCornerSize(
                        if (option.topLeft
                                ?: HongVideoPlayerOption.DEFAULT_SET_TOP_LEFT_RADIUS
                        ) radius else 0f
                    )
                    .setTopRightCornerSize(
                        if (option.topRight
                                ?: HongVideoPlayerOption.DEFAULT_SET_TOP_RIGHT_RADIUS
                        ) radius else 0f
                    )
                    .setBottomLeftCornerSize(
                        if (option.bottomLeft
                                ?: HongVideoPlayerOption.DEFAULT_SET_BOTTOM_LEFT_RADIUS
                        ) radius else 0f
                    )
                    .setBottomRightCornerSize(
                        if (option.bottomRight
                                ?: HongVideoPlayerOption.DEFAULT_SET_BOTTOM_RIGHT_RADIUS
                        ) radius else 0f
                    )
                    .build()
            }
            this.background = shapeDrawable
            this.clipToOutline = false
        }

        return this
    }

    @OptIn(UnstableApi::class)
    fun play() {
        try {
            if (option.playerUrl.isNullOrEmpty()) {
                return
            }
            binding.clPlayerContainer.alpha = 0f

            val videoPlayer = ExoPlayer.Builder(context).build().apply {
                volume = 0f
                playerListener?.let {
                    addListener(it)
                }
            }

            val surfaceView = binding.vPlayer.apply {
                applyRatio(ratio = option.ratio ?: "16:9")
                resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FIT
                useController = false
                player = videoPlayer
            }

            Utils.buildMediaSource(context, option.playerUrl!!)?.let { mediaSource ->
                videoPlayer.setMediaSource(mediaSource)
                videoPlayer.prepare()
                videoPlayer.playWhenReady = true
            }

            this.option = option
                .builder()
                .setVideoPlayer(videoPlayer)
                .setVideoSurfaceView(surfaceView)
                .setShowPlayer(true)
                .build()

        } catch (e: Exception) {
            e.printStackTrace()
            clearPlayer()
        }
    }

    fun clearPlayer() {
        option.videoPlayer?.playWhenReady = false
        option.videoSurfaceView?.onPause()
        option.videoPlayer?.pause()
        option.videoPlayer?.stop()
        option.videoPlayer?.clearMediaItems()
        playerListener?.let { option.videoPlayer?.removeListener(it) }
        option.videoPlayer?.release()
        option.builder()
            .setVideoPlayer(null)
            .setVideoSurfaceView(null)
            .setShowPlayer(false)
            .build()
    }

}