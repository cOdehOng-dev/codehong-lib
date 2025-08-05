package com.codehong.lib.sample.button.text

import android.view.View
import androidx.compose.runtime.Composable
import com.codehong.lib.sample.base.BaseSampleMixActivity
import com.codehong.library.widget.button.text.HongButtonTextBuilder
import com.codehong.library.widget.button.text.HongButtonTextCompose
import com.codehong.library.widget.button.text.HongButtonTextView
import com.codehong.library.widget.rule.HongBorderInfo
import com.codehong.library.widget.rule.HongLayoutParam
import com.codehong.library.widget.rule.HongShadowInfo
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.HongState
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.radius.HongRadiusInfo
import com.codehong.library.widget.rule.typo.HongTypo

class SampleTextButtonActivity : BaseSampleMixActivity() {

    private val option1 = HongButtonTextBuilder()
        .width(HongLayoutParam.MATCH_PARENT.value)
        .height(48)
        .margin(
            HongSpacingInfo(
                left = 20f,
                right = 20f,
                bottom = 10f
            )
        )
        .text("검색하기")
        .textTypo(HongTypo.BODY_15_B)
        .textColor(HongColor.WHITE_100)
        .backgroundColor(HongColor.MAIN_ORANGE_100.hex)
        .radius(
            HongRadiusInfo(
                all = 12
            )
        )
        .applyOption()

    private val option2 = HongButtonTextBuilder()
        .width(HongLayoutParam.MATCH_PARENT.value)
        .height(48)
        .margin(
            HongSpacingInfo(
                left = 20f,
                right = 20f,
                bottom = 10f
            )
        )
        .text("이동하기")
        .textTypo(HongTypo.BODY_15_B)
        .textColor(HongColor.WHITE_100.hex)
        .backgroundColor(HongColor.MAIN_ORANGE_100.hex)
        .radius(
            HongRadiusInfo(
                all = 12
            )
        )
        .shadow(
            HongShadowInfo(
                color = HongColor.BLACK_25.hex,
                blur = 24f,
                offsetY = 0f,
                offsetX = 2f,
                spread = 0f,
            )
        )
        .applyOption()

    private val option3 = HongButtonTextBuilder()
        .width(HongLayoutParam.MATCH_PARENT.value)
        .height(48)
        .margin(
            HongSpacingInfo(
                left = 20f,
                right = 20f,
                bottom = 10f
            )
        )
        .radius(
            HongRadiusInfo(
                topLeft = 10,
                topRight = 10,
                bottomLeft = 10,
                bottomRight = 10
            )
        )
        .border(
            HongBorderInfo(
                width = 1,
                color = HongColor.MAIN_ORANGE_100.hex
            )
        )
        .text("취소")
        .textTypo(HongTypo.BODY_15_B)
        .textColor(HongColor.MAIN_ORANGE_100.hex)
        .backgroundColor(HongColor.WHITE_100.hex)
        .applyOption()

    private val option4 = HongButtonTextBuilder()
        .width(HongLayoutParam.MATCH_PARENT.value)
        .height(48)
        .state(HongState.DISABLED)
        .margin(
            HongSpacingInfo(
                left = 20f,
                right = 20f,
                bottom = 10f
            )
        )
        .text("이동하기")
        .textTypo(HongTypo.BODY_15_B)
        .textColor(HongColor.WHITE_100.hex)
        .backgroundColor(HongColor.MAIN_ORANGE_100.hex)
        .radius(
            HongRadiusInfo(
                all = 12
            )
        )
        .shadow(
            HongShadowInfo(
                color = HongColor.BLACK_25.hex,
                blur = 24f,
                offsetY = 0f,
                offsetX = 2f,
                spread = 0f,
            )
        )
        .applyOption()

    private val optionList
        get() = listOf(
            option1,
            option2,
            option3,
            option4
        )

    override fun optionViewList(): List<View> {
        return mutableListOf<View>().apply {
            optionList.forEach {
                add(HongButtonTextView(this@SampleTextButtonActivity).set(it))
            }
        }
    }

    @Composable
    override fun InitCompose() {
        optionList.forEach {
            HongButtonTextCompose(it)
        }
    }
}
