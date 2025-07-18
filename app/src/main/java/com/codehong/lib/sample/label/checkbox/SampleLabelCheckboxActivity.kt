package com.codehong.lib.sample.label.checkbox

import android.view.View
import androidx.compose.runtime.Composable
import com.codehong.lib.sample.base.BaseSampleMixActivity
import com.codehong.library.widget.label.checkbox.HongLabelCheckboxBuilder
import com.codehong.library.widget.label.checkbox.HongLabelCheckboxCompose
import com.codehong.library.widget.label.checkbox.HongLabelCheckboxView
import com.codehong.library.widget.rule.HongSpacingInfo

class SampleLabelCheckboxActivity : BaseSampleMixActivity() {

    private val option1 = HongLabelCheckboxBuilder()
        .margin(
            HongSpacingInfo(
                left = 20f,
            )
        )
        .label("기본")
        .applyOption()

    private val option2 = HongLabelCheckboxBuilder()
        .margin(
            HongSpacingInfo(
                top = 10f,
                left = 20f,
            )
        )
        .label("체크활성화")
        .description("체크 활성화된 옵션")
        .checkState(true)
        .applyOption()

    private val optionList get() = listOf(
        option1,
        option2
    )

    override fun optionViewList(): List<View> {
        return mutableListOf<View>().apply {
            optionList.forEach {
                add(HongLabelCheckboxView(this@SampleLabelCheckboxActivity).set(it))
            }
        }
    }

    @Composable
    override fun InitCompose() {
        optionList.forEach { option ->
            HongLabelCheckboxCompose(option)
        }

    }
}