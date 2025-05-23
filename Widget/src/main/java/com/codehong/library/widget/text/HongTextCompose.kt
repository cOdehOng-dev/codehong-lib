package com.codehong.library.widget.text

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.codehong.library.widget.pretendardFontFamily
import com.codehong.library.widget.rule.HongTextLineBreak
import com.codehong.library.widget.rule.typo.lineHeight
import com.codehong.library.widget.rule.typo.size
import com.codehong.library.widget.util.adjustHeight
import com.codehong.library.widget.util.adjustWidth
import com.codehong.library.widget.util.dpToSp
import com.codehong.library.widget.util.lineBreakSyllable
import com.codehong.library.widget.util.parseComposeColor

@Composable
fun HongTextCompose(
    option: HongTextOption,
    onTextLayout: ((TextLayoutResult) -> Unit)? = null
) {
    if (!option.isValidComponent) {
        return
    }

    var textDecoration: TextDecoration? = null
    if (option.isEnableCancelLine || option.isEnableUnderLine) {
        val decoList = mutableListOf<TextDecoration>()
        if (option.isEnableUnderLine) {
            decoList.add(TextDecoration.Underline)
        }
        if (option.isEnableCancelLine) {
            decoList.add(TextDecoration.LineThrough)
        }
        textDecoration = TextDecoration.combine(decoList)
    }

    val isLineBreakSyllable = option.lineBreak == HongTextLineBreak.SYLLABLE
    var fullText = option.text ?: ""

    if (isLineBreakSyllable) {
        fullText = fullText.lineBreakSyllable() ?: ""
    }

    val text = buildAnnotatedString {
        this.append(fullText)

        if (option.spanTextsProperty.isNullOrEmpty()) {
            return@buildAnnotatedString
        }

        option.spanTextsProperty?.forEach { spanProperty ->
            spanProperty.injectOption(option)

            val target = if (isLineBreakSyllable) {
                spanProperty.text?.lineBreakSyllable()
            } else {
                spanProperty.text
            }

            target?.toRegex()?.findAll(fullText)?.forEach { matchResult ->
                val startIndex = matchResult.range.first
                val endIndex = matchResult.range.last + 1

                var spanTextDecoration: TextDecoration? = null
                if (spanProperty.isEnableCancelLine || spanProperty.isEnableUnderLine) {
                    val decoList = mutableListOf<TextDecoration>()
                    if (spanProperty.isEnableCancelLine) {
                        decoList.add(TextDecoration.LineThrough)
                    }
                    if (spanProperty.isEnableUnderLine) {
                        decoList.add(TextDecoration.Underline)
                    }
                    spanTextDecoration = TextDecoration.combine(decoList)
                }

                this.addStyle(
                    style = SpanStyle(
                        color = (
                                spanProperty.colorHex
                                    ?: HongTextOption.DEFAULT_LABEL_COLOR.hex
                                ).parseComposeColor(),
                        fontWeight = spanProperty.fontWeight,
                        fontSize = dpToSp(
                            dp = (spanProperty.size ?: HongTextOption.DEFAULT_TYPOGRAPHY.size()).dp
                        ),
                        fontFamily = pretendardFontFamily,
                        textDecoration = spanTextDecoration
                    ),
                    start = startIndex,
                    end = endIndex
                )
            }
        }
    }

    if (option.hasMargin()) {
        Box(
            modifier = Modifier
                .padding(
                    start = option.margin.left.dp,
                    top = option.margin.top.dp,
                    end = option.margin.right.dp,
                    bottom = option.margin.bottom.dp
                )
                .adjustWidth(option.width)
                .adjustHeight(option.height)
        ) {
            Text(
                modifier = Modifier
                    .padding(
                        start = option.padding.left.dp,
                        top = option.padding.top.dp,
                        end = option.padding.right.dp,
                        bottom = option.padding.bottom.dp
                    )
                    .adjustWidth(option.width)
                    .adjustHeight(option.height),
                text = text,
                color = (
                        option.colorHex
                            ?: HongTextOption.DEFAULT_LABEL_COLOR.hex
                        ).parseComposeColor(),
                fontFamily = pretendardFontFamily,
                fontWeight = option.fontWeight,
                fontSize = dpToSp(dp = (option.size ?: HongTextOption.DEFAULT_TYPOGRAPHY.size())),
                lineHeight = dpToSp(
                    dp = option.lineHeight ?: HongTextOption.DEFAULT_TYPOGRAPHY.lineHeight()
                ),
                letterSpacing = (-0.05).sp, // TextView 보다 자간이 살짝 넓어서 시각적 차이를 줄이기 위해 값 조정
                textAlign = option.align.value,
                maxLines = option.maxLines,
                overflow = option.overflow.value,
                textDecoration = textDecoration,
                onTextLayout = onTextLayout ?: {}
            )
        }
    } else {
        Text(
            modifier = Modifier
                .padding(
                    start = option.padding.left.dp,
                    top = option.padding.top.dp,
                    end = option.padding.right.dp,
                    bottom = option.padding.bottom.dp
                )
                .adjustWidth(option.width)
                .adjustHeight(option.height),
            text = text,
            color = (
                    option.colorHex
                        ?: HongTextOption.DEFAULT_LABEL_COLOR.hex
                    ).parseComposeColor(),
            fontFamily = pretendardFontFamily,
            fontWeight = option.fontWeight,
            fontSize = dpToSp(dp = (option.size ?: HongTextOption.DEFAULT_TYPOGRAPHY.size())),
            lineHeight = dpToSp(
                dp = option.lineHeight ?: HongTextOption.DEFAULT_TYPOGRAPHY.lineHeight()
            ),
            letterSpacing = (-0.05).sp, // TextView 보다 자간이 살짝 넓어서 시각적 차이를 줄이기 위해 값 조정
            textAlign = option.align.value,
            maxLines = option.maxLines,
            overflow = option.overflow.value,
            textDecoration = textDecoration,
            onTextLayout = onTextLayout ?: {}
        )
    }
}
