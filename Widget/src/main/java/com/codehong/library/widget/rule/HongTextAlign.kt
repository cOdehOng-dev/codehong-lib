package com.codehong.library.widget.rule

import android.view.Gravity
import android.view.View
import androidx.compose.ui.text.style.TextAlign

enum class HongTextAlign(
    val value: TextAlign,
    val view: Int,
    val gravity: Int
) {
    LEFT(
        TextAlign.Left,
        View.TEXT_ALIGNMENT_TEXT_START,
        Gravity.START or Gravity.CENTER_VERTICAL
    ),
    RIGHT(
        TextAlign.Right,
        View.TEXT_ALIGNMENT_TEXT_END,
        Gravity.END or Gravity.CENTER_VERTICAL
    ),
    CENTER(
        TextAlign.Center,
        View.TEXT_ALIGNMENT_CENTER,
        Gravity.CENTER
    )
}
