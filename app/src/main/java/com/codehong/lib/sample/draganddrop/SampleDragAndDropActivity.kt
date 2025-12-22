package com.codehong.lib.sample.draganddrop

import androidx.compose.runtime.Composable
import com.codehong.lib.sample.base.BaseSampleComposeActivity

class SampleDragAndDropActivity : BaseSampleComposeActivity() {

    @Composable
    override fun InitCompose() {
        DragAndDropScreen {
            finish()
        }
    }
}