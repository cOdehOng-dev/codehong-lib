package com.codehong.library.widget.util

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.codehong.library.widget.HongWidgetAdvanceOption
import com.codehong.library.widget.HongWidgetCommonOption
import com.codehong.library.widget.extensions.disableRippleClickable
import com.codehong.library.widget.extensions.hongBackground
import com.codehong.library.widget.extensions.hongHeight
import com.codehong.library.widget.extensions.hongPadding
import com.codehong.library.widget.extensions.hongWidth
import com.codehong.library.widget.extensions.toColor
import com.codehong.library.widget.rule.keyboard.HongKeyboardActionType
import com.codehong.library.widget.rule.keyboard.HongKeyboardActionType.Companion.toImeAction
import com.codehong.library.widget.rule.keyboard.HongKeyboardType
import com.codehong.library.widget.rule.keyboard.HongKeyboardType.Companion.toKeyboardType

@Composable
fun dpToSp(dp: Dp) = with(LocalDensity.current) { dp.toSp() }

@Composable
fun dpToSp(dp: Int) = with(LocalDensity.current) { dp.dp.toSp() }

@Composable
fun dpToSp(dp: Float) = with(LocalDensity.current) { dp.dp.toSp() }

@Composable
fun dpToPx(dp: Dp) = with(LocalDensity.current) { dp.toPx() }

@Composable
fun dpToPx(dp: Float) = with(LocalDensity.current) { dp.dp.toPx() }

@Composable
fun dpToPx(dp: Int) = with(LocalDensity.current) { dp.dp.toPx() }

@Composable
fun pxToDp(px: Float) = with(LocalDensity.current) { px.toDp() }

@Composable
fun getStatusHeight(): Dp = WindowInsets.systemBars.asPaddingValues().calculateTopPadding()

fun getAlphaColor(colorHexCode: String, alpha: Int): String {
    val prefix = when (alpha) {
        100 -> "FF"
        99 -> "FC"
        98 -> "FA"
        97 -> "F7"
        96 -> "F5"
        95 -> "F2"
        94 -> "F0"
        93 -> "ED"
        92 -> "EB"
        91 -> "E8"
        90 -> "E6"
        89 -> "E3"
        88 -> "E0"
        87 -> "DE"
        86 -> "DB"
        85 -> "D9"
        84 -> "D6"
        83 -> "D4"
        82 -> "D1"
        81 -> "CF"
        80 -> "CC"
        79 -> "C9"
        78 -> "C7"
        77 -> "C4"
        76 -> "C2"
        75 -> "BF"
        74 -> "BD"
        73 -> "BA"
        72 -> "B8"
        71 -> "B5"
        70 -> "B3"
        69 -> "B0"
        68 -> "AD"
        67 -> "AB"
        66 -> "A8"
        65 -> "A6"
        64 -> "A3"
        63 -> "A1"
        62 -> "9E"
        61 -> "9C"
        60 -> "99"
        59 -> "96"
        58 -> "94"
        57 -> "91"
        56 -> "8F"
        55 -> "8C"
        54 -> "8A"
        53 -> "87"
        52 -> "85"
        51 -> "82"
        50 -> "80"
        49 -> "7D"
        48 -> "7A"
        47 -> "78"
        46 -> "75"
        45 -> "73"
        44 -> "70"
        43 -> "6E"
        42 -> "6B"
        41 -> "69"
        40 -> "66"
        39 -> "63"
        38 -> "61"
        37 -> "5E"
        36 -> "5C"
        35 -> "59"
        34 -> "57"
        33 -> "54"
        32 -> "52"
        31 -> "4F"
        30 -> "4D"
        29 -> "4A"
        28 -> "47"
        27 -> "45"
        26 -> "42"
        25 -> "40"
        24 -> "3D"
        23 -> "3B"
        22 -> "38"
        21 -> "36"
        20 -> "33"
        19 -> "30"
        18 -> "2E"
        17 -> "2B"
        16 -> "29"
        15 -> "26"
        14 -> "24"
        13 -> "21"
        12 -> "1F"
        11 -> "1C"
        10 -> "1A"
        9 -> "17"
        8 -> "14"
        7 -> "12"
        6 -> "0F"
        5 -> "0D"
        4 -> "0A"
        3 -> "08"
        2 -> "05"
        1 -> "03"
        0 -> "00"
        else -> ""
    }

    return "#$prefix${colorHexCode.removePrefix("#")}"
}


@Composable
fun HongWidgetContainer(
    option: HongWidgetCommonOption,
    childCompose: @Composable () -> Unit,
) {
    Box(
        modifier = Modifier
            .hongPadding(option.margin)
    ) {
        Box(
            modifier = Modifier
                .hongWidth(option.width)
                .hongHeight(option.height)
                .hongPadding(option.padding)
                .background(option.backgroundColorHex.toColor())
                .disableRippleClickable { option.click?.invoke(option) }
        ) {
            childCompose()
        }
    }
}


@Composable
fun HongWidgetContainer(
    option: HongWidgetAdvanceOption,
    childCompose: @Composable () -> Unit,
) {
    Box(
        modifier = Modifier
            .hongPadding(option.margin)
    ) {
        Box(
            modifier = Modifier
                .hongWidth(option.width)
                .hongHeight(option.height)
                .hongBackground(
                    backgroundColor = option.backgroundColorHex,
                    border = option.border,
                    shadow = option.shadow,
                    radius = option.radius,
                    useShapeCircle = option.useShapeCircle,
                )
                .hongPadding(option.padding)
                .disableRippleClickable { option.click?.invoke(option) },
            contentAlignment = Alignment.Center
        ) {
            childCompose()
        }
    }
}

fun Pair<HongKeyboardType, HongKeyboardActionType>.toKeyboardOptions(): KeyboardOptions {
    return KeyboardOptions(
        keyboardType = this.first.toKeyboardType(),
        imeAction = this.second.toImeAction()
    )
}

fun Pair<HongKeyboardType, HongKeyboardActionType>.checkPasswordType(): VisualTransformation {
    return when (this.first) {
        HongKeyboardType.PASSWORD,
        HongKeyboardType.NUMBER_PASSWORD -> PasswordVisualTransformation()

        else -> VisualTransformation.None
    }
}
