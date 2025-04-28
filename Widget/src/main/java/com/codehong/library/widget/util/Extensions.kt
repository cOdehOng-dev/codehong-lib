package com.codehong.library.widget.util

import android.app.Activity
import android.content.Context
import android.os.Build
import android.text.ParcelableSpan
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.StrikethroughSpan
import android.text.style.UnderlineSpan
import android.util.DisplayMetrics
import android.util.Log
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.DimenRes
import androidx.compose.ui.graphics.Color
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.text.set
import com.codehong.library.widget.R
import com.codehong.library.widget.rule.HongLayoutParam
import com.codehong.library.widget.rule.HongTextLineBreak
import com.codehong.library.widget.rule.typo.HongFont
import com.codehong.library.widget.rule.typo.HongFontManager
import com.codehong.library.widget.text.HongTextOption

fun Context?.getDrawableResIdByFileName(fileName: String?): Int {
    if (fileName.isNullOrEmpty() || this == null) return -1

    return resources.getIdentifier(fileName, "drawable", packageName)
}


//infix fun Context.dpToPx(dp: Float): Float {
//    val displayMetrics = this.resources.displayMetrics
//    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, displayMetrics)
//}
//
//infix fun Context.dpToIntPx(dp: Float): Int {
//    val displayMetrics = this.resources.displayMetrics
//    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, displayMetrics).roundToInt()
//}

infix fun Context.getDimenFloat(@DimenRes dimenRes: Int): Float = this.resources.getDimension(dimenRes)

infix fun Context.getDimenInt(@DimenRes dimenRes: Int): Int = this.resources.getDimensionPixelSize(dimenRes)



fun CharSequence?.lineBreakSyllable(): String? {
    return this?.toString()?.replace(" ", "\u00A0")
}

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


fun View?.setLayout(
    width: Int = HongLayoutParam.WRAP_CONTENT.value,
    height: Int = HongLayoutParam.WRAP_CONTENT.value
): ViewGroup.MarginLayoutParams? {
    val layoutWidth = when (width) {
        HongLayoutParam.WRAP_CONTENT.value -> ViewGroup.MarginLayoutParams.WRAP_CONTENT
        HongLayoutParam.MATCH_PARENT.value -> ViewGroup.MarginLayoutParams.MATCH_PARENT
        else -> this?.context.dpToPx(width)
    }
    val layoutHeight = when (height) {
        HongLayoutParam.WRAP_CONTENT.value -> ViewGroup.MarginLayoutParams.WRAP_CONTENT
        HongLayoutParam.MATCH_PARENT.value -> ViewGroup.MarginLayoutParams.MATCH_PARENT
        else -> this?.context.dpToPx(height)
    }

    this?.layoutParams = when (this?.layoutParams) {
        is LinearLayout.LayoutParams -> LinearLayout.LayoutParams(layoutWidth, layoutHeight)
        is ConstraintLayout.LayoutParams -> ConstraintLayout.LayoutParams(layoutWidth, layoutHeight)
        is FrameLayout.LayoutParams -> FrameLayout.LayoutParams(layoutWidth, layoutHeight)
        else -> ViewGroup.MarginLayoutParams(layoutWidth, layoutHeight)
    }

    return this?.layoutParams as? ViewGroup.MarginLayoutParams
}

fun ViewGroup?.setLayout(
    width: Int = HongLayoutParam.WRAP_CONTENT.value,
    height: Int = HongLayoutParam.WRAP_CONTENT.value
): ViewGroup.MarginLayoutParams? {
    val layoutWidth = when (width) {
        HongLayoutParam.WRAP_CONTENT.value -> ViewGroup.MarginLayoutParams.WRAP_CONTENT
        HongLayoutParam.MATCH_PARENT.value -> ViewGroup.MarginLayoutParams.MATCH_PARENT
        else -> this?.context.dpToPx(width)
    }
    val layoutHeight = when (height) {
        HongLayoutParam.WRAP_CONTENT.value -> ViewGroup.MarginLayoutParams.WRAP_CONTENT
        HongLayoutParam.MATCH_PARENT.value -> ViewGroup.MarginLayoutParams.MATCH_PARENT
        else -> this?.context.dpToPx(height)
    }

    this?.layoutParams = when (this?.layoutParams) {
        is LinearLayout.LayoutParams -> LinearLayout.LayoutParams(layoutWidth, layoutHeight)
        is ConstraintLayout.LayoutParams -> ConstraintLayout.LayoutParams(layoutWidth, layoutHeight)
        is FrameLayout.LayoutParams -> FrameLayout.LayoutParams(layoutWidth, layoutHeight)
        else -> ViewGroup.MarginLayoutParams(layoutWidth, layoutHeight)
    }

    return this?.layoutParams as? ViewGroup.MarginLayoutParams
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

fun String?.parseComposeColor(): Color {
    return try {
        Color(
            android.graphics.Color.parseColor(
                if (this.isNullOrEmpty()
                    || this.equals("null", true)
                    || this.equals("none", true)
                    || this.equals("blank", true)
                    || this.equals("empty", true)
                ) {
                    "#00000000"
                } else if (this.startsWith("#")) {
                    when (this.length) {
                        7 -> this.replace("#", "#ff")
                        9 -> this
                        else -> "#00000000"
                    }
                } else {
                    when (this.length) {
                        6 -> "#ff$this"
                        8 -> "#$this"
                        else -> "#00000000"
                    }
                }
            )
        )
    } catch (e: Exception) {
        Log.e("ERROR", e.toString())
        androidx.compose.ui.graphics.Color.Transparent
    }
}

fun String?.parseColor(): Int {
    return try {
        android.graphics.Color.parseColor(
            if (this.isNullOrEmpty()
                || this.equals("null", true)
                || this.equals("none", true)
                || this.equals("blank", true)
                || this.equals("empty", true)
            ) {
                "#00000000"
            } else if (this.startsWith("#")) {
                when (this.length) {
                    7 -> this.replace("#", "#ff")
                    9 -> this
                    else -> "#00000000"
                }
            } else {
                when (this.length) {
                    6 -> "#ff$this"
                    8 -> "#$this"
                    else -> "#00000000"
                }
            }
        )
    } catch (e: Exception) {
        Log.e("ERROR", e.toString())
        android.graphics.Color.TRANSPARENT
    }
}

fun SpannableString.setTextSpan(
    context: Context?,
    property: HongTextOption,
    lineBreakType: HongTextLineBreak
): SpannableString {
    if (context == null || property.text.isNullOrEmpty()) return this

    val result = SpannableString.valueOf(this)
    val target = if (lineBreakType == HongTextLineBreak.SYLLABLE) {
        property.text?.lineBreakSyllable()
    } else {
        property.text
    }

    target?.toRegex()?.findAll(result)?.forEach {
        val spanList = arrayListOf<ParcelableSpan>()

        HongFontManager.getFont(
            context,
            (property.fontType ?: HongFont.PRETENDARD_400).name
        )?.let {
            spanList.add(TextSpan(it))
        }

        spanList.add(ForegroundColorSpan(property.colorHex.parseColor()))
//            spanList.add(RelativeSizeSpan(rate))

        if (property.isEnableUnderLine) {
            spanList.add(UnderlineSpan())
        }

        if (property.isEnableCancelLine) {
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
            R.anim.none
        )
    } else {
        overridePendingTransition(openAnim, R.anim.none)
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
            R.anim.none,
            closeAnim
        )
    } else {
        overridePendingTransition(R.anim.none, closeAnim)
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
