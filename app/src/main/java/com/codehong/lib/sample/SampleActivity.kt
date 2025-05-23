package com.codehong.lib.sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Column
import com.codehong.lib.sample.databinding.ActivitySampleBinding
import com.codehong.library.widget.HongSpacingInfo
import com.codehong.library.widget.rule.HongLayoutParam
import com.codehong.library.widget.rule.HongTextLineBreak
import com.codehong.library.widget.rule.HongWidgetType
import com.codehong.library.widget.rule.HongWidgetType.Companion.toHongWidgetType
import com.codehong.library.widget.rule.typo.HongTypo
import com.codehong.library.widget.text.HongTextCompose
import com.codehong.library.widget.text.HongTextOption
import com.codehong.library.widget.text.HongTextView
import com.codehong.library.widget.util.applyActivityCloseAnim
import com.codehong.library.widget.util.applyActivityOpenAnim

class SampleActivity : ComponentActivity() {

    private lateinit var binding: ActivitySampleBinding

    private var componentType: HongWidgetType? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        applyActivityOpenAnim(R.anim.slide_right_in)
        binding = ActivitySampleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        componentType = intent.getStringExtra("componentType").toHongWidgetType()

        if (componentType == HongWidgetType.NO_VALUE) {
            finish()
            return
        }

        binding.vHeader.init(
            title = "${componentType?.value} 샘플",
            back = {
                finish()
            }
        )

        initSample()
    }

    override fun finish() {
        super.finish()
        applyActivityCloseAnim(R.anim.slide_right_out)
    }

    private fun initSample() {
        when (componentType) {
            HongWidgetType.TEXT -> {
                val property1 = HongTextOption.Builder()
                    .text("테스트입니다요")
                    .typography(HongTypo.BODY_16_B)
                    .lineBreak(HongTextLineBreak.DEFAULT)
                    .margin(HongSpacingInfo(10f, 10f, 10f, 10f))
                    .build()

                val property2 = HongTextOption.Builder()
                    .width(HongLayoutParam.MATCH_PARENT.value)
                    .text(
                        "김민재의 부상 투혼은 이어졌다. 목이 아프고, 기침이 심한 상태에서 경기에 출전했다. 허리 통증까지 겪고 있는 것으로 알려졌다. 고 휴식이 필요한 상황인 것으로 알려졌다."
                    )
                    .typography(HongTypo.BODY_16_B)
                    .isEnableCancelLine(true)
                    .lineBreak(HongTextLineBreak.DEFAULT)
                    .margin(HongSpacingInfo(10f, 10f, 10f, 10f))
                    .build()

                binding.llView.addView(HongTextView(this).set(option = property1))
                binding.llView.addView(HongTextView(this).set(option = property2))

                binding.vCompose.setContent {
                    Column {
                        HongTextCompose(option = property1)
                        HongTextCompose(option = property2)
                    }
                }
            }

            else -> {
            }
        }
    }
}
