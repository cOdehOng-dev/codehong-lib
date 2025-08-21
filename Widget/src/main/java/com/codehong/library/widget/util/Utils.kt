package com.codehong.library.widget.util

import android.content.Context
import android.net.Uri
import androidx.annotation.OptIn
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.datasource.DefaultHttpDataSource
import androidx.media3.exoplayer.hls.HlsMediaSource
import androidx.media3.exoplayer.source.MediaSource
import androidx.media3.exoplayer.source.ProgressiveMediaSource

object Utils {

    @OptIn(UnstableApi::class)
    fun buildMediaSource(
        context: Context?,
        url: String
    ): MediaSource? {
        if (context == null) return null

        return try {
            val mediaItem = MediaItem.Builder()
                .setUri(Uri.parse(url))
                .build()

            val dataSourceFactory = DefaultHttpDataSource.Factory()
            val mediaSourceFactory: MediaSource.Factory = when {
                url.endsWith(".m3u8") -> HlsMediaSource.Factory(dataSourceFactory)
                else -> ProgressiveMediaSource.Factory(dataSourceFactory)
            }

            mediaSourceFactory.createMediaSource(mediaItem)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

}