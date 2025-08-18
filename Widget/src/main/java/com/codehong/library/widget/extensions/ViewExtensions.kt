package com.codehong.library.widget.extensions

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.text.InputType
import android.text.SpannableString
import android.text.Spanned
import android.text.style.AbsoluteSizeSpan
import android.text.style.ForegroundColorSpan
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatEditText
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.ResourcesCompat
import com.codehong.library.widget.rule.HongBorderInfo
import com.codehong.library.widget.rule.HongLayoutParam
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.keyboard.HongKeyboardActionType
import com.codehong.library.widget.rule.keyboard.HongKeyboardType
import com.codehong.library.widget.rule.radius.HongRadiusInfo
import com.codehong.library.widget.rule.radius.HongRadiusInfo.Companion.toCornerRadii
import com.codehong.library.widget.text.HongTextOption

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

fun ViewGroup?.setLayout(
    width: Int = HongLayoutParam.WRAP_CONTENT.value,
    height: Int = HongLayoutParam.WRAP_CONTENT.value,
    weight: Float = 0f,
    gravity: Int = Gravity.START
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
        is LinearLayout.LayoutParams -> LinearLayout.LayoutParams(layoutWidth, layoutHeight).apply {
            this.weight = weight
            this.gravity = gravity
        }
        is ConstraintLayout.LayoutParams -> ConstraintLayout.LayoutParams(layoutWidth, layoutHeight)
        is FrameLayout.LayoutParams -> FrameLayout.LayoutParams(layoutWidth, layoutHeight)
        else -> ViewGroup.MarginLayoutParams(layoutWidth, layoutHeight)
    }

    return this?.layoutParams as? ViewGroup.MarginLayoutParams
}

/**
 * View 확장 함수 - dp 단위로 패딩 설정
 *
 * @param padding HongSpacingInfo 객체로 패딩 설정
 */
fun View?.hongPadding(
    padding: HongSpacingInfo
): View? {
    if (this == null) return null

    this.setPaddingRelative(
        context.dpToPx(padding.left),
        context.dpToPx(padding.top),
        context.dpToPx(padding.right),
        context.dpToPx(padding.bottom)
    )

    return this
}

/**
 * View 확장 함수 - 마진 설정
 *
 * @param margin HongSpacingInfo 객체로 마진 설정
 */
fun View?.hongMargin(
    margin: HongSpacingInfo
) {
    if (this == null) return

    val params = layoutParams
    if (params is ViewGroup.MarginLayoutParams) {
        params.marginStart = context.dpToPx(margin.left)
        params.marginEnd = context.dpToPx(margin.right)

        params.topMargin = context.dpToPx(margin.top)
        params.bottomMargin = context.dpToPx(margin.bottom)

        this.layoutParams = params
    }
}

fun View?.hongBackground(
    backgroundColor: String,
    border: HongBorderInfo = HongBorderInfo(),
    radius: HongRadiusInfo = HongRadiusInfo(),
    useShapeCircle: Boolean = false,
): View? {
    if (this == null) return null

    if (useShapeCircle) {
        this.background = GradientDrawable().apply {
            shape = GradientDrawable.OVAL
            setColor(Color.parseColor(backgroundColor))
            setStroke(context.dpToPx(border.width), Color.parseColor(border.color))
        }
    } else {
        this.background = GradientDrawable().apply {
            shape = GradientDrawable.RECTANGLE
            setColor(Color.parseColor(backgroundColor))
            cornerRadii = radius.toCornerRadii(context)
            setStroke(context.dpToPx(border.width), Color.parseColor(border.color))
        }
    }

    return this
}


/**
 * AppCompatEditText 확장 함수 - 키보드 옵션 설정
 *
 * @param keyboardOption 키보드 타입과 액션 타입의 pair
 */
fun AppCompatEditText.toKeyboardOptions(
    keyboardOption: Pair<HongKeyboardType, HongKeyboardActionType>
) {
    this.inputType = when (keyboardOption.first) {
        HongKeyboardType.TEXT -> InputType.TYPE_CLASS_TEXT
        HongKeyboardType.NUMBER -> InputType.TYPE_CLASS_NUMBER
        HongKeyboardType.PHONE -> InputType.TYPE_CLASS_PHONE
        HongKeyboardType.EMAIL -> InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
        HongKeyboardType.URL -> InputType.TYPE_TEXT_VARIATION_URI
        HongKeyboardType.PASSWORD -> InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
        HongKeyboardType.NUMBER_PASSWORD -> InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_VARIATION_PASSWORD
        HongKeyboardType.DECIMAL -> InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL
        HongKeyboardType.ASCIITEXT -> InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
        else -> InputType.TYPE_CLASS_TEXT
    }

    val imeOptions = when (keyboardOption.second) {
        HongKeyboardActionType.DONE -> EditorInfo.IME_ACTION_DONE
        HongKeyboardActionType.GO -> EditorInfo.IME_ACTION_GO
        HongKeyboardActionType.SEARCH -> EditorInfo.IME_ACTION_SEARCH
        HongKeyboardActionType.SEND -> EditorInfo.IME_ACTION_SEND
        HongKeyboardActionType.NEXT -> EditorInfo.IME_ACTION_NEXT
        HongKeyboardActionType.PREVIOUS -> EditorInfo.IME_ACTION_PREVIOUS
        else -> EditorInfo.IME_ACTION_DONE
    }
    this.imeOptions = imeOptions
}

fun AppCompatEditText.setUseHideKeyboard(useHideKeyboard: Boolean) {
    if (!useHideKeyboard) return

    setOnEditorActionListener { _, _, _ ->
        val imm =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
        clearFocus()
        true
    }
}

fun AppCompatEditText.setHintStyle(
    textOption: HongTextOption
) {
    val spannableHint = SpannableString(textOption.text ?: "").apply {
        setSpan(
            ForegroundColorSpan(
                Color.parseColor(
                    textOption.colorHex ?: HongColor.BLACK_100.hex
                )
            ), 0, length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        setSpan(AbsoluteSizeSpan(textOption.size ?: 10, true), 0, length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
    }
    hint = spannableHint
}

fun AppCompatEditText.setHintStyle(
    hintText: String?,
    hintColor: String,
    hintSize: Int
) {
    val spannableHint = SpannableString(hintText ?: "").apply {
        setSpan(
            ForegroundColorSpan(Color.parseColor(hintColor)),
            0,
            length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        setSpan(AbsoluteSizeSpan(hintSize, true), 0, length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
    }
    this.hint = spannableHint
}

fun AppCompatEditText.checkFont(
    input: String?,
    hintFontId: Int,
    inputFontId: Int
) {
    val isHintShown = input.isNullOrEmpty() && this.hint != null
    if (isHintShown) {
        this.typeface = ResourcesCompat.getFont(
            context,
            hintFontId
        )
    } else {
        this.typeface = ResourcesCompat.getFont(
            context,
            inputFontId
        )
    }
}
