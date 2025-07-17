package com.codehong.lib.sample.button

import android.view.View
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import com.codehong.lib.sample.base.BaseSampleMixActivity
import com.codehong.library.widget.button.HongTextButtonCompose
import com.codehong.library.widget.button.HongTextButtonBuilder
import com.codehong.library.widget.button.HongTextButtonView
import com.codehong.library.widget.rule.HongLayoutParam
import com.codehong.library.widget.rule.HongShadowInfo
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.radius.HongRadiusInfo
import com.codehong.library.widget.rule.typo.HongFont
import com.codehong.library.widget.text.HongTextBuilder
import com.codehong.library.widget.util.HongToastUtil

class SampleTextButtonActivity : BaseSampleMixActivity() {

    override fun optionViewList(): List<View> {
        val option1 = HongTextButtonBuilder()
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
                    .size(15)
                    .fontType(HongFont.PRETENDARD_700)
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

        val option2 = HongTextButtonBuilder()
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
                    right = 20f
                )
            )
            .textOption(
                HongTextBuilder()
                    .text("이동하기")
                    .size(15)
                    .fontType(HongFont.PRETENDARD_700)
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

        return listOf(
            HongTextButtonView(this).set(option1),
            HongTextButtonView(this).set(option2)
        )
    }

    @Composable
    override fun InitCompose() {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(colorResource(HongColor.WHITE_100.colorResId))
        ) {
            HongTextButtonCompose(
                option = HongTextButtonBuilder()
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
                            .size(15)
                            .fontType(HongFont.PRETENDARD_700)
                            .color(HongColor.WHITE_100)
                            .applyOption()
                    )
                    .backgroundColor(HongColor.MAIN_ORANGE_100.hex)
                    .radius(
                        HongRadiusInfo(
                            all = 12
                        )
                    )
                    .onClick {
                        HongToastUtil.showToast(this@SampleTextButtonActivity, "버튼 클릭")
                    }
                    .applyOption()
            )
            HongTextButtonCompose(
                option = HongTextButtonBuilder()
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
                            right = 20f
                        )
                    )
                    .textOption(
                        HongTextBuilder()
                            .text("이동하기")
                            .size(15)
                            .fontType(HongFont.PRETENDARD_700)
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
                    .onClick {
                        HongToastUtil.showToast(this@SampleTextButtonActivity, "버튼 클릭")
                    }
                    .applyOption()
            )
        }
    }
}
