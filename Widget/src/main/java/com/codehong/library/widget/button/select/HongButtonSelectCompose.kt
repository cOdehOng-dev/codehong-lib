package com.codehong.library.widget.button.select

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.codehong.library.widget.rule.radius.HongRadiusInfo
import com.codehong.library.widget.util.HongWidgetContainer

@Composable
fun HongButtonSelectCompose(
    option: HongButtonSelectOption
) {
    val remOption by remember { mutableStateOf(option) }

    HongWidgetContainer(option) {
        Row(
            modifier = Modifier
                .hongWidth(remOption.width)
                .hongHeight(remOption.height)
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
            ) {
                HongButtonTextCompose(
                    HongButtonTextBuilder()
                        .width(HongLayoutParam.MATCH_PARENT.value)
                        .height(48)
                        .padding(
                            HongSpacingInfo(
                                top = 8f,
                                bottom = 8f
                            )
                        )
                        .radius(
                            HongRadiusInfo(
                                topLeft = 10,
                                topRight = 10,
                                bottomLeft = 10,
                                bottomRight = 10
                            )
                        )
                        .border(
                            HongBorderInfo(
                                width = 1,
                                color = remOption.negativeBorderColorHex
                            )
                        )
                        .text(remOption.negativeText)
                        .textTypo(remOption.negativeTextTypo)
                        .textColor(remOption.negativeTextColorHex)
                        .onClick {
                            remOption.onClickNegative?.invoke()
                        }
                        .applyOption()
                )
            }
            Spacer(
                modifier = Modifier
                    .width(10.dp)
            )
            Box(
                modifier = Modifier
                    .weight(1f)
            ) {
                HongButtonTextCompose(
                    HongButtonTextBuilder()
                        .width(HongLayoutParam.MATCH_PARENT.value)
                        .height(48)
                        .padding(
                            HongSpacingInfo(
                                top = 8f,
                                bottom = 8f
                            )
                        )
                        .radius(
                            HongRadiusInfo(
                                topLeft = 10,
                                topRight = 10,
                                bottomLeft = 10,
                                bottomRight = 10
                            )
                        )
                        .text(remOption.positiveText)
                        .textTypo(remOption.positiveTextTypo)
                        .textColor(remOption.positiveTextColorHex)
                        .backgroundColor(remOption.positiveBackgroundColorHex)
                        .onClick {
                            remOption.onClickPositive?.invoke()
                        }
                        .applyOption()
                )
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHongSelectButtonCompose() {
    HongButtonSelectCompose(
        HongButtonSelectBuilder()
            .margin(
                HongSpacingInfo(
                    top = 20f,
                    left = 20f,
                    right = 20f,
                    bottom = 20f
                )
            )
            .applyOption()
    )
}