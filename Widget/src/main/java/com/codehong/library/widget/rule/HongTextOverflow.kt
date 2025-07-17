package com.codehong.library.widget.rule

import android.text.TextUtils
import androidx.compose.ui.text.style.TextOverflow

enum class HongTextOverflow(
    val alias: String,
    val value: TextOverflow,
    val truncateAt: TextUtils.TruncateAt?
) {
    CLIP("clip", TextOverflow.Clip, null),
    ELLIPSIS("ellipsis", TextOverflow.Ellipsis, TextUtils.TruncateAt.END),
    VISIBLE("visible", TextOverflow.Visible, null);


    companion object {
        fun String?.toHongTextOverFlow(): HongTextOverflow {
            return entries.firstOrNull { it.alias.equals(this, true) } ?: ELLIPSIS
        }

        fun HongTextOverflow?.toHongTextOverflowToAlias(): String {
            return this?.alias ?: ELLIPSIS.alias
        }
    }
}
