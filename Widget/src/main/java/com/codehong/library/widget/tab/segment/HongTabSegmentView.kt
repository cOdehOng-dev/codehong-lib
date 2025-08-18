package com.codehong.library.widget.tab.segment

import android.content.Context
import androidx.compose.ui.platform.ComposeView

class HongTabSegmentView(
    private val context: Context
) {

    fun set(
        option: HongTabSegmentOption
    ): ComposeView {
        return ComposeView(context).apply {
            setContent {
                HongTabSegmentCompose(option)
            }
        }
    }
}
