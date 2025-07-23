package com.codehong.library.widget.textfield.number

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.codehong.library.widget.rule.keyboard.HongKeyboardType
import com.codehong.library.widget.text.HongTextBuilder
import com.codehong.library.widget.text.HongTextCompose
import com.codehong.library.widget.textfield.HongTextFieldOption
import com.codehong.library.widget.util.HongWidgetContainer
import com.codehong.library.widget.util.checkPasswordType
import com.codehong.library.widget.util.dpToSp
import com.codehong.library.widget.util.toKeyboardOptions
import java.text.DecimalFormat

@Composable
fun HongNumberTextFieldCompose(
    option: HongNumberTextFieldOption
) {
    val focusManager = LocalFocusManager.current
    val hasRemoveButton = option.clearImageOption != null

    HongWidgetContainer(option) {

        val initialFormatted = remember(option.inputTextOption.text) {
            if (option.useDecimal) {
                option.inputTextOption.text
                    ?.replace(",", "")
                    ?.toLongOrNull()
                    ?.let { DecimalFormat("#,###").format(it) }
                    ?: ""
            } else {
                option.inputTextOption.text ?: ""
            }
        }

        var inputText by rememberSaveable(initialFormatted) {
            mutableStateOf(initialFormatted)
        }

        var isFormatting by remember { mutableStateOf(false) } // 🔧 포맷 중 여부 플래그 추가

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.CenterStart
            ) {
                if (inputText.isEmpty()) {
                    HongTextCompose(
                        option = option.placeholderTextOption
                    )
                }

                val inputTextOption = option.inputTextOption

                BasicTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = inputText,
                    onValueChange = {
                        if (isFormatting) return@BasicTextField // 🔧 무한 루프 방지

                        val cleanInput = it.replace(",", "").replace(" ", "")
                        val formatted = try {
                            val parsed = cleanInput.toLong()
                            DecimalFormat("#,###").format(parsed)
                        } catch (e: NumberFormatException) {
                            ""
                        }

                        isFormatting = true // 🔧 포맷 시작

                        inputText = if (option.useDecimal) formatted else it
                        option.onTextChanged(if (option.useDecimal) formatted else it)

                        isFormatting = false // 🔧 포맷 종료
                    },
                    textStyle = TextStyle(
                        color = inputTextOption.colorHex.toColor(),
                        fontWeight = inputTextOption.fontType?.weight,
                        fontSize = dpToSp(dp = inputTextOption.size ?: 10),
                        fontFamily = pretendardFontFamily,
                        platformStyle = PlatformTextStyle(includeFontPadding = false)
                    ),
                    cursorBrush = SolidColor(
                        if (option.cursorColor.isEmpty()) {
                            HongColor.MAIN_ORANGE_100.toColor()
                        } else {
                            option.cursorColor.toColor()
                        }
                    ),
                    singleLine = true,
                    maxLines = 1,
                    keyboardOptions = Pair(
                        HongKeyboardType.NUMBER,
                        option.keyboardOption.second
                    ).toKeyboardOptions(),
                    keyboardActions = option.keyboardOption.second.toKeyboardActions {
                        if (option.useHideKeyboard) {
                            focusManager.clearFocus()
                        }
                    },
                    visualTransformation = option.keyboardOption.checkPasswordType()
                )
            }

            if (hasRemoveButton) {
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
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewHongNumberTextFieldCompose() {
    val option = HongNumberTextFieldBuilder()
        .width(HongLayoutParam.MATCH_PARENT.value)
        .margin(
            HongSpacingInfo(
                top = 20f,
                bottom = 20f,
                left = 20f
            )
        )
        .backgroundColor(HongColor.WHITE_100)
        .placeholderTextOption(
            HongTextBuilder()
                .copy(HongTextFieldOption.DEFAULT_PLACEHOLDER)
                .text("값을 입력해주세요.")
                .applyOption()
        )
        .input("53600")
        .cursorColor(HongColor.MAIN_ORANGE_100.hex)
        .applyOption()
    HongNumberTextFieldCompose(option)
}