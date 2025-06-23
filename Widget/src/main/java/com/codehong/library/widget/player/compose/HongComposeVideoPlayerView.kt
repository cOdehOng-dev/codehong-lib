package com.codehong.library.widget.player.compose

import androidx.annotation.OptIn
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView
import com.codehong.library.widget.player.builder.HongVideoPlayerOption
import com.codehong.library.widget.util.Utils
import com.codehong.library.widget.util.aspectRatio

@OptIn(UnstableApi::class)
@Composable
fun HongComposeVideoPlayerView(
    option: HongVideoPlayerOption,
    onPlayVideo: () -> Unit = {},
    onRenderingFinish: () -> Unit = {},
    onReady: () -> Unit = {},
    onBuffering: () -> Unit = {},
    onEnd: () -> Unit = {},
    onError: () -> Unit = {},
    onPlayerReference: (() -> Unit) -> Unit = {}
) {
    if (option.playerUrl.isNullOrEmpty()) return

    val context = LocalContext.current
    var videoPlayer by remember { mutableStateOf<ExoPlayer?>(null) }
    var videoSurfaceView by remember { mutableStateOf<PlayerView?>(null) }
    var playbackStateListener by remember { mutableStateOf<Player.Listener?>(null) }

    val radius = option.radiusDp ?: HongVideoPlayerOption.DEFAULT_RADIUS_DP
    val shape = RoundedCornerShape(
        topStart = if (option.topLeft ?: HongVideoPlayerOption.DEFAULT_SET_TOP_LEFT_RADIUS) radius.dp else 0.dp,
        topEnd = if (option.topRight ?: HongVideoPlayerOption.DEFAULT_SET_TOP_RIGHT_RADIUS) radius.dp else 0.dp,
        bottomStart = if (option.bottomLeft ?: HongVideoPlayerOption.DEFAULT_SET_BOTTOM_LEFT_RADIUS) radius.dp else 0.dp,
        bottomEnd = if (option.bottomRight ?: HongVideoPlayerOption.DEFAULT_SET_BOTTOM_RIGHT_RADIUS) radius.dp else 0.dp,
    )

    fun clearPlayer() {
        videoPlayer?.playWhenReady = false
        videoSurfaceView?.onPause()
        videoPlayer?.pause()
        videoPlayer?.stop()
        videoPlayer?.clearMediaItems()
        playbackStateListener?.let { videoPlayer?.removeListener(it) }
        videoPlayer?.release()
        videoPlayer = null
        videoSurfaceView = null
    }

    playbackStateListener = object : Player.Listener {
        override fun onRenderedFirstFrame() {
            onRenderingFinish()
        }

        override fun onPlaybackStateChanged(playbackState: Int) {
            onPlayVideo()
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

                Player.STATE_BUFFERING -> {
                    onBuffering()
                }
            }
        }
    }

    // 외부에서 clearPlayer 호출할 수 있게 참조 전달
    LaunchedEffect(Unit) {
        onPlayerReference.invoke { clearPlayer() }
    }

    DisposableEffect(option.playerUrl) {
        if (option.playerUrl.isNullOrEmpty()) {
            return@DisposableEffect onDispose { videoPlayer = null }
        }

        videoPlayer = ExoPlayer.Builder(context).build().apply {
            volume = 0f
            playWhenReady = true
            playbackStateListener?.let { addListener(it) }

        }

        videoSurfaceView = PlayerView(context).apply {
            resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FIT
            useController = false
            player = videoPlayer
        }


        Utils.buildMediaSource(context, option.playerUrl ?: "")?.let { mediaSource ->
            videoPlayer?.setMediaSource(mediaSource)
            videoPlayer?.prepare()
        }

        onDispose {
            videoPlayer?.release()
            videoPlayer = null
        }
    }

    if (videoSurfaceView == null) {
        videoPlayer?.release()
        videoPlayer = null
        return
    }

    AndroidView(
        factory = { videoSurfaceView!! },
        modifier = Modifier
            .aspectRatio(option.ratio.aspectRatio())
            .clip(shape)
            .fillMaxSize()
    )
}
