package com.codehong.library.widget.textfield

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import com.codehong.library.widget.extensions.toColor
import com.codehong.library.widget.image.HongImageBuilder
import com.codehong.library.widget.image.HongImageCompose
import com.codehong.library.widget.pretendardFontFamily
import com.codehong.library.widget.rule.HongLayoutParam
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.color.HongColor.Companion.toColor
import com.codehong.library.widget.rule.keyboard.HongKeyboardActionType.Companion.toKeyboardActions
import com.codehong.library.widget.rule.radius.HongRadiusInfo
import com.codehong.library.widget.rule.typo.fontWeight
import com.codehong.library.widget.rule.typo.size
import com.codehong.library.widget.text.HongTextBuilder
import com.codehong.library.widget.text.HongTextCompose
import com.codehong.library.widget.util.HongWidgetContainer
import com.codehong.library.widget.util.checkPasswordType
import com.codehong.library.widget.util.dpToSp
import com.codehong.library.widget.util.toKeyboardOptions
import kotlinx.coroutines.delay

@Composable
fun HongTextFieldCompose(
    option: HongTextFieldOption,
) {
    val focusManager = LocalFocusManager.current

    val hasRemoveButton = option.clearIconRes != null
    val hasDelayInputCallback =
        option.delayInputCallback > HongTextFieldOption.DEFAULT_DELAY_INPUT_CALLBACK

    when {
        hasRemoveButton && hasDelayInputCallback -> {
            val debounceTime = option.delayInputCallback
            var inputText by rememberSaveable(option.input ?: "") {
                mutableStateOf(
                    option.input ?: ""
                )
            }
            var debouncedInput by rememberSaveable { mutableStateOf("") }

            LaunchedEffect(inputText) {
                delay(debounceTime)
                if (inputText == debouncedInput) return@LaunchedEffect
                debouncedInput = inputText
                option.onTextChanged(inputText)
            }

            HongWidgetContainer(option) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier.weight(1f),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        if (inputText.isEmpty()) {
                            HongTextCompose(
                                HongTextBuilder()
                                    .width(HongLayoutParam.MATCH_PARENT.value)
                                    .padding(option.placeholderPadding)
                                    .text(option.placeholder ?: "")
                                    .typography(option.placeholderTypo)
                                    .color(option.placeholderColorHex)
                                    .applyOption()
                            )
                        }

                        BasicTextField(
                            modifier = Modifier
                                .fillMaxWidth(),
                            value = inputText,
                            onValueChange = {
                                inputText = it
                            },
                            textStyle = TextStyle(
                                color = option.inputColorHex.toColor(),
                                fontWeight = option.inputTypo.fontWeight(),
                                fontSize = dpToSp(dp = option.inputTypo.size()),
                                fontFamily = pretendardFontFamily,
                                platformStyle = PlatformTextStyle(includeFontPadding = false)
                            ),
                            cursorBrush = SolidColor(
                                if (option.cursorColorHex.isEmpty()) {
                                    HongColor.MAIN_ORANGE_100.toColor()
                                } else {
                                    option.cursorColorHex.toColor()
                                }
                            ),
                            singleLine = option.singleLine,
                            maxLines = if (option.singleLine) 1 else option.maxLines,
                            minLines = option.minLines,
                            keyboardOptions = option.keyboardOption.toKeyboardOptions(),
                            keyboardActions = option.keyboardOption.second.toKeyboardActions {
                                if (option.useHideKeyboard) {
                                    focusManager.clearFocus()
                                }
                            },
                            visualTransformation = option.keyboardOption.checkPasswordType()
                        )
                    }

                    if (inputText.isNotEmpty()) {
                        HongImageCompose(
                            option = HongImageBuilder()
                                .width(option.clearIconSize)
                                .height(option.clearIconSize)
                                .margin(option.clearIconMargin)
                                .drawableResId(option.clearIconRes)
                                .onClick {
                                    inputText = ""
                                }
                                .applyOption()
                        )
                    }
                }
            }
        }

        hasRemoveButton -> {
            HongWidgetContainer(option) {
                var inputText by rememberSaveable(option.input ?: "") {
                    mutableStateOf(
                        option.input ?: ""
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier.weight(1f),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        if (inputText.isEmpty()) {
                            HongTextCompose(
                                option = HongTextBuilder()
                                    .width(HongLayoutParam.MATCH_PARENT.value)
                                    .padding(option.placeholderPadding)
                                    .text(option.placeholder ?: "")
                                    .typography(option.placeholderTypo)
                                    .color(option.placeholderColorHex)
                                    .applyOption()
                            )
                        }

                        BasicTextField(
                            modifier = Modifier
                                .fillMaxWidth(),
                            value = inputText,
                            onValueChange = {
                                inputText = it
                                option.onTextChanged(it)
                            },
                            textStyle = TextStyle(
                                color = option.inputColorHex.toColor(),
                                fontWeight = option.inputTypo.fontWeight(),
                                fontSize = dpToSp(dp = option.inputTypo.size()),
                                fontFamily = pretendardFontFamily,
                                platformStyle = PlatformTextStyle(includeFontPadding = false)
                            ),
                            cursorBrush = SolidColor(
                                if (option.cursorColorHex.isEmpty()) {
                                    HongColor.MAIN_ORANGE_100.toColor()
                                } else {
                                    option.cursorColorHex.toColor()
                                }
                            ),
                            singleLine = option.singleLine,
                            maxLines = if (option.singleLine) 1 else option.maxLines,
                            minLines = option.minLines,
                            keyboardOptions = option.keyboardOption.toKeyboardOptions(),
                            keyboardActions = option.keyboardOption.second.toKeyboardActions {
                                if (option.useHideKeyboard) {
                                    focusManager.clearFocus()
                                }
                            },
                            visualTransformation = option.keyboardOption.checkPasswordType()
                        )
                    }

                    if (inputText.isNotEmpty()) {
                        HongImageCompose(
                            option = HongImageBuilder()
                                .width(option.clearIconSize)
                                .height(option.clearIconSize)
                                .margin(option.clearIconMargin)
                                .drawableResId(option.clearIconRes)
                                .onClick {
                                    inputText = ""
                                }
                                .applyOption()
                        )
                    }
                }
            }
        }

        hasDelayInputCallback -> {
            val debounceTime = option.delayInputCallback
            var inputText by rememberSaveable(option.input ?: "") {
                mutableStateOf(
                    option.input ?: ""
                )
            }
            var debouncedInput by rememberSaveable { mutableStateOf("") }

            LaunchedEffect(inputText) {
                delay(debounceTime)
                if (inputText == debouncedInput) return@LaunchedEffect
                debouncedInput = inputText
                option.onTextChanged(inputText)
            }

            HongWidgetContainer(option) {
                if (inputText.isEmpty()) {
                    HongTextCompose(
                        option = HongTextBuilder()
                            .width(HongLayoutParam.MATCH_PARENT.value)
                            .padding(option.placeholderPadding)
                            .text(option.placeholder ?: "")
                            .typography(option.placeholderTypo)
                            .color(option.placeholderColorHex)
                            .applyOption()
                    )
                }

                BasicTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = inputText,
                    onValueChange = {
                        inputText = it
                    },

                    textStyle = TextStyle(
                        color = option.inputColorHex.toColor(),
                        fontWeight = option.inputTypo.fontWeight(),
                        fontSize = dpToSp(dp = option.inputTypo.size()),
                        fontFamily = pretendardFontFamily,
                        platformStyle = PlatformTextStyle(includeFontPadding = false)
                    ),
                    cursorBrush = SolidColor(
                        if (option.cursorColorHex.isEmpty()) {
                            HongColor.MAIN_ORANGE_100.toColor()
                        } else {
                            option.cursorColorHex.toColor()
                        }
                    ),
                    singleLine = option.singleLine,
                    maxLines = if (option.singleLine) 1 else option.maxLines,
                    minLines = option.minLines,
                    keyboardOptions = option.keyboardOption.toKeyboardOptions(),
                    keyboardActions = option.keyboardOption.second.toKeyboardActions {
                        if (option.useHideKeyboard) {
                            focusManager.clearFocus()
                        }
                    },
                    visualTransformation = option.keyboardOption.checkPasswordType()
                )
            }
        }

        else -> {
            HongWidgetContainer(option) {
                var input by rememberSaveable(option.input ?: "") {
                    mutableStateOf(
                        option.input ?: ""
                    )
                }
                if (input.isEmpty()) {
                    HongTextCompose(
                        HongTextBuilder()
                            .width(HongLayoutParam.MATCH_PARENT.value)
                            .padding(option.placeholderPadding)
                            .text(option.placeholder ?: "")
                            .padding(option.placeholderPadding)
                            .typography(option.placeholderTypo)
                            .color(option.placeholderColorHex)
                            .applyOption()
                    )
                }

                BasicTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = input,
                    onValueChange = {
                        input = it
                        option.onTextChanged(it)
                    },
                    textStyle = TextStyle(
                        color = option.inputColorHex.toColor(),
                        fontWeight = option.inputTypo.fontWeight(),
                        fontSize = dpToSp(dp = option.inputTypo.size()),
                        fontFamily = pretendardFontFamily,
                        platformStyle = PlatformTextStyle(includeFontPadding = false)
                    ),
                    cursorBrush = SolidColor(
                        if (option.cursorColorHex.isEmpty()) {
                            HongColor.MAIN_ORANGE_100.toColor()
                        } else {
                            option.cursorColorHex.toColor()
                        }
                    ),
                    singleLine = option.singleLine,
                    maxLines = if (option.singleLine) 1 else option.maxLines,
                    minLines = option.minLines,
                    keyboardOptions = option.keyboardOption.toKeyboardOptions(),
                    keyboardActions = option.keyboardOption.second.toKeyboardActions {
                        if (option.useHideKeyboard) {
                            focusManager.clearFocus()
                        }
                    },
                    visualTransformation = option.keyboardOption.checkPasswordType()
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewHongTextFieldCompose() {
    val option1 = HongTextFieldBuilder()
        .width(HongLayoutParam.MATCH_PARENT.value)
        .height(44)
        .padding(
            HongSpacingInfo(
                left = 16f,
                right = 16f,
                top = 12f,
                bottom = 12f
            )
        )
        .backgroundColor(HongColor.BLACK_05.hex)
        .radius(
            HongRadiusInfo(
                all = 50
            )
        )
        .placeholder("값을 입력해주세요.")
        .input("입력!!")
        .cursorColor(HongColor.MAIN_ORANGE_100.hex)
        .applyOption()
    HongTextFieldCompose(option = option1)
}
