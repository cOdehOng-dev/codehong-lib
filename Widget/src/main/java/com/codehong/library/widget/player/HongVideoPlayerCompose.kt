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
import com.codehong.library.widget.extensions.hongBackground
import com.codehong.library.widget.extensions.hongHeight
import com.codehong.library.widget.extensions.hongWidth
import com.codehong.library.widget.rule.radius.HongRadiusInfo.Companion.toRoundedCornerShape
import com.codehong.library.widget.util.Utils
import com.codehong.library.widget.util.aspectRatio

@OptIn(UnstableApi::class)
@Composable
fun HongVideoPlayerCompose(
    option: HongVideoPlayerOption
) {
    if (option.videoUrl.isNullOrEmpty()) return

    val remOption by remember { mutableStateOf(option) }

    val context = LocalContext.current
    var videoPlayer by remember { mutableStateOf<ExoPlayer?>(null) }
    var videoSurfaceView by remember { mutableStateOf<PlayerView?>(null) }
    var playbackStateListener by remember { mutableStateOf<Player.Listener?>(null) }

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
            remOption.onRenderingFinish()
        }

        override fun onPlaybackStateChanged(playbackState: Int) {
            remOption.onPlayVideo()
            when (playbackState) {
                Player.STATE_IDLE -> {
                    clearPlayer()
                    remOption.onError()
                }

                Player.STATE_ENDED -> {
                    clearPlayer()
                    remOption.onEnd()
                }

                Player.STATE_READY -> {
                    remOption.onReady()
                }

                Player.STATE_BUFFERING -> {
                    remOption.onBuffering()
                }
            }
        }
    }

    // 외부에서 clearPlayer 호출할 수 있게 참조 전달
    LaunchedEffect(Unit) {
        remOption.onPlayerReference.invoke { clearPlayer() }
    }

    DisposableEffect(remOption.videoUrl) {
        if (remOption.videoUrl.isNullOrEmpty()) {
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


        Utils.buildMediaSource(context, remOption.videoUrl ?: "")?.let { mediaSource ->
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

    Box(
        modifier = Modifier
            .padding(
                start = remOption.margin.left.dp,
                top = remOption.margin.top.dp,
                end = remOption.margin.right.dp,
                bottom = remOption.margin.bottom.dp
            )
    ) {
        AndroidView(
            modifier = Modifier
                .hongWidth(remOption.width)
                .hongHeight(remOption.height)
                .aspectRatio(remOption.ratio.aspectRatio())
                .hongBackground(
                    color = remOption.backgroundColorHex,
                    radius = remOption.radius,
                    useShapeCircle = remOption.useShapeCircle,
                    shadow = remOption.shadow,
                    border = remOption.border
                )
                .padding(
                    start = remOption.padding.left.dp,
                    top = remOption.padding.top.dp,
                    end = remOption.padding.right.dp,
                    bottom = remOption.padding.bottom.dp
                )
                .clip(remOption.radius.toRoundedCornerShape()),
            factory = { videoSurfaceView!! }
        )
    }


}
