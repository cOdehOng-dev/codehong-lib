package com.codehong.lib.sample.text.check

import android.util.Log
import android.view.View
import androidx.compose.runtime.Composable
import com.codehong.lib.sample.base.BaseSampleMixActivity
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.text.check.HongCheckTextBuilder
import com.codehong.library.widget.text.check.HongCheckTextView

class SampleCheckTextActivity : BaseSampleMixActivity() {

    private val option1 = HongCheckTextBuilder()
        .margin(
            HongSpacingInfo(
                left = 20f,
                bottom = 20f
            )
        )
        .size(22)
        .text("기본 CheckTextView 예시")
        .applyOption()

    private val option2 = HongCheckTextBuilder()
        .margin(
            HongSpacingInfo(
                left = 20f
            )
        )
        .text("[활성화] 기본 CheckTextView 예시")
        .checkState(true)
        .onCheck {
            Log.w("TAG", "test here check: $it")
        }
        .applyOption()

    private val optionList get() = listOf(
        option1,
        option2
    )

    override fun optionViewList(): List<View> {
        return mutableListOf<View>().apply {
            optionList.forEach {
                add(HongCheckTextView(this@SampleCheckTextActivity).set(it))
            }
        }
    }

    @Composable
    override fun InitCompose() {

    }
}