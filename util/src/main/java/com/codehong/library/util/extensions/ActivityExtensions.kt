package com.codehong.library.util.extensions

import android.app.Activity
import android.os.Build
import androidx.activity.result.ActivityResultLauncher
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat

fun Activity?.applyActivityOpenAnim(openAnim: Int) {
    if (this == null) return

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
        overrideActivityTransition(
            Activity.OVERRIDE_TRANSITION_OPEN,
            openAnim,
            0
        )
    } else {
        overridePendingTransition(openAnim, 0)
    }
}

fun Activity?.applyActivityOpenAnim(enterAnim: Int, exitAnim: Int) {
    if (this == null) return

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
        overrideActivityTransition(
            Activity.OVERRIDE_TRANSITION_OPEN,
            enterAnim,
            exitAnim
        )
    } else {
        overridePendingTransition(enterAnim, exitAnim)
    }
}

fun Activity?.applyActivityCloseAnim(closeAnim: Int) {
    if (this == null) return
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
        overrideActivityTransition(
            Activity.OVERRIDE_TRANSITION_CLOSE,
            0,
            closeAnim
        )
    } else {
        overridePendingTransition(0, closeAnim)
    }
}

fun Activity?.applyActivityCloseAnim(enterAnim: Int, exitAnim: Int) {
    if (this == null) return
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
        overrideActivityTransition(
            Activity.OVERRIDE_TRANSITION_CLOSE,
            enterAnim,
            exitAnim
        )
    } else {
        overridePendingTransition(enterAnim, exitAnim)
    }
}

fun <I> ActivityResultLauncher<I>.safeLaunch(
    activity: Activity?,
    input: I
) {
    try {
        if (activity != null && !activity.isFinishing) {
            launch(input)
        }
    } catch (e: IllegalStateException) {
        e.printStackTrace()
    }
}

fun Activity?.applyStatusBarColor(color: Int) {
    if (this == null) return

    WindowCompat.setDecorFitsSystemWindows(window, true)
    window.statusBarColor = ContextCompat.getColor(
        this,
        color
    )
}
