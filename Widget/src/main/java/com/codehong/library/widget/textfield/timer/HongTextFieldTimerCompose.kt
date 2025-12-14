package com.codehong.library.widget.textfield.timer

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import com.codehong.library.widget.HongDivider
import com.codehong.library.widget.extensions.hongHeight
import com.codehong.library.widget.extensions.hongWidth
import com.codehong.library.widget.extensions.toColor
import com.codehong.library.widget.image.HongImageBuilder
import com.codehong.library.widget.image.HongImageCompose
import com.codehong.library.widget.pretendardFontFamily
import com.codehong.library.widget.rule.HongLayoutParam
import com.codehong.library.widget.rule.HongSpacingInfo
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.color.HongColor.Companion.hexToHongColor
import com.codehong.library.widget.rule.color.HongColor.Companion.toColor
import com.codehong.library.widget.rule.keyboard.HongKeyboardActionType
import com.codehong.library.widget.rule.keyboard.HongKeyboardActionType.Companion.toKeyboardActions
import com.codehong.library.widget.rule.keyboard.HongKeyboardType
import com.codehong.library.widget.rule.typo.fontWeight
import com.codehong.library.widget.rule.typo.size
import com.codehong.library.widget.text.HongTextBuilder
import com.codehong.library.widget.text.HongTextCompose
import com.codehong.library.widget.util.HongWidgetContainer
import com.codehong.library.widget.util.checkPasswordType
import com.codehong.library.widget.util.dpToSp
import com.codehong.library.widget.util.toKeyboardOptions
import kotlinx.coroutines.delay
import java.util.Locale

@Composable
fun HongTimerTextFieldCompose(
    option: HongTextFieldTimerOption
) {
    val focusManager = LocalFocusManager.current

    var inputText by rememberSaveable(option.input ?: "") { mutableStateOf(option.input ?: "") }
    var isFocused by rememberSaveable(false) { mutableStateOf(false) }

    var isFinishTimer by rememberSaveable(false) { mutableStateOf(false) }

    val totalTimeMillis = rememberSaveable(option.min, option.sec) {
        (option.min * 60 + option.sec) * 1000L
    }
    var millisLeft by rememberSaveable { mutableLongStateOf(totalTimeMillis) }

    LaunchedEffect(totalTimeMillis) {
        while (millisLeft > 0) {
            delay(1000L)
            millisLeft -= 1000L
        }
        millisLeft = 0L
        isFinishTimer = true
        option.onFinish?.invoke()
    }

    HongWidgetContainer(option) {
        Column(
            modifier = Modifier
                .hongWidth(option.width)
                .hongHeight(option.height),
        ) {
            Row(
                modifier = Modifier
                    .weight(1f),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .onFocusChanged { focusState ->
                            isFocused = focusState.isFocused

                        },
                    contentAlignment = Alignment.CenterStart
                ) {
                    if (inputText.isEmpty()) {
                        HongTextCompose(
                            HongTextBuilder()
                                .width(HongLayoutParam.MATCH_PARENT.value)
                                .text(option.placeholder)
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
                        singleLine = true,
                        maxLines = 1,
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
                            .copy(option.clearImageOption)
                            .onClick {
                                inputText = ""
                            }
                            .applyOption()
                    )
                }

                val minutes = (millisLeft / 1000) / 60
                val seconds = (millisLeft / 1000) % 60
                val formattedTime = String.format(Locale.KOREA,"%02d:%02d", minutes, seconds)
                HongTextCompose(
                    HongTextBuilder()
                        .copy(option.countDownTextOption)
                        .text(formattedTime)
                        .applyOption()
                )
            }

            HongDivider(
                color = (
                        if (isFinishTimer) {
                            option.underlineFinishColorHex
                        } else {
                            if (isFocused) {
                                option.underlineFocusColorHex
                            } else {
                                option.underlineOutFocusColorHex
                            }
                        }
                        ).hexToHongColor(),
                height = option.underlineHeight
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewHongTimerTextFieldCompose() {
    val option = HongTextFieldTimerBuilder()
        .width(HongLayoutParam.MATCH_PARENT.value)
        .height(48)
        .margin(
            HongSpacingInfo(
                left = 20f,
                right = 20f
            )
        )
        .backgroundColor(HongColor.WHITE_100.hex)
        .placeholder("[지우기 버튼] 값을 입력해주세요.")
        .keyboardOption(Pair(HongKeyboardType.TEXT, HongKeyboardActionType.DONE))
        .cursorColor(HongColor.MAIN_ORANGE_100.hex)
        .onTextChanged { _ -> }
        .clearImageOption(
            HongImageBuilder()
                .copy(HongTextFieldTimerOption.DEFAULT_CLEAR_IMAGE)
                .width(16)
                .height(16)
                .applyOption()
        )
        .sec(10)
        .underlineFinishColor(HongColor.RED_100)
        .applyOption()
    HongTimerTextFieldCompose(option)
}