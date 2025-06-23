package com.codehong.library.widget.player.view

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
import com.codehong.library.widget.util.Utils
import com.codehong.library.widget.util.applyRatio
import com.google.android.material.shape.MaterialShapeDrawable
import com.google.android.material.shape.ShapeAppearanceModel

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

    fun set(
        radiusDp: Int = 0,
        topLeft: Boolean = false,
        topRight: Boolean = false,
        bottomLeft: Boolean = false,
        bottomRight: Boolean = false,
        onReady: () -> Unit = {},
        onEnd: () -> Unit = {},
        onError: () -> Unit = {},
    ) {

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
            val radius = Utils.dpToFloatPx(context, radiusDp)
            val shapeDrawable = MaterialShapeDrawable().apply {
                shapeAppearanceModel = ShapeAppearanceModel()
                    .toBuilder()
                    .setTopLeftCornerSize(if (topLeft) radius else 0f)
                    .setTopRightCornerSize(if (topRight) radius else 0f)
                    .setBottomLeftCornerSize(if (bottomLeft) radius else 0f)
                    .setBottomRightCornerSize(if (bottomRight) radius else 0f)
                    .build()
            }
            this.background = shapeDrawable
            this.clipToOutline = false
        }
    }

    @OptIn(UnstableApi::class)
    fun play(
        playerUrl: String?,
        ratio: String?
    ) {
        try {
            if (playerUrl.isNullOrEmpty()) {
                return
            }
            binding.vPlayer.applyRatio(ratio = ratio ?: "16:9")
            binding.clPlayerContainer.alpha = 0f

            // 플레이어 설정
            videoPlayer = ExoPlayer.Builder(context).build()
            videoPlayer?.volume = 0f
            playerListener?.let { videoPlayer?.addListener(it) }

            videoSurfaceView = binding.vPlayer
            videoSurfaceView?.resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FIT
            videoSurfaceView?.useController = false
            videoSurfaceView?.player = videoPlayer

            Utils.buildMediaSource(context, playerUrl)?.let { mediaSource ->
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