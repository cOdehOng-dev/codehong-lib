package com.codehong.library.widget.tab.flow

import android.content.Context
import androidx.compose.ui.platform.ComposeView

class HongTabFlowView(
    private val context: Context
) {

    fun set(
        option: HongTabFlowOption
    ): ComposeView {
        return ComposeView(context).apply {
            setContent {
                HongTabFlowCompose(option)
            }
        }
    }
}