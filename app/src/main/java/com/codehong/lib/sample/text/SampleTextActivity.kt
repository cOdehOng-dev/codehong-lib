package com.codehong.lib.sample.text

import android.view.View
import androidx.compose.runtime.Composable
import com.codehong.lib.sample.base.BaseSampleMixActivity
import com.codehong.library.widget.rule.HongLayoutParam
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.HongTextLineBreak
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.typo.HongTypo
import com.codehong.library.widget.text.def.HongTextBuilder
import com.codehong.library.widget.text.def.HongTextCompose
import com.codehong.library.widget.text.def.HongTextView

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


    private val option3 = HongTextBuilder()
        .text("14342")
        .typography(HongTypo.BODY_16_B)
        .lineBreak(HongTextLineBreak.DEFAULT)
        .color(HongColor.MAIN_ORANGE_100.hex)
        .margin(HongSpacingInfo(10f, 10f, 10f, 10f))
        .useNumberDecimal(true)
        .applyOption()

    private val option4 = HongTextBuilder()
        .text("14342")
        .typography(HongTypo.BODY_16_B)
        .lineBreak(HongTextLineBreak.DEFAULT)
        .margin(HongSpacingInfo(10f, 10f, 10f, 10f))
        .useNumberDecimal(false)
        .applyOption()


    private val option5 = HongTextBuilder()
        .text("숫자가 아닌 텍스트 입니다")
        .typography(HongTypo.BODY_16_B)
        .lineBreak(HongTextLineBreak.DEFAULT)
        .margin(HongSpacingInfo(10f, 10f, 10f, 10f))
        .useNumberDecimal(true)
        .applyOption()


    private val optionList get() = listOf(
        option1,
        option2,
        option3,
        option4,
        option5
    )

    override fun optionViewList(): List<View> {
        return mutableListOf<View>().apply {
            optionList.forEach {
                add(HongTextView(this@SampleTextActivity).set(option = it))
            }
        }
    }

    @Composable
    override fun InitCompose() {
        optionList.forEach {
            HongTextCompose(option = it)
        }
    }
}
