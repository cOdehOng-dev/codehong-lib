package com.codehong.library.widget.icon

import android.content.Context
import android.widget.FrameLayout
import androidx.compose.ui.platform.ComposeView

class HongIconVIew(
    private val context: Context
) {
    fun set(
        option: HongIconOption
    ): ComposeView {
        return ComposeView(context).apply {
            this.layoutParams = FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT
            )
            setContent {
                HongIconCompose(option)
            }
        }
    }
}