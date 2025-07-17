package com.codehong.lib.sample.text

import android.view.View
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.codehong.lib.sample.base.BaseSampleMixActivity
import com.codehong.library.widget.rule.HongLayoutParam
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.HongTextLineBreak
import com.codehong.library.widget.rule.typo.HongTypo
import com.codehong.library.widget.text.HongTextBuilder
import com.codehong.library.widget.text.HongTextCompose
import com.codehong.library.widget.text.HongTextView

class SampleTextActivity : BaseSampleMixActivity() {

    private val option1 = HongTextBuilder()
        .text("테스트입니다요")
        .typography(HongTypo.BODY_16_B)
        .lineBreak(HongTextLineBreak.DEFAULT)
        .margin(HongSpacingInfo(10f, 10f, 10f, 10f))
        .applyOption()

    private val option2 = HongTextBuilder()
        .width(HongLayoutParam.MATCH_PARENT.value)
        .text(
            "김민재의 부상 투혼은 이어졌다. 목이 아프고, 기침이 심한 상태에서 경기에 출전했다. 허리 통증까지 겪고 있는 것으로 알려졌다. 고 휴식이 필요한 상황인 것으로 알려졌다."
        )
        .typography(HongTypo.BODY_16_B)
        .isEnableCancelLine(true)
        .lineBreak(HongTextLineBreak.DEFAULT)
        .margin(HongSpacingInfo(10f, 10f, 10f, 10f))
        .applyOption()


    override fun optionViewList(): List<View> {
        return listOf(
            HongTextView(this).set(option = option1),
            HongTextView(this).set(option = option2)
        )
    }

    @Composable
    override fun InitCompose() {
        Column {
            HongTextCompose(option = option1)
            HongTextCompose(option = option2)
        }
    }
}
