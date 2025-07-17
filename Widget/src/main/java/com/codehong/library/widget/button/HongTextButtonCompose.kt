package com.codehong.library.widget.button

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.codehong.library.widget.rule.HongLayoutParam
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.HongTextAlign
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.radius.HongRadiusInfo
import com.codehong.library.widget.rule.typo.HongFont
import com.codehong.library.widget.text.HongTextCompose
import com.codehong.library.widget.text.HongTextBuilder
import com.codehong.library.widget.util.HongWidgetContainer

@Composable
fun HongTextButtonCompose(
    option: HongTextButtonOption
) {
    HongWidgetContainer(
        option
    ) {
        HongTextCompose(
            option = HongTextBuilder()
                .copy(option.textOption)
                .width(HongLayoutParam.MATCH_PARENT.value)
                .textAlign(HongTextAlign.CENTER)
                .applyOption()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHongTextButtonCompose() {
    val option = HongTextButtonBuilder()
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
                top = 10f,
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

    HongTextButtonCompose(option)
}