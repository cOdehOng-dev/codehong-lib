package com.codehong.lib.sample.label.input

import android.view.View
import androidx.compose.runtime.Composable
import com.codehong.lib.sample.base.BaseSampleMixActivity
import com.codehong.library.widget.label.input.HongLabelInputBuilder
import com.codehong.library.widget.label.input.HongLabelInputCompose
import com.codehong.library.widget.label.input.HongLabelInputView
import com.codehong.library.widget.rule.HongLayoutParam
import com.codehong.library.widget.rule.HongSpacingInfo

class SampleLabelInputActivity : BaseSampleMixActivity() {
    private val option1 = HongLabelInputBuilder()
        .width(HongLayoutParam.MATCH_PARENT.value)
        .padding(
            HongSpacingInfo(
                top = 10f,
                bottom = 10f,
                left = 16f,
                right = 16f
            )
        )
        .label("레이블")
        .description("레이블 설명하는 테스트이에요.")
        .placeholder("월요일 좋아!!!!")
        .applyOption()

    private val option2 = HongLabelInputBuilder()
        .width(HongLayoutParam.MATCH_PARENT.value)
        .padding(
            HongSpacingInfo(
                top = 10f,
                bottom = 10f,
                left = 16f,
                right = 16f
            )
        )
        .label("레이블")
        .description("레이블 설명하는 테스트이에요.")
        .applyOption()

    private val optionList get() = listOf(
        option1,
        option2
    )

    override fun optionViewList(): List<View> {
        return mutableListOf<View>().apply {
            optionList.forEach { option ->
                add(HongLabelInputView(this@SampleLabelInputActivity).set(option))
            }
        }
    }

    @Composable
    override fun InitCompose() {
        optionList.forEach {
            HongLabelInputCompose(it)
        }
    }

}