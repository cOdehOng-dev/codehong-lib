package com.codehong.library.widget.text.unit

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.codehong.library.widget.extensions.hongHeight
import com.codehong.library.widget.extensions.hongWidth
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.text.label.HongTextCompose
import com.codehong.library.widget.util.HongWidgetContainer

@Composable
fun HongTextUnitCompose(
    option: HongTextUnitOption
) {
    HongWidgetContainer(option) {
        Row(
            modifier = Modifier
                .hongWidth(option.width)
                .hongHeight(option.height),
            verticalAlignment = Alignment.CenterVertically
        ) {
            HongTextCompose(option.textOption)

            if (option.useUnit) {
                HongTextCompose(option.unitTextOption)
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewHongTextUnitCompose() {
    val option = HongTextUnitBuilder()
        .margin(
            HongSpacingInfo(
                left = 20f,
                right = 20f,
                bottom = 10f,
                top = 10f
            )
        )
        .text("1000")
        .unitText("장")
        .useUnit(true)
        .useNumberDecimal(true)
        .applyOption()
    HongTextUnitCompose(option)
}