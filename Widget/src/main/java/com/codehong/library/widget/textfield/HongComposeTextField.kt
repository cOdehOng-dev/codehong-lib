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
import androidx.compose.ui.text.font.FontWeight
import com.codehong.library.widget.ColorType
import com.codehong.library.widget.MarginStartOrEnd
import com.codehong.library.widget.R
import com.codehong.library.widget.disableRippleClickable
import com.codehong.library.widget.image.HongImage
import com.codehong.library.widget.model.HongComposeColor
import com.codehong.library.widget.model.keyboard.KeyboardType
import com.codehong.library.widget.model.keyboard.KeyboardTypeUtil.getAction
import com.codehong.library.widget.model.keyboard.KeyboardTypeUtil.getOption
import com.codehong.library.widget.model.text.HongComposeTextStyle
import com.codehong.library.widget.pretendardFontFamily
import com.codehong.library.widget.text.HongText
import com.codehong.library.widget.typo.TypoType
import com.codehong.library.widget.util.dpToSp
import com.codehong.library.widget.util.getColor
import kotlinx.coroutines.delay

@Composable
fun HongTextField(
    modifier: Modifier = Modifier,
    inputText: String,
    placeholder: String = "",
    placeholderStyle: HongComposeTextStyle = HongComposeTextStyle(
        fontWeight = FontWeight.W400,
        color = HongComposeColor(
            resId = ColorType.BLACK_60.colorResId,
            type = ColorType.BLACK_60
        ),
        size = 16,
        typo = TypoType.BODY_16
    ),
    inputStyle: HongComposeTextStyle = HongComposeTextStyle(
        fontWeight = FontWeight.W700,
        color = HongComposeColor(
            resId = ColorType.BLACK_100.colorResId,
            type = ColorType.BLACK_100
        ),
        size = 16,
        typo = TypoType.BODY_16_B
    ),
    cursorColor: HongComposeColor = HongComposeColor(
        resId = ColorType.PRIMARY_MINT.colorResId,
        type = ColorType.PRIMARY_MINT
    ),
    useHideKeyboard: Boolean = true,
    singleLine: Boolean = true,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    keyboardType: KeyboardType = KeyboardType.DONE,
    onTextChanged: (String) -> Unit
) {
    val focusManager = LocalFocusManager.current

    Box(
        modifier = modifier,
        contentAlignment = Alignment.CenterStart
    ) {
        if (inputText.isEmpty()) {
            HongText(
                text = placeholder,
                style = placeholderStyle
            )
        }

        BasicTextField(
            modifier = Modifier.fillMaxWidth(),
            value = inputText,
            onValueChange = {
                onTextChanged(it)
            },
            textStyle = TextStyle(
                color = inputStyle.color.getColor(),
                fontWeight = inputStyle.fontWeight,
                fontSize = dpToSp(dp = inputStyle.size),
                fontFamily = pretendardFontFamily,
                platformStyle = PlatformTextStyle(includeFontPadding = false)
            ),
            cursorBrush = SolidColor(cursorColor.getColor()),
            singleLine = singleLine,
            maxLines = maxLines,
            minLines = minLines,
            keyboardOptions = keyboardType.getOption(),
            keyboardActions = keyboardType.getAction {
                if (useHideKeyboard) {
                    focusManager.clearFocus()
                }
            }
        )
    }
}

@Composable
fun HongTextField(
    modifier: Modifier = Modifier,
    placeholder: String = "",
    placeholderStyle: HongComposeTextStyle = HongComposeTextStyle(
        fontWeight = FontWeight.W400,
        color = HongComposeColor(
            resId = ColorType.BLACK_60.colorResId,
            type = ColorType.BLACK_60
        ),
        size = 16,
        typo = TypoType.BODY_16
    ),
    inputStyle: HongComposeTextStyle = HongComposeTextStyle(
        fontWeight = FontWeight.W700,
        color = HongComposeColor(
            resId = ColorType.BLACK_100.colorResId,
            type = ColorType.BLACK_100
        ),
        size = 16,
        typo = TypoType.BODY_16_B
    ),
    cursorColor: HongComposeColor = HongComposeColor(
        resId = ColorType.PRIMARY_MINT.colorResId,
        type = ColorType.PRIMARY_MINT
    ),
    useHideKeyboard: Boolean = true,
    singleLine: Boolean = true,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    debounceTime: Long = 0L,
    keyboardType: KeyboardType = KeyboardType.DONE,
    onTextChanged: (String) -> Unit
) {
    var inputText by rememberSaveable { mutableStateOf("") }
    var debouncedInput by rememberSaveable { mutableStateOf("") }
    if (debounceTime > 0L) {
        LaunchedEffect(inputText) {
            delay(debounceTime) // 마지막 입력 후 500ms 기다림
            if (inputText == debouncedInput) return@LaunchedEffect
            debouncedInput = inputText
            onTextChanged(inputText)
        }
    }

    HongTextField(
        modifier = modifier,
        placeholder = placeholder,
        inputText = inputText,

        placeholderStyle = placeholderStyle,

        inputStyle = inputStyle,

        cursorColor = cursorColor,
        useHideKeyboard = useHideKeyboard,
        singleLine = singleLine,
        maxLines = maxLines,
        minLines = minLines,
        keyboardType = keyboardType,
        onTextChanged = {
            inputText = it
            if (debounceTime == 0L) {
                onTextChanged(it)
            }
        }
    )
}

@Composable
fun HongTextFieldRemoveButton(
    modifier: Modifier = Modifier,
    inputText: String,
    placeholder: String = "",
    placeholderStyle: HongComposeTextStyle = HongComposeTextStyle(
        fontWeight = FontWeight.W400,
        color = HongComposeColor(
            resId = ColorType.BLACK_60.colorResId,
            type = ColorType.BLACK_60
        ),
        size = 16,
        typo = TypoType.BODY_16
    ),
    inputStyle: HongComposeTextStyle = HongComposeTextStyle(
        fontWeight = FontWeight.W700,
        color = HongComposeColor(
            resId = ColorType.BLACK_100.colorResId,
            type = ColorType.BLACK_100
        ),
        size = 16,
        typo = TypoType.BODY_16_B
    ),
    cursorColor: HongComposeColor = HongComposeColor(
        resId = ColorType.PRIMARY_MINT.colorResId,
        type = ColorType.PRIMARY_MINT
    ),
    useHideKeyboard: Boolean = true,
    singleLine: Boolean = true,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    keyboardType: KeyboardType = KeyboardType.DONE,
    onTextChanged: (String) -> Unit,
    removeClick: () -> Unit
) {
    val focusManager = LocalFocusManager.current

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier.weight(1f),
            contentAlignment = Alignment.CenterStart
        ) {
            if (inputText.isEmpty()) {
                HongText(
                    text = placeholder,
                    style = placeholderStyle
                )
            }

            BasicTextField(
                modifier = Modifier.fillMaxWidth(),
                value = inputText,
                onValueChange = {
                    onTextChanged(it)
                },
                textStyle = TextStyle(
                    color = inputStyle.color.getColor(),
                    fontWeight = inputStyle.fontWeight,
                    fontSize = dpToSp(dp = inputStyle.size),
                    fontFamily = pretendardFontFamily,
                    platformStyle = PlatformTextStyle(includeFontPadding = false)
                ),
                cursorBrush = SolidColor(cursorColor.getColor()),
                singleLine = singleLine,
                maxLines = maxLines,
                minLines = minLines,
                keyboardOptions = keyboardType.getOption(),
                keyboardActions = keyboardType.getAction {
                    if (useHideKeyboard) {
                        focusManager.clearFocus()
                    }
                }
            )
        }
        MarginStartOrEnd(8)

        if (inputText.isNotEmpty()) {
            HongImage(
                modifier = Modifier
                    .disableRippleClickable {
                        removeClick.invoke()
                    },
                drawableResId = R.drawable.honglib_ic_20_close
            )
        }
    }
}

@Composable
fun HongTextFieldRemoveButton(
    modifier: Modifier = Modifier,
    placeholder: String = "",
    placeholderStyle: HongComposeTextStyle = HongComposeTextStyle(
        fontWeight = FontWeight.W400,
        color = HongComposeColor(
            resId = ColorType.BLACK_60.colorResId,
            type = ColorType.BLACK_60
        ),
        size = 16,
        typo = TypoType.BODY_16
    ),
    inputStyle: HongComposeTextStyle = HongComposeTextStyle(
        fontWeight = FontWeight.W700,
        color = HongComposeColor(
            resId = ColorType.BLACK_100.colorResId,
            type = ColorType.BLACK_100
        ),
        size = 16,
        typo = TypoType.BODY_16_B
    ),
    cursorColor: HongComposeColor = HongComposeColor(
        resId = ColorType.PRIMARY_MINT.colorResId,
        type = ColorType.PRIMARY_MINT
    ),
    useHideKeyboard: Boolean = true,
    singleLine: Boolean = true,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    debounceTime: Long = 0L,
    keyboardType: KeyboardType = KeyboardType.DONE,
    onTextChanged: (String) -> Unit
) {
    var inputText by rememberSaveable { mutableStateOf("") }
    var debouncedInput by rememberSaveable { mutableStateOf("") }

    if (debounceTime > 0L) {
        LaunchedEffect(inputText) {
            delay(debounceTime)
            if (inputText == debouncedInput) return@LaunchedEffect
            debouncedInput = inputText
            onTextChanged(inputText)
        }
    }

    HongTextFieldRemoveButton(
        modifier = modifier,

        placeholder = placeholder,
        placeholderStyle = placeholderStyle,

        inputText = inputText,
        inputStyle = inputStyle,

        cursorColor = cursorColor,
        useHideKeyboard = useHideKeyboard,
        singleLine = singleLine,
        maxLines = maxLines,
        minLines = minLines,
        keyboardType = keyboardType,
        onTextChanged = {
            inputText = it
            if (debounceTime == 0L) {
                onTextChanged(it)
            }
        },
        removeClick = {
            inputText = ""
        }
    )
}
