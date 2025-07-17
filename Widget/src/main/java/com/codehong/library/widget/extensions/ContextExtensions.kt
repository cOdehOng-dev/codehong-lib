package com.codehong.library.widget.extensions

import android.app.ActivityManager
import android.content.Context
import android.util.DisplayMetrics
import android.util.TypedValue

fun Context?.dpToPx(dp: Float): Int {
    val displayMetrics = this?.resources?.displayMetrics ?: return 0
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        dp,
        displayMetrics
    ).toInt()
}

fun Context?.dpToPx(dp: Int): Int {
    return this.dpToPx(dp.toFloat())
}

fun Context?.dpToFloatPx(dp: Int): Float {
    return dpToFloatPx(dp.toFloat())
}

fun Context?.dpToFloatPx(dp: Float): Float {
    val metrics = this?.resources?.displayMetrics ?: return 0f
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, metrics)
}

fun Context?.pxToDp(px: Float): Int {
    val metrics = this?.resources?.displayMetrics ?: return 0
    return (px / (metrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)).toInt()
}

fun Context?.pxToDp(px: Int): Int {
    return pxToDp(px.toFloat())
}

fun Context?.isAppForeground(): Boolean {
    if (this == null) return false
    val appProcesses = (getSystemService(Context.ACTIVITY_SERVICE) as? ActivityManager)?.runningAppProcesses
    val packageName = packageName
    return appProcesses?.any {
        it.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND &&
                it.processName == packageName
    } ?: false
}