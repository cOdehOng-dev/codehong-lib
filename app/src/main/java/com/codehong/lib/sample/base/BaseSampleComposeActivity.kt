package com.codehong.lib.sample.base

import android.os.Bundle
import androidx.compose.runtime.Composable
import com.codehong.lib.sample.SampleConst
import com.codehong.lib.sample.databinding.ActivityBaseSampleComposeBinding
import com.codehong.library.widget.rule.HongWidgetType
import com.codehong.library.widget.rule.HongWidgetType.Companion.toHongWidgetType

abstract class BaseSampleComposeActivity : BaseActivity() {

    private lateinit var binding: ActivityBaseSampleComposeBinding

    private var widgetType: HongWidgetType? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBaseSampleComposeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        widgetType = intent.getStringExtra(SampleConst.WIDGET_TYPE).toHongWidgetType()

        if (widgetType == HongWidgetType.NO_VALUE) {
            finish()
            return
        }

        binding.vHeader.init(
            title = "${widgetType?.value} 샘플",
            back = {
                finish()
            }
        )

        binding.vCompose.setContent {
            InitSample()
        }
    }

    @Composable
    protected abstract fun InitSample()

}
