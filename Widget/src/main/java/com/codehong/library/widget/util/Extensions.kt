package com.codehong.library.widget.util

import android.app.Activity
import android.os.Build
import android.util.TypedValue
import android.view.View
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import com.codehong.library.widget.R
import com.codehong.library.widget.rule.typo.HongFont
import com.codehong.library.widget.rule.typo.HongFontManager


fun TextView?.setTextSize(dp: Int?, defDp: Int) {
    if (this == null) return

    if (dp == null) {
        setTextSize(defDp)
        return
    }

    setTextSize(dp)
}

fun TextView?.setTextSize(size: Int?) {
    this ?: return
    size ?: return

    this.setTextSize(TypedValue.COMPLEX_UNIT_DIP, size.toFloat())
}

fun TextView?.setTextFont(fontType: HongFont?, defFont: HongFont) {
    if (this == null) return

    if (fontType == null) {
        typeface = HongFontManager.getFont(context, defFont)
        return
    }

    typeface = HongFontManager.getFont(context, fontType)
}

fun Activity?.applyActivityOpenAnim(openAnim: Int) {
    if (this == null) return

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
        overrideActivityTransition(
            Activity.OVERRIDE_TRANSITION_OPEN,
            openAnim,
            R.anim.honglib_none
        )
    } else {
        overridePendingTransition(openAnim, R.anim.honglib_none)
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
            R.anim.honglib_none,
            closeAnim
        )
    } else {
        overridePendingTransition(R.anim.honglib_none, closeAnim)
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



fun View?.applyRatio(ratio: String?) {
    if (this == null || ratio.isNullOrEmpty()) return

    try {
        val params = this.layoutParams as ConstraintLayout.LayoutParams
        params.dimensionRatio = ratio
        this.layoutParams = params
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

fun String?.aspectRatio(): Float {
    if (this.isNullOrEmpty()) return 0f
    val parts = this.split(":")
    return if (parts.size == 2) {
        parts[0].toFloat() / parts[1].toFloat()
    } else {
        1f / 1f
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

