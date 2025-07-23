package com.codehong.library.widget.toggleswitch

import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.util.HongWidgetContainer
import com.codehong.library.widget.extensions.disableRippleClickable
import com.codehong.library.widget.extensions.hongHeight
import com.codehong.library.widget.extensions.hongWidth
import com.codehong.library.widget.extensions.toColor

@Composable
fun HongSwitchCompose(
    option: HongSwitchOption
) {
    var isOn by rememberSaveable(option.initialState) { mutableStateOf(option.initialState) }

    val transition = updateTransition(targetState = isOn, label = "switch")
    val offsetX by transition.animateDp(
        label = "offset animation",
        transitionSpec = { tween(durationMillis = 200) }
    ) { state ->
        if (state) {
            (option.width - option.cursorSize - option.cursorHorizontalMargin * 2).dp
        } else {
            0.dp
        }
    }

    val injectOption = HongSwitchBuilder()
        .copy(option)
        .backgroundColor(
            if (isOn) {
                option.onColorHex
            } else {
                option.offColorHex
            }
        )
        .applyOption()

    HongWidgetContainer(injectOption) {
        Box(
            modifier = Modifier
                .hongWidth(option.width)
                .hongHeight(option.height)
                .padding(
                    horizontal = option.cursorHorizontalMargin.dp
                )
                .disableRippleClickable {
                    isOn = !isOn
                    option.switchClick?.invoke(option, isOn)
                },
            contentAlignment = Alignment.CenterStart
        ) {
            Box(
                modifier = Modifier
                    .offset(x = offsetX)
                    .size(option.cursorSize.dp)
                    .clip(CircleShape)
                    .background(
                        color = option.cursorColorHex.toColor()
                    )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHongSwitchCompose() {
    val option = HongSwitchBuilder()
        .width(55)
        .height(30)
        .margin(
            HongSpacingInfo(
                left = 20f,
                bottom = 20f,
                top = 20f,
                right = 20f
            )
        )
        .onColor(HongColor.MAIN_ORANGE_100)
        .offColor(HongColor.GRAY_20)
        .cursorSize(25)
        .cursorHorizontalMargin(3)
        .cursorColor(HongColor.WHITE_100)
        .initialState(false)
        .applyOption()
    HongSwitchCompose(option)
}
