package com.codehong.lib.sample.text.check

import android.util.Log
import android.view.View
import androidx.compose.runtime.Composable
import com.codehong.lib.sample.base.BaseSampleMixActivity
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.typo.HongTypo
import com.codehong.library.widget.text.HongTextBuilder
import com.codehong.library.widget.text.check.HongCheckTextBuilder
import com.codehong.library.widget.text.check.HongCheckTextCompose
import com.codehong.library.widget.text.check.HongCheckTextOption
import com.codehong.library.widget.text.check.HongCheckTextView

class SampleCheckTextActivity : BaseSampleMixActivity() {

    private val option1 = HongCheckTextBuilder()
        .margin(
            HongSpacingInfo(
                left = 20f,
                bottom = 20f
            )
        )
        .checkSize(22)
        .arrowSize(16)
        .text("기본 CheckTextView 예시")
        .applyOption()

    private val option2 = HongCheckTextBuilder()
        .margin(
            HongSpacingInfo(
                left = 20f
            )
        )
        .checkSize(30)
        .arrowSize(20)
        .text("휴대폰/카드 본인확인 서비스")
        .textOption(
            HongTextBuilder()
                .copy(HongCheckTextOption.DEFAULT_TEXT_OPTION)
                .typography(HongTypo.BODY_15)
                .color(HongColor.GRAY_70)
                .applyOption()
        )
        .checkState(true)
        .onClick {
            Log.d("TAG", "click")
        }
        .onCheck {
            Log.w("TAG", "check: $it")
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
        optionList.forEach { option ->
            HongCheckTextCompose(option)
        }
    }
}