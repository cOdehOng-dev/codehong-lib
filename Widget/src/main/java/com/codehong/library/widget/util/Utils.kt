package com.codehong.library.widget.util

import android.content.Context
import android.util.DisplayMetrics
import android.util.TypedValue

object Utils {

    /**
     * Dp 를 pixel 변환
     *
     * @param context applicationContext
     * @param dp
     * @return
     */
    @JvmStatic
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
    @JvmStatic
    fun dpToPx(context: Context?, dp: Int): Int {
        return dpToPx(context, dp.toFloat())
    }

    @JvmStatic
    fun dpToFloatPx(context: Context?, dp: Int): Float {
        return dpToFloatPx(context, dp.toFloat())
    }

    @JvmStatic
    fun dpToFloatPx(context: Context?, dp: Float): Float {
        val metrics = context?.resources?.displayMetrics ?: return 0f
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, metrics)
    }

    @JvmStatic
    fun pxToDp(context: Context?, px: Float): Int {
        val metrics = context?.resources?.displayMetrics ?: return 0
        return (px / (metrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)).toInt()
    }

    @JvmStatic
    fun pxToDp(context: Context?, px: Int): Int {
        return pxToDp(context, px.toFloat())
    }

}