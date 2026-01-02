package com.codehong.library.widget.player

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.annotation.OptIn
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView
import com.codehong.library.widget.databinding.HonglibViewVideoPlayerBinding
import com.codehong.library.widget.extensions.dpToPx
import com.codehong.library.widget.extensions.hongBackground
import com.codehong.library.widget.extensions.hongPadding
import com.codehong.library.widget.extensions.setLayout
import com.codehong.library.widget.rule.radius.HongRadiusInfo.Companion.toMaterialShapeDrawable
import com.codehong.library.widget.util.Utils
import com.codehong.library.widget.util.applyRatio

class HongVideoPlayerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {
    private val binding =
        HonglibViewVideoPlayerBinding.inflate(LayoutInflater.from(context), this, true)

    private var videoPlayer: ExoPlayer? = null
    private var videoSurfaceView: PlayerView? = null
    private var playerListener: Player.Listener? = null

    var isShowPlayer = false
        private set


    var option = HongVideoPlayerOption()
        private set

    fun set(
        option: HongVideoPlayerOption
    ): HongVideoPlayerView {
        this.option = option

        with(binding.clPlayerContainer) {
            setLayout(
                option.width,
                option.height
            )?.apply {
                this.leftMargin = context.dpToPx(option.margin.left)
                this.topMargin = context.dpToPx(option.margin.top)
                this.rightMargin = context.dpToPx(option.margin.right)
                this.bottomMargin = context.dpToPx(option.margin.bottom)
            }

            hongBackground(
                backgroundColor = option.backgroundColorHex,
                radius = option.radius
            )

            hongPadding(option.padding)
        }

        this.playerListener = object : Player.Listener {
            override fun onRenderedFirstFrame() {
                super.onRenderedFirstFrame()
                binding.clPlayerContainer.animate().alpha(1f).setDuration(50).start()
            }

            override fun onPlaybackStateChanged(playbackState: Int) {
                when (playbackState) {
                    Player.STATE_IDLE -> {
                        clearPlayer()
                        option.onError()
                    }

                    Player.STATE_ENDED -> {
                        clearPlayer()
                        option.onEnd()
                    }

                    Player.STATE_READY -> {
                        option.onReady()
                    }

                    Player.STATE_BUFFERING -> {}
                    else -> {}
                }
            }
        }

        with(binding.vPlayer) {
            this.background = option.radius.toMaterialShapeDrawable(context)
            this.clipToOutline = true
        }
        return this
    }

    @OptIn(UnstableApi::class)
    fun play() {
        try {
            if (option.videoUrl.isNullOrEmpty()) {
                return
            }
            binding.clPlayerContainer.alpha = 0f

            videoPlayer = ExoPlayer.Builder(context).build().apply {
                volume = 0f
                playerListener?.let {
                    addListener(it)
                }
            }

            videoSurfaceView = binding.vPlayer.apply {
                applyRatio(ratio = option.ratio ?: "16:9")
                resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FIT
                useController = false
                player = videoPlayer
            }

            Utils.buildMediaSource(context, option.videoUrl!!)?.let { mediaSource ->
                videoPlayer?.setMediaSource(mediaSource)
                videoPlayer?.prepare()
                videoPlayer?.playWhenReady = true
            }

            this.isShowPlayer = true

        } catch (e: Exception) {
            e.printStackTrace()
            clearPlayer()
        }
    }

    fun clearPlayer() {
        videoPlayer?.playWhenReady = false
        videoSurfaceView?.onPause()
        videoPlayer?.pause()
        videoPlayer?.stop()
        videoPlayer?.clearMediaItems()
        playerListener?.let { videoPlayer?.removeListener(it) }
        videoPlayer?.release()

        this.videoPlayer = null
        this.videoSurfaceView = null

        this.isShowPlayer = false
    }

}