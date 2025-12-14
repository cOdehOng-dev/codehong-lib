package com.codehong.library.widget.button.icon

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.codehong.library.widget.extensions.hongBackground
import com.codehong.library.widget.image.HongImageBuilder
import com.codehong.library.widget.image.HongImageCompose
import com.codehong.library.widget.rule.HongBorderInfo
import com.codehong.library.widget.rule.HongClickState
import com.codehong.library.widget.rule.HongClickState.Companion.isEnable
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.util.HongWidgetContainer

@Composable
fun HongButtonIconCompose(
    option: HongButtonIconOption
) {
    val iconColorState = if (option.state == HongClickState.DISABLE) {
        HongColor.GRAY_20.hex
    } else {
        option.iconColorHex
    }

    val backgroundColorState = if (option.state == HongClickState.DISABLE) {
        HongColor.GRAY_10.hex
    } else {
        option.backgroundColorHex
    }
    val borderColorState = if (option.state == HongClickState.DISABLE) {
        HongColor.GRAY_05.hex
    } else {
        option.border.color
    }

    val buttonSize = if (option.buttonType.size > 40) option.buttonType.size else 40
    val iconSize = when (option.buttonType.size) {
        56, 48 -> 24
        40, 32 -> 16
        else -> 12
    }
    val padding = when (option.buttonType.size) {
        32 -> 4
        28 -> 8
        else -> 0
    }

    val containerOption = HongButtonIconBuilder()
        .copy(option)
        .width(buttonSize)
        .height(buttonSize)
        .border(HongBorderInfo())
        .applyOption()

    HongWidgetContainer(containerOption) {
        Box(
            modifier = Modifier
                .size(buttonSize.dp)
                .padding(padding.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(buttonSize.dp)
                    .hongBackground(
                        color = backgroundColorState,
                        border = HongBorderInfo(
                            width = 1,
                            color = borderColorState
                        ),
                        useShapeCircle = option.useShapeCircle
                    )
                    .clickable {
                        if (option.state.isEnable()) {
                            option.click?.invoke(option)
                        }
                    },
                contentAlignment = Alignment.Center
            ) {
                HongImageCompose(
                    HongImageBuilder()
                        .width(iconSize)
                        .height(iconSize)
                        .imageInfo(option.iconResId)
                        .imageColor(iconColorState)
                        .applyOption()
                )
            }
        }
    }
}