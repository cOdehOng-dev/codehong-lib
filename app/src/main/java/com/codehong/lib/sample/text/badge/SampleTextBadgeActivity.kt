package com.codehong.lib.sample.text.badge

import android.view.View
import androidx.compose.runtime.Composable
import com.codehong.lib.sample.base.BaseSampleMixActivity
import com.codehong.library.widget.rule.HongBorderInfo
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.radius.HongRadiusInfo
import com.codehong.library.widget.rule.typo.HongTypo
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
        .text("오늘이 마지막 세일!!!")
        .textColor(HongColor.PURPLE_100.hex)
        .textTypo(HongTypo.CONTENTS_12_B)
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
        .text("모두 파랑파랑파랑해")
        .textColor(HongColor.PURPLE_100.hex)
        .textTypo(HongTypo.CONTENTS_12_B)
        .backgroundColor(HongColor.WHITE_100.hex)
        .border(
            HongBorderInfo(
                color = HongColor.PURPLE_50.hex,
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
        .text("지금이 아니면 상품 없음")
        .textColor(HongColor.PURPLE_100.hex)
        .textTypo(HongTypo.CONTENTS_12_B)
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
        .text("모두 보라보라해해에에에")
        .textColor(HongColor.PURPLE_100.hex)
        .textTypo(HongTypo.CONTENTS_12_B)
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
        .text("지금이 아니면 상품 없음 3000")
        .textColor(HongColor.PURPLE_100.hex)
        .textTypo(HongTypo.CONTENTS_12_B)
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
