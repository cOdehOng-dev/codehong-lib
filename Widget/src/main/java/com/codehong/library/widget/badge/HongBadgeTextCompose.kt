package com.codehong.library.widget.badge

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.radius.HongRadiusInfo
import com.codehong.library.widget.rule.typo.HongTypo
import com.codehong.library.widget.text.HongTextCompose
import com.codehong.library.widget.text.HongTextBuilder
import com.codehong.library.widget.util.HongWidgetContainer

@Composable
fun HongBadgeTextCompose(
    option: HongBadgeTextOption
) {
    if (option.textOption.text.isNullOrEmpty()) return

    HongWidgetContainer(option) {
        HongTextCompose(
            option = option.textOption
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHongBadgeTextCompose() {
    HongBadgeTextCompose(
        HongBadgeTextBuilder()
            .padding(
                HongSpacingInfo(
                    top = 4f,
                    bottom = 4f,
                    left = 8f,
                    right = 8f
                )
            )
            .textOption(
                HongTextBuilder()
                    .text("지금이 아니면 상품 없음")
                    .color(HongColor.MAIN_ORANGE_100)
                    .typography(HongTypo.CONTENTS_12_B)
                    .applyOption()
            )
            .backgroundColor(HongColor.MAIN_ORANGE_20)
            .radius(
                HongRadiusInfo(
                    all = 6
                )
            )
            .applyOption()
    )
}