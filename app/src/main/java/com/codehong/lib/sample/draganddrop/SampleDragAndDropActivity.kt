package com.codehong.lib.sample.draganddrop

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.codehong.lib.sample.base.BaseSampleComposeActivity

class SampleDragAndDropActivity : BaseSampleComposeActivity() {

    @Composable
    override fun InitCompose() {
        var isEditMode by remember { mutableStateOf(false) }

        BackHandler(
            enabled = isEditMode
        ) {
            isEditMode = false
        }

        DragAndDropScreen(isEditMode) {
            isEditMode = true
        }
    }
}