package com.codehong.library.widget.text.badge

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.codehong.library.widget.rule.HongBorderInfo
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.radius.HongRadiusInfo
import com.codehong.library.widget.rule.typo.HongTypo
import com.codehong.library.widget.text.HongTextBuilder
import com.codehong.library.widget.text.HongTextCompose
import com.codehong.library.widget.util.HongWidgetContainer

@Composable
fun HongTextBadgeCompose(
    option: HongTextBadgeOption
) {
    if (option.textOption.text.isNullOrEmpty()) return

    HongWidgetContainer(option) {
        HongTextCompose(
            HongTextBuilder()
                .copy(option.textOption)
                .maxLines(1)
                .applyOption()
        )
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewHongTextBadgeCompose() {
    val option = HongTextBadgeBuilder()
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
                .typography(HongTypo.CONTENTS_12_B)
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
    HongTextBadgeCompose(option)
}