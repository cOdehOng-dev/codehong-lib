package com.codehong.library.widget.player

import androidx.annotation.OptIn
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
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
import com.codehong.library.widget.extensions.aspectRatio
import com.codehong.library.widget.extensions.hongBackground
import com.codehong.library.widget.extensions.hongHeight
import com.codehong.library.widget.extensions.hongWidth
import com.codehong.library.widget.rule.radius.HongRadiusInfo.Companion.toRoundedCornerShape
import com.codehong.library.widget.util.Utils

@OptIn(UnstableApi::class)
@Composable
fun HongVideoPlayerCompose(
    option: HongVideoPlayerOption
) {
    if (option.videoUrl.isNullOrEmpty()) return

    val context = LocalContext.current
    var videoPlayer by remember { mutableStateOf<ExoPlayer?>(null) }
    var videoSurfaceView by remember { mutableStateOf<PlayerView?>(null) }

    val clearPlayer: () -> Unit = {
        videoPlayer?.run {
            playWhenReady = false
            pause()
            stop()
            clearMediaItems()
            release()
        }
        videoSurfaceView?.onPause()
        videoPlayer = null
        videoSurfaceView = null
    }

    val playbackStateListener = remember {
        object : Player.Listener {
            override fun onRenderedFirstFrame() {
                option.onRenderingFinish()
            }

            override fun onPlaybackStateChanged(playbackState: Int) {
                option.onPlayVideo()
                when (playbackState) {
                    Player.STATE_IDLE -> {
                        clearPlayer()
                        option.onError()
                    }
                    Player.STATE_ENDED -> {
                        clearPlayer()
                        option.onEnd()
                    }
                    Player.STATE_READY -> option.onReady()
                    Player.STATE_BUFFERING -> option.onBuffering()
                }
            }
        }
    }

    LaunchedEffect(Unit) {
        option.onPlayerReference.invoke { clearPlayer() }
    }

    DisposableEffect(option.videoUrl) {
        if (option.videoUrl.isNullOrEmpty()) {
            return@DisposableEffect onDispose { videoPlayer = null }
        }

        videoPlayer = ExoPlayer.Builder(context).build().apply {
            volume = 0f
            playWhenReady = true
            addListener(playbackStateListener)
        }

        videoSurfaceView = PlayerView(context).apply {
            resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FIT
            useController = false
            player = videoPlayer
        }

        Utils.buildMediaSource(context, option.videoUrl ?: "")?.let { mediaSource ->
            videoPlayer?.setMediaSource(mediaSource)
            videoPlayer?.prepare()
        }

        onDispose {
            videoPlayer?.removeListener(playbackStateListener)
            videoPlayer?.release()
            videoPlayer = null
        }
    }

    if (videoSurfaceView == null) {
        videoPlayer?.release()
        videoPlayer = null
        return
    }

    Box(
        modifier = Modifier.padding(
            start = option.margin.left.dp,
            top = option.margin.top.dp,
            end = option.margin.right.dp,
            bottom = option.margin.bottom.dp
        )
    ) {
        AndroidView(
            modifier = Modifier
                .hongWidth(option.width)
                .hongHeight(option.height)
                .aspectRatio(option.ratio.aspectRatio())
                .hongBackground(
                    color = option.backgroundColorHex,
                    radius = option.radius,
                    useShapeCircle = option.useShapeCircle,
                    shadow = option.shadow,
                    border = option.border
                )
                .padding(
                    start = option.padding.left.dp,
                    top = option.padding.top.dp,
                    end = option.padding.right.dp,
                    bottom = option.padding.bottom.dp
                )
                .clip(option.radius.toRoundedCornerShape()),
            factory = { videoSurfaceView!! }
        )
    }
}
