package com.codehong.library.widget.textfield.border

import android.content.Context
import androidx.compose.ui.platform.ComposeView

class HongTextFieldBorderView(
    private val context: Context
) {

    fun set(
        option: HongTextFieldBorderOption
    ): ComposeView {
        return ComposeView(context).apply {
            setContent {
                HongTextFieldBorderCompose(option)
            }
        }
    }
}