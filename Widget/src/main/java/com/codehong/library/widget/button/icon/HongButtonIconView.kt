package com.codehong.library.widget.button.icon

import android.content.Context
import android.widget.FrameLayout
import androidx.compose.ui.platform.ComposeView

class HongButtonIconView(
    private val context: Context
) {
    fun set(
        option: HongButtonIconOption
    ): ComposeView {
        return ComposeView(context).apply {
            this.layoutParams = FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT
            )
            setContent {
                HongButtonIconCompose(option)
            }
        }
    }
}