package com.codehong.library.widget.button.select

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.codehong.library.widget.button.text.HongButtonTextBuilder
import com.codehong.library.widget.button.text.HongButtonTextCompose
import com.codehong.library.widget.extensions.hongHeight
import com.codehong.library.widget.extensions.hongWidth
import com.codehong.library.widget.rule.HongBorderInfo
import com.codehong.library.widget.rule.HongLayoutParam
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.radius.HongRadiusInfo
import com.codehong.library.widget.rule.typo.HongTypo
import com.codehong.library.widget.util.HongWidgetContainer

private const val BUTTON_HEIGHT = 48
private const val BUTTON_RADIUS = 10
private const val BUTTON_SPACING = 10
private val BUTTON_PADDING = HongSpacingInfo(top = 8f, bottom = 8f)
private val BUTTON_RADIUS_INFO = HongRadiusInfo(all = BUTTON_RADIUS)

@Composable
fun HongButtonSelectCompose(
    option: HongButtonSelectOption
) {
    HongWidgetContainer(option) {
        Row(
            modifier = Modifier
                .hongWidth(option.width)
                .hongHeight(option.height)
        ) {
            Box(modifier = Modifier.weight(1f)) {
                NegativeButton(option)
            }
            Spacer(modifier = Modifier.width(BUTTON_SPACING.dp))
            Box(modifier = Modifier.weight(1f)) {
                PositiveButton(option)
            }
        }
    }
}

@Composable
private fun NegativeButton(option: HongButtonSelectOption) {
    HongButtonTextCompose(
        HongButtonTextBuilder()
            .width(HongLayoutParam.MATCH_PARENT.value)
            .height(BUTTON_HEIGHT)
            .padding(BUTTON_PADDING)
            .radius(BUTTON_RADIUS_INFO)
            .border(HongBorderInfo(width = 1, color = option.negativeBorderColorHex))
            .text(option.negativeText)
            .textTypo(option.negativeTextTypo)
            .textColor(option.negativeTextColorHex)
            .onClick { option.onClickNegative?.invoke() }
            .applyOption()
    )
}

@Composable
private fun PositiveButton(option: HongButtonSelectOption) {
    HongButtonTextCompose(
        HongButtonTextBuilder()
            .width(HongLayoutParam.MATCH_PARENT.value)
            .height(BUTTON_HEIGHT)
            .padding(BUTTON_PADDING)
            .radius(BUTTON_RADIUS_INFO)
            .text(option.positiveText)
            .textTypo(option.positiveTextTypo)
            .textColor(option.positiveTextColorHex)
            .backgroundColor(option.positiveBackgroundColorHex)
            .onClick { option.onClickPositive?.invoke() }
            .applyOption()
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewHongButtonSelectCompose() {
    val defaultMargin = HongSpacingInfo(
        top = 16f,
        left = 20f,
        right = 20f,
        bottom = 16f
    )

    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        HongButtonSelectCompose(
            HongButtonSelectBuilder()
                .margin(defaultMargin)
                .applyOption()
        )

        HongButtonSelectCompose(
            HongButtonSelectBuilder()
                .margin(defaultMargin)
                .negativeText("아니오")
                .positiveText("예")
                .applyOption()
        )

        HongButtonSelectCompose(
            HongButtonSelectBuilder()
                .margin(defaultMargin)
                .negativeText("닫기")
                .negativeTextColor(HongColor.GRAY_60.hex)
                .negativeBorderColor(HongColor.GRAY_40.hex)
                .positiveText("저장하기")
                .positiveTextTypo(HongTypo.BODY_15_B)
                .positiveBackgroundColor(HongColor.BLUE_100.hex)
                .applyOption()
        )
    }
}