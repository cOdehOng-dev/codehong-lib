package com.codehong.lib.sample.captureshare

import androidx.compose.runtime.Composable
import com.codehong.lib.sample.base.BaseSampleComposeActivity
import com.codehong.library.widget.captureshare.HongCaptureShareMainScreen

class SampleCaptureShareComposeActivity : BaseSampleComposeActivity() {

    @Composable
    override fun InitCompose() {
        HongCaptureShareMainScreen(
            activity = this,
            shareLink = "https://your.link/here"
        )
    }

}