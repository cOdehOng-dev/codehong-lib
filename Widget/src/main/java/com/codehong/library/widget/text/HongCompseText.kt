package com.codehong.library.widget.text

import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.codehong.library.widget.R
import com.codehong.library.widget.model.HongComposeColor
import com.codehong.library.widget.model.text.HongComposeHighlightTextInfo
import com.codehong.library.widget.model.text.HongComposeTextStyle
import com.codehong.library.widget.pretendardFontFamily
import com.codehong.library.widget.rule.color.HongColor
import com.codehong.library.widget.rule.typo.HongTypo
import com.codehong.library.widget.rule.typo.isBold
import com.codehong.library.widget.rule.typo.lineHeight
import com.codehong.library.widget.rule.typo.size
import com.codehong.library.widget.util.dpToSp
import com.codehong.library.widget.util.getColor

/**
 * @param fontFamily 설정할 폰트 @default pretendardFont를 설정
 */
@Composable
fun HongText(
    modifier: Modifier = Modifier,
    text: String?,
    size: Int? = null,
    color: HongComposeColor = HongComposeColor(
        resId = R.color.honglib_color_000000
    ),
    fontFamily: FontFamily = pretendardFontFamily,
    fontWeight: FontWeight = FontWeight.W400,
    textAlign: TextAlign? = null,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
    overflow: TextOverflow = TextOverflow.Clip,
    lineHeight: Float? = null,
    letterSpacing: Float? = null,
    highlight: HongComposeHighlightTextInfo? = null,
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

    val resultModifier = if (text.isNullOrEmpty()) {
        Modifier
            .then(modifier)
            .height(0.dp)
    } else {
        Modifier.then(modifier)
    }

    if (highlight != null) {
        val fullText = text ?: ""
        val annotatedString = buildAnnotatedString {
            val startIndex = fullText.indexOf(highlight.highlightText)
            if (startIndex != -1) {
                append(fullText.substring(0, startIndex))
                withStyle(
                    style = SpanStyle(
                        color = highlight.style.color.getColor(),
                        fontWeight = highlight.style.convertFontWeight(),
                        fontSize = dpToSp(dp = highlight.style.convertTextSize().dp),
                        fontFamily = pretendardFontFamily
                    )
                ) {
                    append(highlight.highlightText)
                }
                append(fullText.substring(startIndex + highlight.highlightText.length))
            } else {
                append(fullText) // 강조할 부분이 없으면 그냥 표시
            }
        }
        Text(
            modifier = resultModifier,
            text = annotatedString,
            color = color.getColor(),
            fontFamily = pretendardFontFamily,
            fontWeight = fontWeight,
            fontSize = size?.let { dpToSp(dp = it) } ?: TextUnit.Unspecified,
            lineHeight = lineHeight?.let { dpToSp(dp = it) } ?: TextUnit.Unspecified,
            letterSpacing = letterSpacing?.let { dpToSp(dp = it) } ?: TextUnit.Unspecified,
            textAlign = textAlign,
            maxLines = maxLines,
            overflow = overflow,
            softWrap = softWrap,
            textDecoration = textDecoration,
            fontStyle = if (useItalic) FontStyle.Italic else null,
            onTextLayout = onTextLayout ?: {}
        )
        return
    }

    Text(
        modifier = resultModifier,
        text = text ?: "",
        color = color.getColor(),
        fontFamily = fontFamily,
        fontWeight = fontWeight,
        fontSize = size?.let { dpToSp(dp = it) } ?: TextUnit.Unspecified,
        lineHeight = lineHeight?.let { dpToSp(dp = it) } ?: TextUnit.Unspecified,
        letterSpacing = letterSpacing?.let { dpToSp(dp = it) } ?: TextUnit.Unspecified,
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

@Composable
fun HongText(
    modifier: Modifier = Modifier,
    text: String?,
    typo: HongTypo,
    colorType: HongColor = HongColor.BLACK_100,
    textAlign: TextAlign? = null,
    maxLines: Int = Int.MAX_VALUE,
    letterSpacing: Int? = null,
    highlight: HongComposeHighlightTextInfo? = null,
    overflow: TextOverflow = TextOverflow.Clip,
    useUnderLine: Boolean = false,
    useLineThrough: Boolean = false,
    useItalic: Boolean = false,
    softWrap: Boolean = true,
    isEmptyOrNullHideView: Boolean = false,
    onTextLayout: ((TextLayoutResult) -> Unit)? = null
) {
    HongText(
        modifier = modifier,
        isEmptyOrNullHideView = isEmptyOrNullHideView,
        text = text ?: "",
        fontWeight = if (typo.isBold()) FontWeight.W700 else FontWeight.W400,
        size = typo.size(),
        color = HongComposeColor(
            type = colorType
        ),
        lineHeight = typo.lineHeight().toFloat(),
        textAlign = textAlign,
        highlight = highlight,
        maxLines = maxLines,
        overflow = overflow,
        softWrap = softWrap,
        letterSpacing = letterSpacing?.toFloat(),
        useUnderline = useUnderLine,
        useLineThrough = useLineThrough,
        useItalic = useItalic,
        onTextLayout = onTextLayout
    )
}

@Composable
fun HongText(
    modifier: Modifier = Modifier,
    text: String?,
    style: HongComposeTextStyle,
    textAlign: TextAlign? = null,
    maxLines: Int = Int.MAX_VALUE,
    overflow: TextOverflow = TextOverflow.Clip,
    lineHeight: Float? = null,
    letterSpacing: Float? = null,
    useUnderline: Boolean = false,
    useLineThrough: Boolean = false,
    useItalic: Boolean = false,
    softWrap: Boolean = true,
    isEmptyOrNullHideView: Boolean = false,
    onTextLayout: ((TextLayoutResult) -> Unit)? = null
) {
    HongText(
        modifier = modifier,
        isEmptyOrNullHideView = isEmptyOrNullHideView,
        text = text ?: "",
        fontWeight = style.fontWeight,
        size = style.size,
        color = style.color,
        lineHeight = lineHeight,
        textAlign = textAlign,
        maxLines = maxLines,
        overflow = overflow,
        softWrap = softWrap,
        letterSpacing = letterSpacing,
        useUnderline = useUnderline,
        useLineThrough = useLineThrough,
        useItalic = useItalic,
        onTextLayout = onTextLayout
    )
}
