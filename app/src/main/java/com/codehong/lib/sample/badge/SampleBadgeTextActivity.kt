package com.codehong.lib.sample.badge

import android.view.View
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.codehong.lib.sample.SampleComposeDespContainer
import com.codehong.lib.sample.base.BaseSampleMixActivity
import com.codehong.library.widget.badge.HongBadgeTextBuilder
import com.codehong.library.widget.badge.HongBadgeTextCompose
import com.codehong.library.widget.badge.HongBadgeTextView
import com.codehong.library.widget.rule.HongBorderInfo
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.radius.HongRadiusInfo
import com.codehong.library.widget.rule.typo.HongFont
import com.codehong.library.widget.rule.typo.HongTypo
import com.codehong.library.widget.text.HongTextBuilder

class SampleBadgeTextActivity : BaseSampleMixActivity() {

    override fun optionViewList(): List<View> {
        val option1 = HongBadgeTextBuilder()
            .width(300)
            .padding(
                HongSpacingInfo(
                    top = 4f,
                    bottom = 4f,
                    left = 8f,
                    right = 8f
                )
            )
            .margin(
                HongSpacingInfo(
                    bottom = 10f,
                    left = 20f
                )
            )
            .textOption(
                HongTextBuilder()
                    .text("오늘이 마지막 세일!!!")
                    .color("#ff322e")
                    .size(12)
                    .fontType(HongFont.PRETENDARD_700)
                    .applyOption()
            )
            .backgroundColor("#12ff322e")
            .radius(
                HongRadiusInfo(
                    all = 6
                )
            )
            .applyOption()

        val option2 = HongBadgeTextBuilder()
            .padding(
                HongSpacingInfo(
                    top = 1.5f,
                    bottom = 1.5f,
                    left = 4f,
                    right = 4f
                )
            )
            .margin(
                HongSpacingInfo(
                    bottom = 10f,
                    left = 20f
                )
            )
            .textOption(
                HongTextBuilder()
                    .text("모두 파랑파랑파랑해")
                    .color("#8e43e7")
                    .size(12)
                    .fontType(HongFont.PRETENDARD_700)
                    .applyOption()
            )
            .backgroundColor(HongColor.WHITE_100.hex)
            .border(
                HongBorderInfo(
                    color = "#dfb4fc",
                    width = 1
                )
            )
            .radius(
                HongRadiusInfo(
                    all = 6
                )
            )
            .applyOption()
        return listOf(
            HongBadgeTextView(this).set(option = option1),
            HongBadgeTextView(this).set(option = option2)
        )
    }

    @Composable
    override fun InitCompose() {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(colorResource(HongColor.WHITE_100.colorResId))
        ) {
            SampleComposeDespContainer(desp = "border 없는 BadgeText") {
                HongBadgeTextCompose(
                    option = HongBadgeTextBuilder()
                        .padding(
                            HongSpacingInfo(
                                top = 4f,
                                bottom = 4f,
                                left = 8f,
                                right = 8f
                            )
                        )
                        .margin(
                            HongSpacingInfo(
                                left = 20f
                            )
                        )
                        .textOption(
                            HongTextBuilder()
                                .text("지금이 아니면 상품 없음")
                                .color("#ff322e")
                                .typography(HongTypo.CONTENTS_12_B)
                                .applyOption()
                        )
                        .backgroundColor("#12ff322e")
                        .radius(
                            HongRadiusInfo(
                                all = 6
                            )
                        )
                        .applyOption()
                )
            }

            SampleComposeDespContainer(desp = "border 있는 BadgeText 1") {
                Box(
                    modifier = Modifier
                        .padding(start = 20.dp)
                ) {
                    HongBadgeTextCompose(
                        option = HongBadgeTextBuilder()
                            .padding(
                                HongSpacingInfo(
                                    top = 1.5f,
                                    bottom = 1.5f,
                                    left = 4f,
                                    right = 4f
                                )
                            )
                            .textOption(
                                HongTextBuilder()
                                    .text("모두 보라보라해해에에에")
                                    .color("#8e43e7")
                                    .size(12)
                                    .fontType(HongFont.PRETENDARD_700)
                                    .applyOption()
                            )
                            .backgroundColor(HongColor.WHITE_100.hex)

                            .border(
                                HongBorderInfo(
                                    color = "#dfb4fc",
                                    width = 1
                                )
                            )
                            .radius(
                                HongRadiusInfo(
                                    all = 4
                                )
                            )
                            .applyOption()
                    )
                }
            }

            SampleComposeDespContainer(desp = "width 300") {
                HongBadgeTextCompose(
                    option = HongBadgeTextBuilder()
                        .width(300)
                        .padding(
                            HongSpacingInfo(
                                top = 4f,
                                bottom = 4f,
                                left = 8f,
                                right = 8f
                            )
                        )
                        .margin(
                            HongSpacingInfo(
                                left = 20f
                            )
                        )
                        .textOption(
                            HongTextBuilder()
                                .text("지금이 아니면 상품 없음 3000")
                                .color("#ff322e")
                                .size(12)
                                .fontType(HongFont.PRETENDARD_700)
                                .applyOption()
                        )
                        .backgroundColor("#12ff322e")
                        .radius(
                            HongRadiusInfo(
                                all = 6
                            )
                        )
                        .applyOption()
                )
            }
        }
    }
}
