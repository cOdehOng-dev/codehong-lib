package com.codehong.library.widget.label.toggleswitch

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.codehong.library.widget.extensions.hongHeight
import com.codehong.library.widget.extensions.hongWidth
import com.codehong.library.widget.label.HongLabelViewCompose
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.toggleswitch.HongSwitchBuilder
import com.codehong.library.widget.toggleswitch.HongSwitchCompose
import com.codehong.library.widget.util.HongWidgetContainer

@Composable
fun HongLabelSwitchCompose(
    option: HongLabelSwitchOption
) {
    HongWidgetContainer(option) {
        Row(
            modifier = Modifier
                .hongWidth(option.width)
                .hongHeight(option.height),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
            ) {
                HongLabelViewCompose(option.labelOption)
            }
            Spacer(
                modifier = Modifier
                    .size(5.dp)
            )
            HongSwitchCompose(option.switchOption)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHongLabelSwitchCompose() {
    val option = HongLabelSwitchBuilder()
        .padding(
            HongSpacingInfo(
                top = 20f,
                bottom = 20f,
                left = 16f,
                right = 16f
            )
        )
        .label("테스트")
        .description("테스트 입니다요")
        .switchOption(
            HongSwitchBuilder()
                .width(55)
                .height(30)
                .onColor(HongColor.MAIN_ORANGE_100)
                .offColor(HongColor.GRAY_20)
                .cursorSize(25)
                .cursorHorizontalMargin(3)
                .cursorColor(HongColor.WHITE_100)
                .initialState(true)
                .switchClick { _, isEnable -> }
                .applyOption()
        )
        .applyOption()

    HongLabelSwitchCompose(option)
}