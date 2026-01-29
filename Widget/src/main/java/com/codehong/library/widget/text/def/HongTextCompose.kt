package com.codehong.library.widget.text.def

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.codehong.library.widget.extensions.hongHeight
import com.codehong.library.widget.extensions.hongWidth
import com.codehong.library.widget.extensions.lineBreakSyllable
import com.codehong.library.widget.extensions.toColor
import com.codehong.library.widget.pretendardFontFamily
import com.codehong.library.widget.rule.HongTextLineBreak
import com.codehong.library.widget.rule.typo.lineHeight
import com.codehong.library.widget.rule.typo.size
import com.codehong.library.widget.util.dpToSp
import java.text.DecimalFormat

@Composable
fun HongTextCompose(
    option: HongTextOption,
    onTextLayout: ((TextLayoutResult) -> Unit)? = null
) {
    if (!option.isValidComponent) {
        return
    }

    val isLineBreakSyllable = option.lineBreak == HongTextLineBreak.SYLLABLE
    val fullText = option.formatText(isLineBreakSyllable)
    val annotatedText = buildStyledText(fullText, option, isLineBreakSyllable)
    val textDecoration = createTextDecoration(option.isEnableUnderLine, option.isEnableCancelLine)

    if (option.hasMargin()) {
        Box(
            modifier = Modifier
                .padding(
                    start = option.margin.left.dp,
                    top = option.margin.top.dp,
                    end = option.margin.right.dp,
                    bottom = option.margin.bottom.dp
                )
                .hongWidth(option.width)
                .hongHeight(option.height)
        ) {
            HongStyledText(
                option = option,
                text = annotatedText,
                textDecoration = textDecoration,
                onTextLayout = onTextLayout
            )
        }
    } else {
        HongStyledText(
            option = option,
            text = annotatedText,
            textDecoration = textDecoration,
            onTextLayout = onTextLayout
        )
    }
}

@Composable
private fun HongStyledText(
    option: HongTextOption,
    text: AnnotatedString,
    textDecoration: TextDecoration?,
    onTextLayout: ((TextLayoutResult) -> Unit)?
) {
    Text(
        modifier = Modifier
            .padding(
                start = option.padding.left.dp,
                top = option.padding.top.dp,
                end = option.padding.right.dp,
                bottom = option.padding.bottom.dp
            )
            .hongWidth(option.width)
            .hongHeight(option.height),
        text = text,
        fontFamily = pretendardFontFamily,
        fontWeight = option.fontWeight,
        fontSize = dpToSp(dp = (option.size ?: HongTextOption.DEFAULT_TYPOGRAPHY.size())),
        lineHeight = dpToSp(
            dp = option.lineHeight ?: HongTextOption.DEFAULT_TYPOGRAPHY.lineHeight()
        ),
        letterSpacing = (-0.05).sp,
        textAlign = option.align.value,
        maxLines = option.maxLines,
        overflow = option.overflow.value,
        textDecoration = textDecoration,
        onTextLayout = onTextLayout ?: {},
        style = TextStyle(
            color = (option.colorHex ?: HongTextOption.DEFAULT_LABEL_COLOR.hex).toColor(),
            platformStyle = PlatformTextStyle(includeFontPadding = false)
        )
    )
}

private fun HongTextOption.formatText(isLineBreakSyllable: Boolean): String {
    val formattedText = if (useNumberDecimal) {
        val clean = (text ?: "").replace(",", "").trim()
        when {
            clean.toLongOrNull() != null -> DecimalFormat("#,###").format(clean.toLong())
            clean.toDoubleOrNull() != null -> DecimalFormat("#,##0.##").format(clean.toDouble())
            else -> text ?: ""
        }
    } else {
        text ?: ""
    }

    return if (isLineBreakSyllable) {
        formattedText.lineBreakSyllable() ?: ""
    } else {
        formattedText
    }
}

private fun createTextDecoration(
    isEnableUnderLine: Boolean,
    isEnableCancelLine: Boolean
): TextDecoration? {
    if (!isEnableUnderLine && !isEnableCancelLine) {
        return null
    }

    val decorations = buildList {
        if (isEnableUnderLine) add(TextDecoration.Underline)
        if (isEnableCancelLine) add(TextDecoration.LineThrough)
    }

    return TextDecoration.combine(decorations)
}

@Composable
private fun buildStyledText(
    fullText: String,
    option: HongTextOption,
    isLineBreakSyllable: Boolean
): AnnotatedString {
    return buildAnnotatedString {
        append(fullText)

        if (option.spanTextBuilderList.isNullOrEmpty()) {
            return@buildAnnotatedString
        }

        option.spanTextBuilderList?.forEach { builder ->
            builder.injectOption(option)
            applySpanStyle(fullText, builder, isLineBreakSyllable)
        }
    }
}

@Composable
private fun AnnotatedString.Builder.applySpanStyle(
    fullText: String,
    builder: HongTextBuilder,
    isLineBreakSyllable: Boolean
) {
    val target = if (isLineBreakSyllable) {
        builder.option.text?.lineBreakSyllable()
    } else {
        builder.option.text
    } ?: return

    val spanDecoration = createTextDecoration(
        builder.option.isEnableUnderLine,
        builder.option.isEnableCancelLine
    )

    target.toRegex().findAll(fullText).forEach { matchResult ->
        addStyle(
            style = SpanStyle(
                color = (builder.option.colorHex ?: HongTextOption.DEFAULT_LABEL_COLOR.hex).toColor(),
                fontWeight = builder.option.fontWeight,
                fontSize = dpToSp(
                    dp = (builder.option.size ?: HongTextOption.DEFAULT_TYPOGRAPHY.size()).dp
                ),
                fontFamily = pretendardFontFamily,
                textDecoration = spanDecoration
            ),
            start = matchResult.range.first,
            end = matchResult.range.last + 1
        )
    }
}
