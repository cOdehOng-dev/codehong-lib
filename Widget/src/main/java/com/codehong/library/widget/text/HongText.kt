package com.codehong.library.widget.text

import androidx.annotation.ColorRes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import com.codehong.library.widget.pretendardFontFamily
import com.codehong.library.widget.util.dpToSp

/**
 * @param fontFamily 설정할 폰트 @default pretendardFont를 설정
 */
@Composable
fun HongText(
    modifier: Modifier = Modifier,
    text: String?,
    textSize: Int? = null,
    @ColorRes textColor: Int? = null,
    fontFamily: FontFamily = pretendardFontFamily,
    fontWeight: FontWeight = FontWeight.W400,
    textAlign: TextAlign? = null,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
    overflow: TextOverflow = TextOverflow.Clip,
    lineHeight: Float? = null,
    letterSpacing: Float? = null,
    useUnderline: Boolean = false,
    useLineThrough: Boolean = false,
    useItalic: Boolean = false,
    softWrap: Boolean = true,
    isEmptyOrNullHideView: Boolean = true,
    onTextLayout: ((TextLayoutResult) -> Unit)? = null
) {
    if (isEmptyOrNullHideView && text.isNullOrEmpty()) {
        return
    }

    var textDecoration: TextDecoration? = null
    if (useUnderline || useLineThrough) {
        val decoList = mutableListOf<TextDecoration>()
        if (useUnderline) {
            decoList.add(TextDecoration.Underline)
        }
        if (useLineThrough) {
            decoList.add(TextDecoration.LineThrough)
        }
        textDecoration = TextDecoration.combine(decoList)
    }
    Text(
        modifier = modifier,
        text = text ?: "",
        color = textColor?.let { colorResource(id = it) } ?: run { Color.Unspecified },
        fontFamily = fontFamily,
        fontWeight = fontWeight,
        fontSize = textSize?.let { dpToSp(dp = it) } ?: run { TextUnit.Unspecified },
        lineHeight = lineHeight?.let { dpToSp(dp = it) } ?: run { TextUnit.Unspecified },
        letterSpacing = letterSpacing?.let { dpToSp(dp = it) } ?: run { TextUnit.Unspecified },
        textAlign = textAlign,
        maxLines = maxLines,
        minLines = minLines,
        overflow = overflow,
        softWrap = softWrap,
        textDecoration = textDecoration,
        fontStyle = if (useItalic) FontStyle.Italic else FontStyle.Normal,
        onTextLayout = onTextLayout
    )
}
