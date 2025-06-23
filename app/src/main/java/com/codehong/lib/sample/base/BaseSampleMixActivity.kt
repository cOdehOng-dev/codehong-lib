package com.codehong.lib.sample.base

import android.os.Bundle
import android.view.View
import androidx.compose.runtime.Composable
import com.codehong.lib.sample.SampleConst
import com.codehong.lib.sample.databinding.ActivitySampleBinding
import com.codehong.library.widget.rule.HongWidgetType
import com.codehong.library.widget.rule.HongWidgetType.Companion.toHongWidgetType

abstract class BaseSampleMixActivity : BaseActivity() {

    private lateinit var binding: ActivitySampleBinding

    private var widgetType: HongWidgetType? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySampleBinding.inflate(layoutInflater)
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

        propertyViewList().forEach {
            binding.llView.addView(it)
        }
        binding.vCompose.setContent {
            InitCompose()
        }
    }

    protected abstract fun propertyViewList(): List<View>

    @Composable
    protected abstract fun InitCompose()

}
