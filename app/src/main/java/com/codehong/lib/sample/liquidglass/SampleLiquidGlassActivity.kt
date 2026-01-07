package com.codehong.lib.sample.liquidglass

import android.os.Bundle
import androidx.activity.compose.setContent
import com.codehong.lib.sample.base.BaseActivity
import com.codehong.library.widget.liquid.LiquidGlassScreen

class SampleLiquidGlassActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LiquidGlassScreen()
        }
    }
}
