package com.codehong.library.widget.button.icon

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
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
    val remOption by remember { mutableStateOf(option) }

    val iconColorState by rememberSaveable {
        mutableStateOf(
            if (remOption.state == HongClickState.DISABLE) {
                HongColor.GRAY_20.hex
            } else {
                remOption.iconColorHex
            }
        )
    }

    val backgroundColorState by rememberSaveable {
        mutableStateOf(
            if (remOption.state == HongClickState.DISABLE) {
                HongColor.GRAY_10.hex
            } else {
                remOption.backgroundColorHex
            }
        )
    }
    val borderColorState by rememberSaveable {
        mutableStateOf(
            if (remOption.state == HongClickState.DISABLE) {
                HongColor.GRAY_05.hex
            } else {
                remOption.border.color
            }
        )
    }

    val buttonSize by rememberSaveable {
        mutableIntStateOf(if (remOption.buttonType.size > 40) remOption.buttonType.size else 40)
    }
    val iconSize by rememberSaveable {
        mutableIntStateOf(
            when (remOption.buttonType.size) {
                56, 48 -> 24
                40, 32 -> 16
                else -> 12
            }
        )
    }
    val padding by rememberSaveable {
        mutableIntStateOf(
            when (remOption.buttonType.size) {
                32 -> 4
                28 -> 8
                else -> 0
            }
        )
    }

    HongWidgetContainer(
        HongButtonIconBuilder()
            .copy(remOption)
            .width(buttonSize)
            .height(buttonSize)
            .border(HongBorderInfo())
            .applyOption()
    ) {
        Box(
            modifier = Modifier
                .padding(padding.dp)
                .size(buttonSize.dp)
                .hongBackground(
                    color = backgroundColorState,
                    border = HongBorderInfo(
                        width = 1,
                        color = borderColorState
                    ),
                    useShapeCircle = remOption.useShapeCircle
                )
                .clickable {
                    if (remOption.state.isEnable()) {
                        remOption.click?.invoke(option)
                    }
                },
            contentAlignment = Alignment.Center
        ) {
            HongImageCompose(
                HongImageBuilder()
                    .width(iconSize)
                    .height(iconSize)
                    .imageInfo(remOption.iconResId)
                    .imageColor(iconColorState)
                    .applyOption()
            )
        }
    }
}