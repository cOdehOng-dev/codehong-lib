package com.codehong.library.widget.button.select

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.codehong.library.widget.button.text.HongTextButtonBuilder
import com.codehong.library.widget.button.text.HongTextButtonCompose
import com.codehong.library.widget.extensions.hongHeight
import com.codehong.library.widget.extensions.hongWidth
import com.codehong.library.widget.util.HongWidgetContainer

@Composable
fun HongSelectButtonCompose(
    option: HongSelectButtonOption
) {
    HongWidgetContainer(option) {
        Row(
            modifier = Modifier
                .hongWidth(option.width)
                .hongHeight(option.height)
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
            ) {
                HongTextButtonCompose(
                    HongTextButtonBuilder()
                        .copy(option.negativeTextButtonOption)
                        .onClick {
                            option.negativeClick?.invoke()
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
                HongTextButtonCompose(
                    HongTextButtonBuilder()
                        .copy(option.positiveTextButtonOption)
                        .onClick {
                            option.positiveClick?.invoke()
                        }
                        .applyOption()
                )
            }

        }
    }
}