package com.codehong.library.widget.button.icon

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.codehong.library.widget.extensions.hongBackground
import com.codehong.library.widget.image.HongImageBuilder
import com.codehong.library.widget.image.HongImageCompose
import com.codehong.library.widget.rule.HongBorderInfo
import com.codehong.library.widget.rule.HongClickState
import com.codehong.library.widget.rule.HongClickState.Companion.isEnable
import com.codehong.library.widget.rule.button.HongButtonIconType
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.util.HongWidgetContainer

@Composable
fun HongButtonIconCompose(
    option: HongButtonIconOption
) {
    val isDisabled = option.state == HongClickState.DISABLE

    val iconColor = if (isDisabled) HongColor.GRAY_20.hex else option.iconColorHex
    val backgroundColor = if (isDisabled) HongColor.GRAY_10.hex else option.backgroundColorHex
    val borderColor = if (isDisabled) HongColor.GRAY_05.hex else option.border.color

    val buttonSize = option.buttonType.size.coerceAtLeast(40)
    val iconSize = option.buttonType.getIconSize()
    val contentPadding = option.buttonType.getContentPadding()

    HongWidgetContainer(
        HongButtonIconBuilder()
            .copy(option)
            .width(buttonSize)
            .height(buttonSize)
            .border(HongBorderInfo())
            .applyOption()
    ) {
        Box(
            modifier = Modifier
                .padding(contentPadding.dp)
                .size(buttonSize.dp)
                .hongBackground(
                    color = backgroundColor,
                    border = HongBorderInfo(
                        width = 1,
                        color = borderColor
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
                    .imageColor(iconColor)
                    .applyOption()
            )
        }
    }
}

private fun HongButtonIconType.getIconSize(): Int {
    return when (size) {
        56, 48 -> 24
        40, 32 -> 16
        else -> 12
    }
}

private fun HongButtonIconType.getContentPadding(): Int {
    return when (size) {
        32 -> 4
        28 -> 8
        else -> 0
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHongButtonIconCompose() {
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            HongButtonIconCompose(
                HongButtonIconBuilder()
                    .buttonType(HongButtonIconType.SIZE_56)
                    .useShapeCircle(true)
                    .applyOption()
            )
            HongButtonIconCompose(
                HongButtonIconBuilder()
                    .buttonType(HongButtonIconType.SIZE_48)
                    .useShapeCircle(true)
                    .applyOption()
            )
            HongButtonIconCompose(
                HongButtonIconBuilder()
                    .buttonType(HongButtonIconType.SIZE_40)
                    .useShapeCircle(true)
                    .applyOption()
            )
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            HongButtonIconCompose(
                HongButtonIconBuilder()
                    .buttonType(HongButtonIconType.SIZE_32)
                    .useShapeCircle(true)
                    .applyOption()
            )
            HongButtonIconCompose(
                HongButtonIconBuilder()
                    .buttonType(HongButtonIconType.SIZE_28)
                    .useShapeCircle(true)
                    .applyOption()
            )
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            HongButtonIconCompose(
                HongButtonIconBuilder()
                    .buttonType(HongButtonIconType.SIZE_48)
                    .useShapeCircle(true)
                    .iconColor(HongColor.MAIN_ORANGE_100)
                    .backgroundColor(HongColor.MAIN_ORANGE_10.hex)
                    .border(HongBorderInfo(width = 1, color = HongColor.MAIN_ORANGE_40.hex))
                    .applyOption()
            )
            HongButtonIconCompose(
                HongButtonIconBuilder()
                    .buttonType(HongButtonIconType.SIZE_48)
                    .useShapeCircle(true)
                    .state(HongClickState.DISABLE)
                    .applyOption()
            )
        }
    }
}