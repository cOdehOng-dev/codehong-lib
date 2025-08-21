package com.codehong.library.widget.text.count

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.codehong.library.widget.R
import com.codehong.library.widget.button.icon.HongButtonIconBuilder
import com.codehong.library.widget.button.icon.HongButtonIconCompose
import com.codehong.library.widget.extensions.toColor
import com.codehong.library.widget.pretendardFontFamily
import com.codehong.library.widget.rule.HongClickState
import com.codehong.library.widget.rule.HongCountType
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.color.HongColor.Companion.toColor
import com.codehong.library.widget.rule.typo.fontWeight
import com.codehong.library.widget.rule.typo.size
import com.codehong.library.widget.text.HongTextBuilder
import com.codehong.library.widget.text.HongTextCompose
import com.codehong.library.widget.util.HongWidgetNoneClickContainer
import com.codehong.library.widget.util.dpToSp

@Composable
fun HongTextCountCompose(
    option: HongTextCountOption
) {
    var inputText by rememberSaveable {
        mutableStateOf(
            when (option.countType) {
                HongCountType.LONG -> option.startCount.toLong().toString()
                HongCountType.DOUBLE -> "%.1f".format(option.startCount.toDouble())
            }
        )
    }

    val focusManager = LocalFocusManager.current
    var isFocused by rememberSaveable(false) { mutableStateOf(false) }

    val textMeasurer = rememberTextMeasurer()
    val density = LocalDensity.current

    val textStyle = TextStyle(
        color = option.countColorHex.toColor(),
        fontWeight = option.countTypo.fontWeight(),
        fontSize = dpToSp(dp = option.countTypo.size()),
        fontFamily = pretendardFontFamily,
        textAlign = TextAlign.Center,
        platformStyle = PlatformTextStyle(includeFontPadding = false)
    )

    val measuredTextWidth = with(density) {
        textMeasurer.measure(
            text = AnnotatedString(inputText.ifEmpty { "" }),
            style = textStyle
        ).size.width.toDp()
    }

    val minWidth = 80.dp
    val maxWidth = 300.dp
    val dynamicWidth = measuredTextWidth.coerceIn(minWidth, maxWidth)

    val currentValue = inputText.toDoubleOrNull() ?: 0.0

    val isUnderMin = currentValue <= option.minCount.toDouble()
    val isOverMax = option.maxCount?.let { currentValue >= it.toDouble() } ?: false

    val containerOption = HongTextCountBuilder()
        .padding(option.padding)
        .margin(option.margin)
        .applyOption()
    HongWidgetNoneClickContainer(containerOption) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            // ➖ 버튼
            HongButtonIconCompose(
                HongButtonIconBuilder()
                    .buttonType(option.buttonType)
                    .iconResId(R.drawable.honglib_ic_16_minus)
                    .state(if (isUnderMin) HongClickState.DISABLE else HongClickState.ENABLE)
                    .onClick {
                        val next = (currentValue - option.amount.toDouble())
                            .coerceAtLeast(option.minCount.toDouble())
                            .coerceAtLeast(0.0)
                        inputText = when (option.countType) {
                            HongCountType.LONG -> next.toLong().toString()
                            HongCountType.DOUBLE -> "%.1f".format(next)
                        }
                        option.onCountChange(inputText)
                    }
                    .applyOption(),
            )

            // 입력 영역 + 단위 텍스트
            Column(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .onFocusChanged { focusState ->
                        isFocused = focusState.isFocused
                    },
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                BasicTextField(
                    modifier = Modifier.width(dynamicWidth),
                    value = inputText,
                    onValueChange = {
                        inputText = it
                        option.onCountChange(inputText)
                    },
                    textStyle = textStyle,
                    cursorBrush = SolidColor(HongColor.MAIN_ORANGE_100.toColor()),
                    singleLine = true,
                    maxLines = 1,
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done,
                        keyboardType = KeyboardType.Number
                    ),
                    keyboardActions = KeyboardActions(
                        onSearch = { focusManager.clearFocus() }
                    ),
                )

                HongTextCompose(
                    HongTextBuilder()
                        .padding(HongSpacingInfo(top = 4f))
                        .text(option.unitText)
                        .typography(option.unitTypo)
                        .color(option.unitColorHex)
                        .applyOption()
                )
            }

            // ➕ 버튼
            HongButtonIconCompose(
                HongButtonIconBuilder()
                    .buttonType(option.buttonType)
                    .iconResId(R.drawable.honglib_ic_24_plus)
                    .state(if (isOverMax) HongClickState.DISABLE else HongClickState.ENABLE)
                    .onClick {
                        val next = (currentValue + option.amount.toDouble()).let {
                            option.maxCount?.toDouble()?.let { max -> it.coerceAtMost(max) } ?: it
                        }
                        inputText = when (option.countType) {
                            HongCountType.LONG -> next.toLong().toString()
                            HongCountType.DOUBLE -> "%.1f".format(next)
                        }
                        option.onCountChange(inputText)
                    }
                    .applyOption(),
            )
        }
    }
}

