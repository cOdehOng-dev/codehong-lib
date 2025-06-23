package com.codehong.lib.sample.base

import android.os.Bundle
import android.view.View
import com.codehong.lib.sample.SampleConst
import com.codehong.lib.sample.databinding.ActivityBaseSampleBinding
import com.codehong.library.widget.rule.HongWidgetType
import com.codehong.library.widget.rule.HongWidgetType.Companion.toHongWidgetType

abstract class BaseSampleActivity : BaseActivity() {

    private lateinit var binding: ActivityBaseSampleBinding

    private var widgetType: HongWidgetType? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBaseSampleBinding.inflate(layoutInflater)
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
        binding.llView.addView(initSample())

    }

    protected abstract fun initSample(): View

}