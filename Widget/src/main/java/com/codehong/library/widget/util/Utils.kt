package com.codehong.library.widget.util

import android.content.Context
import android.net.Uri
import android.util.DisplayMetrics
import android.util.TypedValue
import androidx.annotation.OptIn
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.datasource.DefaultHttpDataSource
import androidx.media3.exoplayer.hls.HlsMediaSource
import androidx.media3.exoplayer.source.MediaSource
import androidx.media3.exoplayer.source.ProgressiveMediaSource

object Utils {

    /**
     * Dp 를 pixel 변환
     *
     * @param context applicationContext
     * @param dp
     * @return
     */
    fun dpToPx(context: Context?, dp: Float): Int {
        val displayMetrics = context?.resources?.displayMetrics ?: return 0
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp,
            displayMetrics
        ).toInt()
    }

    /**
     * Dp 를 pixel 변환
     *
     * @param context applicationContext
     * @param dp
     * @return
     */
    @Deprecated("")
    @JvmStatic
    fun dpToPx(context: Context?, dp: Int): Int {
        return dpToPx(context, dp.toFloat())
    }

    @Deprecated("")
    @JvmStatic
    fun dpToFloatPx(context: Context?, dp: Int): Float {
        return dpToFloatPx(context, dp.toFloat())
    }

    @Deprecated("")
    @JvmStatic
    fun dpToFloatPx(context: Context?, dp: Float): Float {
        val metrics = context?.resources?.displayMetrics ?: return 0f
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, metrics)
    }

    @Deprecated("")
    @JvmStatic
    fun pxToDp(context: Context?, px: Float): Int {
        val metrics = context?.resources?.displayMetrics ?: return 0
        return (px / (metrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)).toInt()
    }

    @Deprecated("")
    @JvmStatic
    fun pxToDp(context: Context?, px: Int): Int {
        return pxToDp(context, px.toFloat())
    }

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