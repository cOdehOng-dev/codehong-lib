package com.codehong.library.widget.util

import android.content.Context

fun Context?.getDrawableResIdByFileName(fileName: String?): Int {
    if (fileName.isNullOrEmpty() || this == null) return -1

    return resources.getIdentifier(fileName, "drawable", packageName)
}
