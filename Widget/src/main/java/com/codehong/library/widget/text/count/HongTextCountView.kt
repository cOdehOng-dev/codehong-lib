package com.codehong.library.widget.text.count

import android.content.Context
import android.widget.FrameLayout
import androidx.compose.ui.platform.ComposeView

class HongTextCountView(
    private val context: Context
) {
    fun set(
        option: HongTextCountOption
    ): ComposeView {
        return ComposeView(context).apply {
            this.layoutParams = FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT
            )
            setContent {
                HongTextCountCompose(option)
            }
        }
    }
}