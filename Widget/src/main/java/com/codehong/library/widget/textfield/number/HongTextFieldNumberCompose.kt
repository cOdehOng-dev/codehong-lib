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
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
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
import com.codehong.library.widget.rule.typo.fontWeight
import com.codehong.library.widget.rule.typo.size
import com.codehong.library.widget.text.HongTextBuilder
import com.codehong.library.widget.text.HongTextCompose
import com.codehong.library.widget.util.HongWidgetContainer
import com.codehong.library.widget.util.checkPasswordType
import com.codehong.library.widget.util.dpToSp
import com.codehong.library.widget.util.toKeyboardOptions
import java.text.DecimalFormat

@Composable
fun HongNumberTextFieldCompose(
    option: HongTextFieldNumberOption
) {
    val focusManager = LocalFocusManager.current
    val hasRemoveButton = option.clearIconRes != null

    HongWidgetContainer(option) {

        val initialFormatted = remember(option.input) {
            if (option.useDecimal) {
                option.input
                    ?.replace(",", "")
                    ?.toLongOrNull()
                    ?.let { DecimalFormat("#,###").format(it) }
                    ?: ""
            } else {
                option.input ?: ""
            }
        }

        var textFieldValue by rememberSaveable(
            initialFormatted,
            stateSaver = androidx.compose.runtime.saveable.Saver(
                save = { it.text },
                restore = { TextFieldValue(it) }
            )
        ) {
            mutableStateOf(TextFieldValue(initialFormatted))
        }

        var isFormatting by remember { mutableStateOf(false) }

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.CenterStart
            ) {
                if (textFieldValue.text.isEmpty()) {
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
                    modifier = Modifier.fillMaxWidth(),
                    value = textFieldValue,
                    onValueChange = { newValue ->
                        if (isFormatting) return@BasicTextField

                        val cleanInput = newValue.text.replace(",", "").replace(" ", "")
                        val formatted = try {
                            val parsed = cleanInput.toLong()
                            DecimalFormat("#,###").format(parsed)
                        } catch (e: NumberFormatException) {
                            ""
                        }

                        isFormatting = true

                        val finalText = if (option.useDecimal) formatted else newValue.text
                        textFieldValue = TextFieldValue(
                            text = finalText,
                            selection = TextRange(finalText.length)
                        )
                        option.onTextChanged(finalText)

                        isFormatting = false
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
                if (textFieldValue.text.isNotEmpty()) {
                    HongImageCompose(
                        HongImageBuilder()
                            .width(option.clearIconSize)
                            .height(option.clearIconSize)
                            .margin(option.clearIconMargin)
                            .imageInfo(option.clearIconRes)
                            .onClick {
                                textFieldValue = TextFieldValue("")
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
    val option = HongTextFieldNumberBuilder()
        .width(HongLayoutParam.MATCH_PARENT.value)
        .margin(
            HongSpacingInfo(
                top = 20f,
                bottom = 20f,
                left = 20f
            )
        )
        .backgroundColor(HongColor.WHITE_100)
        .placeholder("값을 입력해주세요.")
        .input("53600")
        .cursorColor(HongColor.MAIN_ORANGE_100.hex)
        .applyOption()
    HongNumberTextFieldCompose(option)
}