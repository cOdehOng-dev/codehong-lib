package com.codehong.lib.sample.badge

import android.view.View
import androidx.compose.runtime.Composable
import com.codehong.lib.sample.base.BaseSampleMixActivity
import com.codehong.library.widget.rule.HongBorderInfo
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.radius.HongRadiusInfo
import com.codehong.library.widget.rule.typo.HongFont
import com.codehong.library.widget.rule.typo.HongTypo
import com.codehong.library.widget.text.HongTextBuilder
import com.codehong.library.widget.text.badge.HongTextBadgeBuilder
import com.codehong.library.widget.text.badge.HongTextBadgeCompose
import com.codehong.library.widget.text.badge.HongTextBadgeView

class SampleTextBadgeActivity : BaseSampleMixActivity() {

    private val option1 = HongTextBadgeBuilder()
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

    private val option2 = HongTextBadgeBuilder()
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

    private val option3 = HongTextBadgeBuilder()
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

    private val option4 = HongTextBadgeBuilder()
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

    private val option5 = HongTextBadgeBuilder()
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
                add(HongTextBadgeView(this@SampleTextBadgeActivity).set(it))
            }
        }
    }

    @Composable
    override fun InitCompose() {
        optionList.forEach {
            HongTextBadgeCompose(it)
        }
    }
}
