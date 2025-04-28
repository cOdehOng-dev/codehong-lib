package com.codehong.library.widget.rule

import android.text.TextUtils
import androidx.compose.ui.text.style.TextOverflow

enum class HongTextOverflow(
    val value: TextOverflow,
    val truncateAt: TextUtils.TruncateAt?
) {
    CLIP(TextOverflow.Clip, null),
    ELLIPSIS(TextOverflow.Ellipsis, TextUtils.TruncateAt.END),
    VISIBLE(TextOverflow.Visible, null)
}
