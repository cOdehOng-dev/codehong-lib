package com.codehong.library.widget.textfield

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.Composable
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
import com.codehong.library.widget.typo.fontWeight
import com.codehong.library.widget.typo.size
import com.codehong.library.widget.util.dpToSp
import com.codehong.library.widget.util.getColor

@Composable
fun HongTextField(
    modifier: Modifier = Modifier,
    placeholder: String = "",
    placeholderColor: HongComposeColor = HongComposeColor(
        colorType = ColorType.BLACK_60
    ),
    placeholderTypo: TypoType = TypoType.BODY_16,
    inputTextTypo: TypoType = TypoType.BODY_16_B,
    inputTextColor: HongComposeColor = HongComposeColor(
        colorType = ColorType.BLACK_100
    ),
    cursorColor: HongComposeColor = HongComposeColor(
        colorType = ColorType.BLACK_100
    ),
    useHideKeyboard: Boolean = true,
    singleLine: Boolean = true,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    keyboardType: KeyboardType = KeyboardType.DONE,
    onTextChanged: (String) -> Unit
) {
    HongTextField(
        modifier = modifier,
        placeholder = placeholder,
        placeholderColor = placeholderColor,
        placeholderSize = placeholderTypo.size(),
        placeholderFontWeight = placeholderTypo.fontWeight(),
        inputTextSize = inputTextTypo.size(),
        inputTextFontWeight = inputTextTypo.fontWeight(),
        inputTextColor = inputTextColor,
        cursorColor = cursorColor,
        useHideKeyboard = useHideKeyboard,
        singleLine = singleLine,
        maxLines = maxLines,
        minLines = minLines,
        keyboardType = keyboardType,
        onTextChanged = onTextChanged
    )
}

@Composable
fun HongTextField(
    modifier: Modifier = Modifier,
    placeholder: String = "",
    placeholderColor: HongComposeColor = HongComposeColor(
        colorRes = R.color.honglib_color_9929292d,
        colorType = ColorType.BLACK_60
    ),
    placeholderSize: Int = 16,
    placeholderFontWeight: FontWeight = FontWeight.W400,

    inputTextSize: Int = 16,
    inputTextFontWeight: FontWeight = FontWeight.W700,

    inputTextColor: HongComposeColor = HongComposeColor(
        colorRes = R.color.honglib_color_29292d,
        colorType = ColorType.BLACK_100
    ),
    cursorColor: HongComposeColor = HongComposeColor(
        colorRes = R.color.honglib_color_29292d,
        colorType = ColorType.BLACK_100
    ),
    useHideKeyboard: Boolean = true,
    singleLine: Boolean = true,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    keyboardType: KeyboardType = KeyboardType.DONE,
    onTextChanged: (String) -> Unit
) {
    val focusManager = LocalFocusManager.current
    var inputText by rememberSaveable { mutableStateOf("") }

    Box(
        modifier = modifier,
        contentAlignment = Alignment.CenterStart
    ) {
        if (inputText.isEmpty()) {
            HongText(
                text = placeholder,
                style = HongComposeTextStyle(
                    textSize = placeholderSize,
                    fontWeight = placeholderFontWeight,
                    textColorType = placeholderColor.colorType,
                    textColorResId = placeholderColor.colorRes
                )
            )
        }

        BasicTextField(
            modifier = Modifier.fillMaxWidth(),
            value = inputText,
            onValueChange = {
                inputText = it
                onTextChanged(it)
            },
            textStyle = TextStyle(
                color = inputTextColor.getColor(),
                fontWeight = inputTextFontWeight,
                fontSize = dpToSp(dp = inputTextSize),
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
fun HongTextFieldRemoveButton(
    modifier: Modifier = Modifier,
    placeholder: String = "",
    placeholderColor: HongComposeColor = HongComposeColor(
        colorType = ColorType.BLACK_60
    ),
    placeholderTypo: TypoType = TypoType.BODY_16,
    inputTextTypo: TypoType = TypoType.BODY_16_B,
    inputTextColor: HongComposeColor = HongComposeColor(
        colorType = ColorType.BLACK_100
    ),
    cursorColor: HongComposeColor = HongComposeColor(
        colorType = ColorType.BLACK_100
    ),
    @DrawableRes removeIconResId: Int = R.drawable.honglib_ic_20_close,
    useHideKeyboard: Boolean = true,
    singleLine: Boolean = true,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    keyboardType: KeyboardType = KeyboardType.DONE,
    onTextChanged: (String) -> Unit
) {
    HongTextFieldRemoveButton(
        modifier = modifier,
        placeholder = placeholder,
        placeholderColor = placeholderColor,
        placeholderSize = placeholderTypo.size(),
        placeholderFontWeight = placeholderTypo.fontWeight(),
        inputTextSize = inputTextTypo.size(),
        inputTextFontWeight = inputTextTypo.fontWeight(),
        inputTextColor = inputTextColor,
        cursorColor = cursorColor,
        removeIconResId = removeIconResId,
        useHideKeyboard = useHideKeyboard,
        singleLine = singleLine,
        maxLines = maxLines,
        minLines = minLines,
        keyboardType = keyboardType,
        onTextChanged = onTextChanged
    )
}

@Composable
fun HongTextFieldRemoveButton(
    modifier: Modifier = Modifier,
    placeholder: String = "",
    placeholderColor: HongComposeColor = HongComposeColor(
        colorRes = R.color.honglib_color_9929292d,
        colorType = ColorType.BLACK_60
    ),
    placeholderSize: Int = 16,
    placeholderFontWeight: FontWeight = FontWeight.W400,

    inputTextSize: Int = 16,
    inputTextFontWeight: FontWeight = FontWeight.W700,

    inputTextColor: HongComposeColor = HongComposeColor(
        colorRes = R.color.honglib_color_29292d,
        colorType = ColorType.BLACK_100
    ),
    cursorColor: HongComposeColor = HongComposeColor(
        colorRes = R.color.honglib_color_29292d,
        colorType = ColorType.BLACK_100
    ),
    @DrawableRes removeIconResId: Int = R.drawable.honglib_ic_20_close,
    useHideKeyboard: Boolean = true,
    singleLine: Boolean = true,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    keyboardType: KeyboardType = KeyboardType.DONE,
    onTextChanged: (String) -> Unit
) {
    val focusManager = LocalFocusManager.current
    var inputText by rememberSaveable { mutableStateOf("") }

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
                    style = HongComposeTextStyle(
                        textSize = placeholderSize,
                        fontWeight = placeholderFontWeight,
                        textColorType = placeholderColor.colorType,
                        textColorResId = placeholderColor.colorRes
                    )
                )
            }

            BasicTextField(
                modifier = Modifier.fillMaxWidth(),
                value = inputText,
                onValueChange = {
                    inputText = it
                    onTextChanged(it)
                },
                textStyle = TextStyle(
                    color = inputTextColor.getColor(),
                    fontWeight = inputTextFontWeight,
                    fontSize = dpToSp(dp = inputTextSize),
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
                        inputText = ""
                        focusManager.clearFocus()
                    },
                drawableResId = removeIconResId
            )
        }
    }
}



