package com.codehong.library.widget.checkbox

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.codehong.library.widget.extensions.toColor
import com.codehong.library.widget.rule.HongBorderInfo
import com.codehong.library.widget.rule.HongState
import com.codehong.library.widget.rule.HongState.Companion.isEnabled
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.color.HongColor.Companion.toColor
import com.codehong.library.widget.rule.radius.HongRadiusInfo
import com.codehong.library.widget.util.HongWidgetContainer


@Composable
fun HongCheckBoxCompose(
    option: HongCheckboxOption
) {
    var isChecked by rememberSaveable(option.checkState) { mutableStateOf(option.checkState) }
    val isEnabled by rememberSaveable(option.enableState.isEnabled()) { mutableStateOf(option.enableState.isEnabled()) }


    // 상태별 배경색
    val backgroundColor = when {
        !isEnabled -> HongColor.GRAY_20.hex
        isChecked -> option.checkedColorHex
        else -> HongColor.TRANSPARENT.hex
    }

    // 상태별 테두리색
    val borderColor = when {
        !isEnabled -> HongColor.GRAY_20.hex
        isChecked -> option.checkedColorHex
        else -> option.border.color
    }

    val injectOption = HongCheckboxBuilder()
        .copy(option)
        .width(option.size)
        .height(option.size)
        .backgroundColor(backgroundColor)
        .onClick {
            if (isEnabled) {
                isChecked = !isChecked
            }

        }
        .border(
            HongBorderInfo(
                width = option.border.width,
                color = borderColor
            )
        )
        .applyOption()

    HongWidgetContainer(injectOption) {
        if (isChecked) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = "체크됨",
                tint = option.checkmarkColorHex.toColor(),
                modifier = Modifier.size(option.size.dp * 0.9f)
            )
        } else if (!isEnabled) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = "비활성화 상태",
                tint = HongColor.GRAY_40.toColor(),
                modifier = Modifier.size(option.size.dp * 0.9f)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHongCheckboxCompose() {
    val option = HongCheckboxBuilder()
        .size(24)
        .backgroundColor(HongColor.TRANSPARENT)
        .checkedColor(HongColor.MAIN_ORANGE_100)
        .checkmarkColor(HongColor.WHITE_100)
        .enableState(HongState.DISABLED)
        .border(
            HongBorderInfo(
                width = 2,
                color = HongColor.GRAY_20.hex
            )
        )
        .radius(
            HongRadiusInfo(
                topLeft = 4,
                topRight = 4,
                bottomLeft = 4,
                bottomRight = 4
            )
        )
        .applyOption()
    HongCheckBoxCompose(option)
}