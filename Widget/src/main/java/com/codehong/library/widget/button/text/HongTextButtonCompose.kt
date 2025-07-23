package com.codehong.library.widget.button.text

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.codehong.library.widget.rule.HongBorderInfo
import com.codehong.library.widget.rule.HongLayoutParam
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.HongState
import com.codehong.library.widget.rule.HongTextAlign
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.radius.HongRadiusInfo
import com.codehong.library.widget.rule.typo.HongFont
import com.codehong.library.widget.text.HongTextBuilder
import com.codehong.library.widget.text.HongTextCompose
import com.codehong.library.widget.util.HongWidgetContainer
import com.codehong.library.widget.util.HongWidgetNoneClickContainer

@Composable
fun HongTextButtonCompose(
    option: HongTextButtonOption
) {
    if (option.state == HongState.DISABLED) {
        HongWidgetNoneClickContainer(
            HongTextButtonBuilder()
                .copy(option)
                .backgroundColor(HongTextButtonOption.DEFAULT_DISABLE_BACKGROUND_COLOR)
                .border(HongBorderInfo())
                .applyOption()
        ) {
            HongTextCompose(
                option = HongTextBuilder()
                    .copy(HongTextButtonOption.DEFAULT_DISABLE_TEXT_OPTION)
                    .text(option.textOption.text)
                    .width(HongLayoutParam.MATCH_PARENT.value)
                    .textAlign(HongTextAlign.CENTER)
                    .applyOption()
            )
        }
    } else {
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
}

@Preview(showBackground = true)
@Composable
fun PreviewHongTextButtonCompose() {
    val option = HongTextButtonBuilder()
        .width(HongLayoutParam.MATCH_PARENT.value)
        .height(48)
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

    val option2 = HongTextButtonBuilder()
        .width(HongLayoutParam.MATCH_PARENT.value)
        .state(HongState.DISABLED)
        .height(48)
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

    Column {
        HongTextButtonCompose(option)
        HongTextButtonCompose(option2)
    }
}