package com.codehong.library.widget.util

import android.app.Activity
import android.content.Context
import android.os.Build
import android.text.ParcelableSpan
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.StrikethroughSpan
import android.text.style.UnderlineSpan
import android.util.TypedValue
import android.view.View
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.text.set
import androidx.core.view.WindowCompat
import com.codehong.library.widget.R
import com.codehong.library.widget.extensions.parseColor
import com.codehong.library.widget.rule.HongTextLineBreak
import com.codehong.library.widget.rule.typo.HongFont
import com.codehong.library.widget.rule.typo.HongFontManager
import com.codehong.library.widget.text.HongTextBuilder


fun CharSequence?.lineBreakSyllable(): String? {
    return this?.toString()?.replace(" ", "\u00A0")
}



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

fun SpannableString.setTextSpan(
    context: Context?,
    builder: HongTextBuilder,
    lineBreakType: HongTextLineBreak
): SpannableString {
    if (context == null || builder.option.text.isNullOrEmpty()) return this

    val result = SpannableString.valueOf(this)
    val target = if (lineBreakType == HongTextLineBreak.SYLLABLE) {
        builder.option.text?.lineBreakSyllable()
    } else {
        builder.option.text
    }

    target?.toRegex()?.findAll(result)?.forEach {
        val spanList = arrayListOf<ParcelableSpan>()

        HongFontManager.getFont(
            context,
            (builder.option.fontType ?: HongFont.PRETENDARD_400).name
        )?.let {
            spanList.add(TextSpan(it))
        }

        spanList.add(ForegroundColorSpan(builder.option.colorHex.parseColor()))

        if (builder.option.isEnableUnderLine) {
            spanList.add(UnderlineSpan())
        }

        if (builder.option.isEnableCancelLine) {
            spanList.add(StrikethroughSpan())
        }

        spanList.forEach { span ->
            result[it.range.first, it.range.last + 1] = span
        }
    }

    return result
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

