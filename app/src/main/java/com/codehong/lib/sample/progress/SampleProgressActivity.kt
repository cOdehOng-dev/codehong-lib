package com.codehong.lib.sample.progress

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.codehong.lib.sample.base.BaseSampleComposeActivity
import com.codehong.library.widget.extensions.disableRippleClickable
import com.codehong.library.widget.progress.HongProgress

class SampleProgressActivity : BaseSampleComposeActivity() {

    @Composable
    override fun InitCompose() {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .disableRippleClickable { },
            contentAlignment = Alignment.Center
        ) {
            HongProgress()
        }
    }

}