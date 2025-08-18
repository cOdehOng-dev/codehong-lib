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
import com.codehong.library.widget.rule.typo.HongTypo
import com.codehong.library.widget.text.HongTextBuilder
import com.codehong.library.widget.text.HongTextCompose
import com.codehong.library.widget.util.HongWidgetContainer
import com.codehong.library.widget.util.HongWidgetNoneClickContainer

@Composable
fun HongButtonTextCompose(
    option: HongButtonTextOption
) {
    if (option.state == HongState.DISABLED) {
        HongWidgetNoneClickContainer(
            HongButtonTextBuilder()
                .copy(option)
                .backgroundColor(HongButtonTextOption.DEFAULT_DISABLE_BACKGROUND_COLOR)
                .border(HongBorderInfo())
                .applyOption()
        ) {
            HongTextCompose(
                option = HongTextBuilder()
                    .width(HongLayoutParam.MATCH_PARENT.value)
                    .text(option.text)
                    .typography(HongTypo.BODY_15_B)
                    .color(HongColor.WHITE_60.hex)
                    .textAlign(HongTextAlign.CENTER)
                    .applyOption()
            )
        }
    } else {
        HongWidgetContainer(option) {
            HongTextCompose(
                option = HongTextBuilder()
                    .width(HongLayoutParam.MATCH_PARENT.value)
                    .text(option.text)
                    .typography(option.textTypo)
                    .color(option.textColorHex)
                    .textAlign(HongTextAlign.CENTER)
                    .applyOption()
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHongTextButtonCompose() {
    val option = HongButtonTextBuilder()
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

    val option2 = HongButtonTextBuilder()
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
        .text("검색하기")
        .textTypo(HongTypo.BODY_15_B)
        .textColor(HongColor.WHITE_100)
        .backgroundColor(HongColor.MAIN_ORANGE_100.hex)
        .radius(
            HongRadiusInfo(
                all = 12
            )
        )
        .state(HongState.DISABLED)
        .applyOption()

    Column {
        HongButtonTextCompose(option)
        HongButtonTextCompose(option2)
    }
}