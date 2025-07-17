package com.codehong.lib.sample.button.text

import android.view.View
import androidx.compose.runtime.Composable
import com.codehong.lib.sample.base.BaseSampleMixActivity
import com.codehong.library.widget.button.text.HongTextButtonBuilder
import com.codehong.library.widget.button.text.HongTextButtonCompose
import com.codehong.library.widget.button.text.HongTextButtonView
import com.codehong.library.widget.rule.HongBorderInfo
import com.codehong.library.widget.rule.HongLayoutParam
import com.codehong.library.widget.rule.HongShadowInfo
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.radius.HongRadiusInfo
import com.codehong.library.widget.rule.typo.HongTypo
import com.codehong.library.widget.text.HongTextBuilder

class SampleTextButtonActivity : BaseSampleMixActivity() {

    private val option1 = HongTextButtonBuilder()
        .width(HongLayoutParam.MATCH_PARENT.value)
        .height(48)
        .padding(
            HongSpacingInfo(
                top = 15f,
                bottom = 15f
            )
        )
        .margin(
            HongSpacingInfo(
                left = 20f,
                right = 20f,
                bottom = 10f
            )
        )
        .textOption(
            HongTextBuilder()
                .text("검색하기")
                .typography(HongTypo.BODY_15_B)
                .color(HongColor.WHITE_100)
                .applyOption()
        )
        .backgroundColor(HongColor.MAIN_ORANGE_100.hex)
        .radius(
            HongRadiusInfo(
                all = 12
            )
        )
        .applyOption()

    private val option2 = HongTextButtonBuilder()
        .width(HongLayoutParam.MATCH_PARENT.value)
        .height(48)
        .padding(
            HongSpacingInfo(
                top = 15f,
                bottom = 15f
            )
        )
        .margin(
            HongSpacingInfo(
                left = 20f,
                right = 20f,
                bottom = 10f
            )
        )
        .textOption(
            HongTextBuilder()
                .text("이동하기")
                .typography(HongTypo.BODY_15_B)
                .color(HongColor.WHITE_100.hex)
                .applyOption()
        )
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

    private val option3 = HongTextButtonBuilder()
        .width(HongLayoutParam.MATCH_PARENT.value)
        .height(48)
        .padding(
            HongSpacingInfo(
                top = 8f,
                bottom = 8f
            )
        )
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
        .textOption(
            HongTextBuilder()
                .text("취소")
                .typography(HongTypo.BODY_15_B)
                .color(HongColor.MAIN_ORANGE_100.hex)
                .applyOption()
        )
        .backgroundColor(HongColor.WHITE_100.hex)
        .applyOption()

    private val optionList get() = listOf(
        option1,
        option2,
        option3
    )

    override fun optionViewList(): List<View> {
        return mutableListOf<View>().apply {
            optionList.forEach {
                add(HongTextButtonView(this@SampleTextButtonActivity).set(it))
            }
        }
    }

    @Composable
    override fun InitCompose() {
        optionList.forEach {
            HongTextButtonCompose(it)
        }
    }
}
