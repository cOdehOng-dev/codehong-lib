package com.codehong.lib.sample.base

import android.os.Bundle
import android.view.View
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.codehong.lib.sample.SampleConst
import com.codehong.lib.sample.databinding.ActivitySampleMixBinding
import com.codehong.lib.sample.databinding.ViewSampleDespContainerBinding
import com.codehong.library.widget.rule.HongLayoutParam
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.HongWidgetType
import com.codehong.library.widget.rule.HongWidgetType.Companion.toHongWidgetType
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.color.HongColor.Companion.toColor
import com.codehong.library.widget.rule.typo.HongTypo
import com.codehong.library.widget.text.HongTextBuilder

abstract class BaseSampleMixActivity : BaseActivity() {

    private lateinit var binding: ActivitySampleMixBinding

    private var widgetType: HongWidgetType? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySampleMixBinding.inflate(layoutInflater)
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

        if (optionDesscriptionViewList().isNotEmpty()) {
            optionDesscriptionViewList().forEach {
                sampleDescriptionContainerView(
                    desp = it.first,
                    childView = it.second
                ).apply {
                    binding.vContainer.llView.addView(this)
                }
            }
        } else {
            optionViewList().forEach {
                binding.vContainer.llView.addView(it)
            }
        }
        binding.vContainer.vCompose.setContent {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(HongColor.WHITE_100.toColor())
            ) {
                InitCompose()
            }
        }
    }

    protected open fun optionViewList(): List<View> = mutableListOf()
    protected open fun optionDesscriptionViewList(): List<Pair<String, View>> = mutableListOf()

    @Composable
    protected abstract fun InitCompose()


    protected fun sampleDescriptionContainerView(
        desp: String,
        childView: View
    ): View {
        val despBinding = ViewSampleDespContainerBinding.inflate(layoutInflater)

        despBinding.vText.set(
            HongTextBuilder()
                .width(HongLayoutParam.MATCH_PARENT.value)
                .text(desp)
                .padding(
                    HongSpacingInfo(
                        left = 20f,
                        right = 20f,
                    )
                )
                .color(HongColor.BLACK_100.hex)
                .typography(HongTypo.BODY_14_B)
                .applyOption()
        )

        if (despBinding.flContainer.childCount > 0) {
            despBinding.flContainer.removeAllViews()
        }
        despBinding.flContainer.addView(childView)

        return despBinding.root
    }

}
