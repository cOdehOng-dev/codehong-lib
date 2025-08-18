package com.codehong.library.widget.rule

import android.view.Gravity
import android.view.View
import androidx.compose.ui.text.style.TextAlign

enum class HongTextAlign(
    val alias: String,
    val value: TextAlign,
    val view: Int,
    val gravity: Int
) {
    LEFT(
        "left",
        TextAlign.Left,
        View.TEXT_ALIGNMENT_TEXT_START,
        Gravity.START or Gravity.CENTER_VERTICAL
    ),
    RIGHT(
        "right",
        TextAlign.Right,
        View.TEXT_ALIGNMENT_TEXT_END,
        Gravity.END or Gravity.CENTER_VERTICAL
    ),
    CENTER(
        "center",
        TextAlign.Center,
        View.TEXT_ALIGNMENT_CENTER,
        Gravity.CENTER
    );

    companion object {
        fun HongTextAlign?.toHongTextAlignToAlias(): String {
            return this?.alias ?: LEFT.alias
        }
        fun String?.toHongTextAlign(): HongTextAlign {
            return entries.firstOrNull { it.alias.equals(this, true) } ?: LEFT
        }
    }
}
