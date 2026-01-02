package com.codehong.library.widget.textfield.border

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.codehong.library.widget.R
import com.codehong.library.widget.extensions.hongBackground
import com.codehong.library.widget.extensions.hongSpacing
import com.codehong.library.widget.extensions.toColor
import com.codehong.library.widget.image.HongImageBuilder
import com.codehong.library.widget.image.HongImageCompose
import com.codehong.library.widget.pretendardFontFamily
import com.codehong.library.widget.rule.HongBorderInfo
import com.codehong.library.widget.rule.HongInputState
import com.codehong.library.widget.rule.HongLayoutParam
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.color.HongColor.Companion.toColor
import com.codehong.library.widget.rule.typo.HongTypo
import com.codehong.library.widget.rule.typo.fontWeight
import com.codehong.library.widget.rule.typo.size
import com.codehong.library.widget.text.def.HongTextBuilder
import com.codehong.library.widget.text.def.HongTextCompose
import com.codehong.library.widget.util.HongWidgetContainer
import com.codehong.library.widget.util.dpToSp

@Composable
fun HongTextFieldBorderCompose(
    option: HongTextFieldBorderOption,
) {
    val remOption by remember { mutableStateOf(option) }

    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()
    val focusManager = LocalFocusManager.current

    var input by remember(remOption.initialInput) { mutableStateOf(remOption.initialInput) }

    val isEnabled = remOption.state == HongInputState.ENABLE

    val borderColor = when {
        !isEnabled -> HongColor.GRAY_10.hex
        isFocused && isEnabled -> remOption.focusedBorderColorHex
        else -> remOption.enableBorderColorHex
    }

    val backgroundColor = when {
        isEnabled -> remOption.inputBackgroundColorHex
        else -> HongColor.GRAY_10.hex
    }

    val labelTextColor = when {
        isEnabled -> remOption.labelColoHex
        else -> HongColor.GRAY_30.hex
    }

    val inputTextColor = when {
        isEnabled -> remOption.inputTextColorHex
        else -> HongColor.GRAY_30.hex
    }

    val placeholderTextColor = when {
        isEnabled -> remOption.placeholderColorHex
        else -> HongColor.GRAY_30.hex
    }

    val paddingMarginOption = HongTextFieldBorderBuilder()
        .width(HongLayoutParam.MATCH_PARENT.value)
        .padding(remOption.padding)
        .margin(remOption.margin)
        .applyOption()

    HongWidgetContainer(paddingMarginOption) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .hongBackground(
                        color = backgroundColor,
                        radius = remOption.inputRadius,
                        border = HongBorderInfo(
                            width = 1,
                            color = borderColor
                        )
                    )
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                ) {
                    Row(
                        modifier = Modifier
                            .padding(
                                top = 18.dp,
                                start = 19.dp,
                                bottom = 10.dp
                            ),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        HongTextCompose(
                            HongTextBuilder()
                                .text(remOption.label)
                                .typography(remOption.labelTypo)
                                .color(labelTextColor)
                                .applyOption()
                        )

                        if (remOption.isRequired && isEnabled) {
                            HongTextCompose(
                                HongTextBuilder()
                                    .text(" *")
                                    .typography(remOption.labelTypo)
                                    .color(HongColor.MAIN_ORANGE_100)
                                    .applyOption()
                            )
                        }
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // BasicTextField 영역
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(),
                            contentAlignment = Alignment.CenterStart
                        ) {
                            if (input.isEmpty() && remOption.placeholder.isNotEmpty()) {
                                HongTextCompose(
                                    HongTextBuilder()
                                        .width(HongLayoutParam.MATCH_PARENT.value)
                                        .padding(
                                            HongSpacingInfo(
                                                left = 20f,
                                                bottom = 18f
                                            )
                                        )
                                        .text(remOption.placeholder)
                                        .typography(remOption.placeholderTypo)
                                        .color(placeholderTextColor)
                                        .applyOption()
                                )
                            }

                            BasicTextField(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .hongSpacing(
                                        HongSpacingInfo(
                                            left = 20f,
                                            bottom = 18f
                                        )
                                    ),
                                value = input,
                                onValueChange = {
                                    if (isEnabled) {
                                        input = it
                                        remOption.onChangeInput(it)
                                    }
                                },
                                enabled = isEnabled,
                                readOnly = !isEnabled,
                                textStyle = TextStyle(
                                    color = inputTextColor.toColor(),
                                    fontFamily = pretendardFontFamily,
                                    fontSize = dpToSp(HongTypo.BODY_16.size()),
                                    fontWeight = HongTypo.BODY_16.fontWeight(),
                                    platformStyle = PlatformTextStyle(includeFontPadding = false)
                                ),
                                keyboardOptions = KeyboardOptions(
                                    imeAction = ImeAction.Done,
                                    keyboardType = if (remOption.useNumberKeypad) {
                                        KeyboardType.Number
                                    } else {
                                        KeyboardType.Text
                                    }
                                ),
                                keyboardActions = KeyboardActions(
                                    onDone = {
                                        focusManager.clearFocus()
                                    }
                                ),
                                interactionSource = interactionSource,
                                cursorBrush = SolidColor(HongColor.MAIN_ORANGE_100.toColor()),
                                singleLine = true,
                            )
                        }
                    }
                }

                Row(
                    modifier = Modifier
                        .padding(top = 28.dp)
                ) {
                    if (remOption.suffix.isNotEmpty()) {
                        Box(
                            modifier = Modifier
                                .padding(
                                    end = if (input.isNotEmpty() && isEnabled && remOption.useClearButton) 0.dp else 20.dp
                                )
                                .width(40.dp)
                                .height(52.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            HongTextCompose(
                                HongTextBuilder()
                                    .text(remOption.suffix)
                                    .typography(remOption.suffixTypo)
                                    .color(inputTextColor)
                                    .applyOption()
                            )

                        }
                    }

                    if (input.isNotEmpty()
                        && isEnabled
                        && remOption.useClearButton
                    ) {
                        Box(
                            modifier = Modifier
                                .width(60.dp)
                                .height(52.dp)
                                .clickable(
                                    interactionSource = remember { MutableInteractionSource() },
                                    indication = null
                                ) {
                                    input = ""
                                    remOption.onChangeInput("")
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            HongImageCompose(
                                HongImageBuilder()
                                    .width(20)
                                    .height(20)
                                    .imageInfo(R.drawable.honglib_ic_20_circle_close_fill)
                                    .applyOption()
                            )
                        }
                    }

                }
            }

            if (remOption.helperText.isNotEmpty()) {
                HongTextCompose(
                    HongTextBuilder()
                        .padding(
                            HongSpacingInfo(
                                top = 9f
                            )
                        )
                        .text(option.helperText)
                        .typography(option.helperTextTypo)
                        .color(inputTextColor)
                        .maxLines(2)
                        .applyOption()
                )
            }
        }
    }
}