package com.codehong.library.widget.graph.line

import android.content.Context
import android.widget.FrameLayout
import androidx.compose.ui.platform.ComposeView
import com.codehong.library.widget.graph.HongGraphOption

class HongGraphLineView(
    private val context: Context
) {
    fun set(
        option: HongGraphOption
    ): ComposeView {
        return ComposeView(context).apply {
            this.layoutParams = FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT
            )
            setContent {
                HongGraphLineCompose(option)
            }
        }
    }
}