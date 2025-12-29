package com.codehong.lib.sample.label

import android.view.View
import androidx.compose.runtime.Composable
import com.codehong.lib.sample.base.BaseSampleMixActivity
import com.codehong.library.widget.label.def.HongLabelBuilder
import com.codehong.library.widget.label.def.HongLabelView
import com.codehong.library.widget.label.def.HongLabelViewCompose
import com.codehong.library.widget.rule.HongSpacingInfo

class SampleLabelActivity : BaseSampleMixActivity() {

    private val option1 = HongLabelBuilder()
        .margin(
            HongSpacingInfo(
                left = 20f,
                bottom = 20f
            )
        )
        .label("text align")
        .description("width가 MATCH_PARENT인 경우, textAlign이 적용되지 않습니다.")
        .applyOption()

    private val option2 = HongLabelBuilder()
        .margin(
            HongSpacingInfo(
                left = 20f,
                bottom = 20f
            )
        )
        .label("text align")
        .applyOption()

    private val optionList get() = listOf(
        option1,
        option2
    )

    override fun optionViewList(): List<View> {
        return mutableListOf<View>().apply {
            optionList.forEach { option ->
                add(HongLabelView(this@SampleLabelActivity).set(option))
            }
        }
    }


    @Composable
    override fun InitCompose() {
        optionList.forEach {
            HongLabelViewCompose(it)
        }
    }
}